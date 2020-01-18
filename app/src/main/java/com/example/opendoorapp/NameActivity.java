/**
 * By Arnold Gihozo
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class NameActivity extends AppCompatActivity {
  
  Long currentTime;
  Long lastCheckedTime;
  Boolean isScreenBeingTouched = false;
  private Integer DELAY_TIME_TO_SHOW_ALERT_BOX = 15000;
  
  
  private Handler handler = new Handler();
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);
    hideKeyboardAfterTypingName();

    currentTime = System.currentTimeMillis();
    lastCheckedTime = System.currentTimeMillis();
    // screenIsAboutToChange();
  
  
    lastTimeTouchOnScreen();
    
    screenMove.run();
    

  } // onCreate

  private Runnable screenMove =
      new Runnable() {

        @Override
        public void run() {
          
          if (isScreenBeingTouched) {
            System.out.println("System had touch input---------------------");
            isScreenBeingTouched = false;
          }
          else {

            handler.postDelayed(this, DELAY_TIME_TO_SHOW_ALERT_BOX);
            System.out.println("first time--------- " + currentTime);
            System.out.println("current time------- " + System.currentTimeMillis());
            System.out.println("difference is ----- " + (System.currentTimeMillis() - currentTime));
          } // else
        } // run
      }; // new Runnable

  private void lastTimeTouchOnScreen() {
    View myView = findViewById(R.id.nameActivityXML);
    myView.setOnTouchListener(
        new View.OnTouchListener() {
          public boolean onTouch(View v, MotionEvent event) {
            // ... Respond to touch events
            
            isScreenBeingTouched = true;
            currentTime = System.currentTimeMillis();
            System.out.println("Current time on System is "+currentTime);
            
            return true;
          }
        });
  }


// Alvee's Method
  /**
   * Checks the EditText input empty or not, then takes it value and stores it in the
   * User class for using email purpose.
   * Also Takes user to next Activity - 'Services Activity'
   *
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   */
  public void whatDoINeedHelpBtnClicked(View view){
    Toast nameMessage = Toast.makeText(getApplicationContext(),"Please Enter Your Name", Toast.LENGTH_SHORT);
    EditText textInputFromUser = findViewById(R.id.userName);
    String userNameFromInput = textInputFromUser.getText().toString().trim();
    if (userNameFromInput.isEmpty()){
      nameMessage.show();
      return;
      //return;
    }
    User.userName = userNameFromInput;
    Intent nextActivity = new Intent(NameActivity.this, ServicesActivity.class);
    startActivity(nextActivity);
    finish();
  } // checkInBtnClicked



  /**
   * Hides Keyboard after user touches anywhere one the screen when EditText is on focus.
   */
  private void hideKeyboardAfterTypingName() {

    View nameActivityXMLView = findViewById(R.id.nameActivityXML);
    final EditText userNameInput = findViewById(R.id.userName);

    nameActivityXMLView.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
            if (userNameInput.hasFocus()) {
              hideSoftKeyboard(NameActivity.this);
              return true;
            } else return false;
          }
        });
  }


  /**
   * Hides keyboard from the current screen
   *
   * @param activity - where the method call happens
   * Code is taken from https://stackoverflow.com/questions/1109022/close-hide-android-soft-keyboard
   * Document was access on 15-Jan-2020
   *
   * by Alvee
   */
  private void hideSoftKeyboard(Activity activity) {
    InputMethodManager inputMethodManager =
            (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
  } // hideSoftKeyboard
  
  
  /**
   *
   *
   * https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
   */
  public void callAlertDialogBoxToInformUsersInactiveScreen(){
  
    AlertDialog.Builder builder = new AlertDialog.Builder(NameActivity.this);
  
    // Set a title for alert dialog
    builder.setTitle("Inactive Screen");
  
    // Ask the final question
    builder.setMessage("Are you still with us?");
  
    // Set the alert dialog yes button click listener
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // Do something when user clicked the Yes button
        // Set the TextView visibility GONE
      }
    });
  
    // Set the alert dialog no button click listener
    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // Do something when No button clicked
        Toast.makeText(getApplicationContext(),
                "No Button Clicked",Toast.LENGTH_SHORT).show();
      }
    });
  
    AlertDialog dialog = builder.create();
    // Display the alert dialog on interface
    dialog.show();
  } // callAlertDialogBoxToInformUsersInactiveScreen
  
  
  private Runnable lastTimeActiveScreenCheck =
          new Runnable() {
            
            @Override
            public void run() {
              Toast.makeText(NameActivity.this, "This is a delayed toast", Toast.LENGTH_SHORT).show();
              
              handler.postDelayed(this, DELAY_TIME_TO_SHOW_ALERT_BOX);
              if ((System.currentTimeMillis() - DELAY_TIME_TO_SHOW_ALERT_BOX) > lastCheckedTime) {
                System.out.println("System time is "+System.currentTimeMillis() +" last checked time is "+ lastCheckedTime);
              }
              if (isScreenBeingTouched) {
                isScreenBeingTouched = false;
              } else {
                
                System.out.println("Current time on System is " + System.currentTimeMillis());
                System.out.println("You are about to go to another screen----------------------------");
              }
            }
          };
  
  
} //
