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



public class NameActivity extends AppCompatActivity {
  
  Boolean isScreenBeingTouched;
  Integer DELAY_TIME_TO_SHOW_ALERT_BOX = 20000;
  
  
  private Handler handler = new Handler();
  View nameActivityXMLView;
  EditText userNameInput;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);
    nameActivityXMLView = findViewById(R.id.nameActivityXML);
    userNameInput = findViewById(R.id.userName);
    
    isScreenBeingTouched = true;
  
  
    lastTimeTouchOnScreen();
    
    screenMove.run();

  } // onCreate

  private Runnable screenMove =
      new Runnable() {
        @Override
        public void run() {
          if (isScreenBeingTouched) {
            if (userNameInput.hasFocus()) {
              System.out.println("has focus and is touched is true");
              isScreenBeingTouched = false;
              return;
            }

            System.out.println("System had touch input---------------------");
            isScreenBeingTouched = false;
          } else {
            System.out.println("System had NOT touch input-----------------");
            handler.removeCallbacks(screenMove);
            callAlertDialogBoxToInformUsersInactiveScreen();
          } // else
          handler.postDelayed(this, DELAY_TIME_TO_SHOW_ALERT_BOX);
        } // run
      }; // new Runnable

  private void lastTimeTouchOnScreen() {
    View myView = findViewById(R.id.nameActivityXML);
    myView.setOnTouchListener(
        new View.OnTouchListener() {
          public boolean onTouch(View v, MotionEvent event) {
            hideKeyboardAfterTypingName();
            isScreenBeingTouched = true;
            return true;
          }
        });
  } // lastTimeTouchOnScreen

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
    EditText textInputFromUser = findViewById(R.id.userName);
    String userNameFromInput = textInputFromUser.getText().toString().trim();
    if (userNameFromInput.isEmpty()){
      Toast.makeText(getApplicationContext(), R.string.pleaseEnterYourName, Toast.LENGTH_SHORT).show();
      return;
    } // if
    User.userName = userNameFromInput;
    Intent nextActivity = new Intent(NameActivity.this, ServicesActivity.class);
    startActivity(nextActivity);
    finish();
  } // checkInBtnClicked



  /**
   * Hides Keyboard after user touches anywhere one the screen when EditText is on focus.
   */
  private void hideKeyboardAfterTypingName() {
    nameActivityXMLView.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
            if (userNameInput.hasFocus()) {
              
              handler.postDelayed(screenMove, DELAY_TIME_TO_SHOW_ALERT_BOX);
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
        handler.postDelayed(screenMove, DELAY_TIME_TO_SHOW_ALERT_BOX);
        
      }
    });
  
    // Set the alert dialog no button click listener
    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // Do something when No button clicked
        handler.removeCallbacks(screenMove);
        changeActivityToMainActivity();
      }
    });
  
    AlertDialog dialog = builder.create();
    // Display the alert dialog on interface
    dialog.show();
  } // callAlertDialogBoxToInformUsersInactiveScreen
  
  /**
   * Goes to system screen to Main Activity screen
   */
  private void changeActivityToMainActivity(){
    Intent goToMainActivity = new Intent(NameActivity.this, MainActivity.class);
    NameActivity.this.startActivity(goToMainActivity);
    NameActivity.this.finish();
    
  } // changeActivityToMainActivity
  
} // NameActivity

