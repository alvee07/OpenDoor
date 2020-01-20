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
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements OnItemSelectedListener {

  private String selectedServices;
  private String selectedWorker;
  private User currentUser;
  private Boolean isSelectedOption;
  private Spinner services;
  private Spinner worker;
  private GestureDetectorCompat mDetector;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_services);


    List<String> serviceList =   Arrays.asList(getResources().getStringArray(R.array.services));
    List <String> workerList = Arrays.asList(getResources().getStringArray(R.array.names));



      services =  findViewById(R.id.servicesSpinner);
      worker = findViewById(R.id.workerSpinner);







    services.setAdapter(new SpinnerAdapter(serviceList , this));
    worker.setAdapter(new SpinnerAdapter(workerList, this));


    services.setOnItemSelectedListener(this);
    worker.setOnItemSelectedListener(this);


  } // end of onCreate




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


    if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
      enableSpinners();
      isSelectedOption = false;

    } else {

      switch (parent.getId()) {
        case R.id.servicesSpinner:
          disableSpinner(worker);
          getSelectedServices(parent, position);
          isSelectedOption = true;
          break;
        case R.id.workerSpinner:
          disableSpinner(services);
          isSelectedOption = true;
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
    currentSpinner.setAlpha(0.3f);

  }

  /**
   * Enables both the worker and staff spinner
   * by Arnold
   */
  public void enableSpinners(){
    services.setEnabled(true);
    worker.setEnabled(true);
    services.setAlpha(1f);
    worker.setAlpha(1f);
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
   * Takes user to next Activity - 'Emotions Activity' or, 'Confirmation Activity'
   * @param view - View object - Button object in this scenario
   *
   * Modified by Arnold (added the isSelectedOption in oder to not change activity
   * without selecting the service
   *
   * by Alvee
   *
   */
  public void servicesContinueBtnClicked (View view){

      if(isSelectedOption == false){
          Toast.makeText(getApplicationContext(), R.string.pleaseChooseAnOption, Toast.LENGTH_SHORT)
                  .show();
      }else{
          Intent name = new Intent(ServicesActivity.this, EmotionsCheck.class);
          startActivity(name);
          finish();
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








/**
 * To work on:
 *
 * Cleaning up the view inside the drop down!
 * Disabeling (when you have an item selected)
 * Not continue onto the next activitiy
 * Getting the "Choose an Option" as position 0!
 */

