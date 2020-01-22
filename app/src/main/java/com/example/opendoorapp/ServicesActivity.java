/**
 * AUCSC 320 ServicesActivity.java By Arnold Gihozo
 *
 * <p>This class will give the opportunity to the user to choose between either a service or a
 * worker.It will also connect the service activity to the emotion activitiy
 */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Looper;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity implements OnItemSelectedListener {

  private String selectedServices;
  private String selectedWorker;
  public static ArrayList<String> selectedEmailList;
  private Boolean isSelectedService;
  private Boolean isSelectedWorker;
  private User currentUser;
  private Boolean isSelectedOption;
  private Spinner services;
  private Spinner worker;
  private final Integer DELAY_TIME_TO_START_MAIN_ACTIVITY = 60000;
  private Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_services);

    // puts all the items in the from the strings.xml into the List strings
    List<String> serviceList = Arrays.asList(getResources().getStringArray(R.array.services));
    List<String> workerList = Arrays.asList(getResources().getStringArray(R.array.names));

    // Sets up the services and worker spinner
    services = findViewById(R.id.servicesSpinner);
    worker = findViewById(R.id.workerSpinner);

    // sends the information into the SpinnerAdapter
    services.setAdapter(new SpinnerAdapter(serviceList, this));
    worker.setAdapter(new SpinnerAdapter(workerList, this));

    isSelectedService = false;
    isSelectedWorker = false;

    services.setOnItemSelectedListener(this);
    worker.setOnItemSelectedListener(this);

    // If user stays more than one min on the screen, it will go to Main screen
    changeActivityToMainActivity();
  } // end of onCreate

  /**
   * Checks the selections of the user. If user selects services, it disables the staff lists and
   * vice versa.
   *
   * @param parent -- adapter view of where the selection has happened
   * @param view -- the view that was selected
   * @param position -- position of the view
   * @param id -- the id of the item selected
   *     <p>To excecute this section, I looked over the code on StackOver flow on Saturday January
   *     11, 2020 https://stackoverflow.com/questions/4476379/spinner-switch-case-problem Changes
   *     have been made to fit this project and refactoring the code
   *     <p>by Arnold
   */
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    setBackgroundColor();

    if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
      enableSpinners();

    } else {

      switch (parent.getId()) {
        case R.id.servicesSpinner:
          isSelectedService = true;
          disableSpinner(worker);
          getSelectedServices(parent, position);
          selectedWorker = "not selected";
          break;

        case R.id.workerSpinner:
          isSelectedWorker = true;
          disableSpinner(services);
          getSelectedWorker(parent, position);
          selectedServices = "not selected";

          break;
      } // end of switch
    } // end of else
  } // end of onItemSelected

  /**
   * Sets the background of the drop down menu to white (for both the works and staff)
   *
   * <p>by Arnold
   */
  public void setBackgroundColor() {
    services.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
    worker.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
  }

  /**
   * Disables a given spinner and greys out the given spinner
   *
   * @param currentSpinner-- the spinner to be disabled
   *     <p>by Arnold
   */
  public void disableSpinner(Spinner currentSpinner) {
    currentSpinner.setEnabled(false);
    currentSpinner.setAlpha(0.3f);
  }

  /** Enables both the worker and staff spinner and resets the alpha value by Arnold */
  public void enableSpinners() {
    services.setEnabled(true);
    worker.setEnabled(true);
    services.setAlpha(1f);
    worker.setAlpha(1f);
  }

  /**
   * Trigged when a view disaperars from the screen or when you have an empty adapter
   *
   * @param parent -- it is an adapter view (with no selected item)
   *     <p>by Arnold
   */
  public void onNothingSelected(AdapterView<?> parent) {}

  /**
   * Gets the selected services
   *
   * @param parent -- adapter view of where the selection happens
   * @param position -- position of the view
   * @return -- returns the selected item
   *     <p>by Arnold
   */
  public String getSelectedServices(AdapterView<?> parent, int position) {
    return selectedServices = parent.getItemAtPosition(position).toString();
  }
  /**
   * Gets the selected services
   *
   * @param parent -- adapter view of where the selection happens
   * @param position -- position of the view
   * @return -- returns the selected item
   *     <p>by Arnold
   */
  public String getSelectedWorker(AdapterView<?> parent, int position) {
    return selectedWorker = parent.getItemAtPosition(position).toString();
  }

  /**
   * Takes user to next Activity - 'Emotions Activity' or, 'Confirmation Activity'
   *
   * @param view - View object - Button object in this scenario
   *     <p>Modified by Arnold (added the isSelectedOption in oder to not change activity without
   *     selecting the service
   *     <p>by Alvee Hassan Akash
   *     <p>edited by benjamin to send user between emotion screen & confirmation screen
   */
  public void servicesContinueBtnClicked(View view) {

    ServiceStorageList serviceStorageList = new ServiceStorageList();
    WorkerStorageList workerStorageList = new WorkerStorageList();

    ArrayList<Service> serviceList = serviceStorageList.getServiceStorageList();
    ArrayList<Workers> workerList = workerStorageList.getWorkerStorageList();

    Toast message =
        Toast.makeText(
            getApplicationContext(), "Please Select a Service or Staff member", Toast.LENGTH_SHORT);
    Intent emotion = new Intent(ServicesActivity.this, EmotionsCheck.class);
    Intent name = new Intent(ServicesActivity.this, ConfirmationActivity.class);
    if (isSelectedWorker || isSelectedService) {
      if (selectedWorker.equals("not selected")) {
        // check to find what the user selected
        for (int counter = 0; counter < serviceList.size(); counter++) {
          System.out.println("COUNTER IS AT:" + counter);
          System.out.println("service is: " + serviceList.get(counter));
          if (serviceList.get(counter).getDepartmentName().equals(selectedServices)) {
            selectedEmailList = serviceList.get(counter).getEmailList();
            User.serviceName = selectedServices;
            System.out.println("selected emails are: " + selectedEmailList);
            if (serviceList.get(counter).isEmotion()) {
              startActivity(emotion);
              finish();
              break;
            } // if is emotion
            else {
              startActivity(name);
              finish();
              break;
            } // go to confirmation
          } // if statement
        } // for loop
      } // if selectedWorker
      else {
        // check to find what the user selected
        for (int counter = 0; counter < workerList.size(); counter++) {
          System.out.println("counter is at: " + counter);
          if (workerList.get(counter).getName().equals(selectedWorker)) {
            selectedEmailList = workerList.get(counter).getEmail();
            User.workerName = selectedWorker;
            if (workerList.get(counter).isEmotion()) {
              startActivity(emotion);
              finish();
              break;
            } // if is emotion
            else {
              startActivity(name);
              finish();
              break;
            } // go to confirmation
          } // if statement
        } // for loop
      } // else
    } // if either worker or service selected
    else {
      message.show();
      return;
    }
  } // servicesContinueBtnClicked

  /**
   * Goes back to Main Activity screen after an 1 min Assuming, mostly user can input their name in
   * one min
   *
   * <p>by Alvee Hassan Akash
   */
  private void changeActivityToMainActivity() {
    MAIN_HANDLER.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            final Intent goBackToMainActivity =
                new Intent(ServicesActivity.this, MainActivity.class);
            ServicesActivity.this.startActivity(goBackToMainActivity);
            ServicesActivity.this.finish();
          }
        },
        DELAY_TIME_TO_START_MAIN_ACTIVITY);
  } // changeActivityToMainActivity

  /** Last task of this activity to happen before destroying it fully */
  @Override
  protected void onDestroy() {
    MAIN_HANDLER.removeCallbacksAndMessages(null);
    System.out.println("Services Activity is done forever-----------no handler");
    super.onDestroy();
  }

  /** Disabling the back button, so user can not go back to Main Screen just by clicking it */
  @Override
  public void onBackPressed() {
    // disable the android app back button
  }
} // end of class
