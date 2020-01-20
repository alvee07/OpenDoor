/**
 * AUCSC 320
 * ServicesActivity.java
 * By Arnold Gihozo
 *
 *
 * This class will give the opportunity to the user to choose between either a service
 * or a worker. If a specific services is taken, it will then lead the user to choose
 * their emotion.
 *
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity implements OnItemSelectedListener {

  private String selectedServices;
  private String selectedWorker;
  private ArrayList<String> selectedEmailList;
  private Boolean isSelectedService;
  private Boolean isSelectedWorker;
  private Spinner services;
  private Spinner worker;
  private GestureDetectorCompat mDetector;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_services);

    servicesSpinner();
    workerSpinner();

    isSelectedService = false;
    isSelectedWorker = false;

    services.setOnItemSelectedListener(this);
    worker.setOnItemSelectedListener(this);


  } // end of onCreate


  /**
   * Sets up the services spinner (with the items from the string.xml file) and sets up
   * the drop down menu
   *
   * by Arnold
   *
   * Initial setup of this has been inspired from this source
   * https://developer.android.com/guide/topics/ui/controls/spinner.
   * Accessed on January 11, 2020
   */
  public void servicesSpinner(){
    services =  findViewById(R.id.servicesSpinner);
    //container that hold the values and integrate them with the spinner
    ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.services));

    //Drop down list of services stored in .xml file
    servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    services.setAdapter(servicesAdapter);
  } // end of servicesSpinner

  /**
   * Sets up the worker spinner (with the items from the string.xml file) and sets up
   * the drop down menu
   *
   * by Arnold
   *
   * Initial setup of this has been inspired from this source
   * https://developer.android.com/guide/topics/ui/controls/spinner.
   * Accessed on January 11, 2020
   */
  public void workerSpinner(){
    //Workers
    //==============================================================================
    worker = findViewById(R.id.workerSpinner);
    // Container that hold the values and integrate them with the spinner
    ArrayAdapter<String> workerAdapter = new ArrayAdapter<String>(ServicesActivity.this,
            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

    workerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    worker.setAdapter(workerAdapter);

  }// end of workerSpinner

  /**
   * Checks the selections of the user. If user selects services, it disables the staff
   * lists and vice versa.
   *
   * @param parent -- adapter view of where the selection has happened
   * @param view -- the view that was selected
   * @param position -- position of the view
   * @param id -- the id of the item selected
   *
   * To excecute this section, I looked over the code on StackOver flow on Saturday January 11, 2020
   * https://stackoverflow.com/questions/4476379/spinner-switch-case-problem
   * Changes have been made to fit this project and refactoring the code
   *
   * by Arnold
   */
  public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {

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
      }// end of switch

    }// end of else

  }// end of onItemSelected

  /**
   * Sets the background of the drop down menu to white (for both the works and staff)
   *
   * by Arnold
   */
  public void setBackgroundColor(){
    services.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
    worker.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
  }

  /**
   * Disables a given spinner
   * @param currentSpinner-- the spinner to be disabled
   *
   * by Arnold
   */
  public void disableSpinner(Spinner currentSpinner){
    currentSpinner.setEnabled(false);
  }

  /**
   * Enables both the worker and staff spinner
   * by Arnold
   */
  public void enableSpinners(){
    services.setEnabled(true);
    worker.setEnabled(true);
  }

  /**
   * Trigged when a view disaperars from the screen or when you have an empty adapter
   *
   * @param parent -- it is an adapter view (with no selected item)
   *
   * by Arnold
   */
  public void onNothingSelected(AdapterView<?> parent) {}

  /**
   * Gets the selected services
   *
   * @param parent -- adapter view of where the selection happens
   * @param position -- position of the view
   * @return -- returns the selected item
   *
   * by Arnold
   */
  public String getSelectedServices(AdapterView<?> parent, int position){
    return selectedServices = parent.getItemAtPosition(position).toString();

  }
  /**
   * Gets the selected services
   *
   * @param parent -- adapter view of where the selection happens
   * @param position -- position of the view
   * @return -- returns the selected item
   *
   * by Arnold
   */
  public String getSelectedWorker(AdapterView<?> parent, int position){
    return selectedWorker = parent.getItemAtPosition(position).toString();

  }

  /**
   * Takes user to next Activity - 'Emotions Activity' or, 'Confirmation Activity'
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   * edited by benjamin to send select between emotion screen & confirmation screen
   *
   */
  public void servicesContinueBtnClicked (View view){

    Toast message = Toast.makeText(getApplicationContext(),
            "Please Select a Service or Staff member", Toast.LENGTH_SHORT);
    Intent emotion = new Intent(ServicesActivity.this, EmotionsCheck.class);
    Intent name = new Intent(ServicesActivity.this, ConfirmationActivity.class);
    if(isSelectedWorker || isSelectedService ){

      if(selectedWorker.equals("not selected")){

      }
      startActivity(name);
      finish();

    }
    else {
      message.show();
      return;
    }
  } // servicesContinueBtnClicked

  /**
   * Starts MainActivity class in 10 seconds
   *
   * by Alvee
   */
  public void startMainActivity(){
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        final Intent goBackToMainActivity = new Intent(ServicesActivity.this, MainActivity.class);
        ServicesActivity.this.startActivity(goBackToMainActivity);
        ServicesActivity.this.finish();
      }
    }, 10000);
  } // startMainActivity


}// end of file

