/** By Arnold Gihozo */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

  private Boolean isScreenBeingTouched;
  private Integer DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN = 15000;

  private Handler handler = new Handler();
  private View nameActivityXMLView;
  private EditText userNameInput;
  
  AlertDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);
    getNameActivityXMLViews();

    editTextInputFieldTypingListening();

    isScreenBeingTouched = true;

    lastTimeTouchOnScreen();

    inActiveScreenAlertForClass.run();
    
    
    
    
    
    
  } // onCreate
  
  
  @Override
  protected void onDestroy() {
    if (dialog != null && dialog.isShowing()) {
      dialog.dismiss();
      handler.removeCallbacks(inActiveScreenAlertForClass);
      handler.removeCallbacks(closeAlertBox);
    }
    super.onDestroy();
  }
  
  private void getNameActivityXMLViews() {
    nameActivityXMLView = findViewById(R.id.nameActivityXML);
    userNameInput = findViewById(R.id.userName);
  } // getNameActivityXMLViews

  private Runnable inActiveScreenAlertForClass =
      new Runnable() {
        @Override
        public void run() {
          if (isScreenBeingTouched) {

            if (userNameInput.hasFocus()) {
              System.out.println("has focus and is touched is true");
              isScreenBeingTouched = false; // F
              System.out.println("inside has focus --- is screen being touched "+isScreenBeingTouched);
  
              handler.postDelayed(this, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
              //return;
            }
            System.out.println("System had touch input---------------------");
            isScreenBeingTouched = false;
            System.out.println("after  ---  IFFFFFF ------ is screen being touched "+isScreenBeingTouched);
  
            handler.postDelayed(this, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
          } else {
            System.out.println("System had NOT touch input-----------------");
            //handler.postDelayed(inActiveScreenAlertForClass, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
            callAlertDialogBoxToInformUsersInactiveScreen();
          } // else
          //handler.postDelayed(this, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
        } // run
      }; // new Runnable

  private void lastTimeTouchOnScreen() {
    View myView = findViewById(R.id.nameActivityXML);
    myView.setOnTouchListener(
        new View.OnTouchListener() {
          public boolean onTouch(View v, MotionEvent event) {
            hideKeyboardAfterTypingName();
            isScreenBeingTouched = true;
            System.out.println("Last time TOUCH ON screen ---   is screen being touched "+isScreenBeingTouched);
  
            return true;
          }
        });
  } // lastTimeTouchOnScreen

  /**
   * Changes of string sizes (char) in EditText input field calls inActiveScreenAlertForClass method
   * to show alert box and decides to stay on the screen or not Code is taken from - (Modified by
   * Alvee)
   * https://stackoverflow.com/questions/35224459/how-to-detect-if-users-stop-typing-in-edittext-android
   * Document access date is 18-Jan-2020
   */
  private void editTextInputFieldTypingListening() {
    userNameInput.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            isScreenBeingTouched = true;
            System.out.println("is screen being touched "+isScreenBeingTouched);
  
            //handler.postDelayed(inActiveScreenAlertForClass, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
          }

          @Override
          public void onTextChanged(final CharSequence s, int start, int before, int count) {
            // You need to remove this to run only once
            isScreenBeingTouched = true;
            System.out.println("is screen being touched "+isScreenBeingTouched);
  
            //handler.postDelayed(inActiveScreenAlertForClass, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
          }

          @Override
          public void afterTextChanged(final Editable s) {
            // avoid triggering event when text is empty
            if (s.length() > 0) {
              isScreenBeingTouched = true;
              System.out.println("after text changed IF ---  is screen being touched "+isScreenBeingTouched);
  
              //handler.postDelayed(inActiveScreenAlertForClass, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
            } else {
              isScreenBeingTouched = true;
              
              System.out.println("after text changed ELSE --- is screen being touched "+isScreenBeingTouched);
              //handler.postDelayed(inActiveScreenAlertForClass, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
            }
            //handler.postDelayed(inActiveScreenAlertForClass, DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
          }
        });
  } // editTextInputFieldTypingListening

  /**
   * Checks the EditText input empty or not, then takes it value and stores it in the User class for
   * using email purpose. Also Takes user to next Activity - 'Services Activity'
   *
   * @param view - View object - Button object in this scenario by Alvee
   */
  public void whatDoINeedHelpBtnClicked(View view) {
    handler.removeCallbacks(inActiveScreenAlertForClass);
    handler.removeCallbacks(closeAlertBox);
    EditText textInputFromUser = findViewById(R.id.userName);
    String userNameFromInput = textInputFromUser.getText().toString().trim();
    if (userNameFromInput.isEmpty()) {
      Toast.makeText(getApplicationContext(), R.string.pleaseEnterYourName, Toast.LENGTH_SHORT)
          .show();
      return;
    } // if
    User.userName = userNameFromInput;
  
    Intent goToMainActivity = new Intent(NameActivity.this, ServicesActivity.class);
    NameActivity.this.startActivity(goToMainActivity);
    NameActivity.this.finish();
  } // checkInBtnClicked

  /** Hides Keyboard after user touches anywhere one the screen when EditText is on focus. */
  private void hideKeyboardAfterTypingName() {
    nameActivityXMLView.setOnTouchListener(
        new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
            if (userNameInput.hasFocus()) {

              handler.postDelayed(
                  inActiveScreenAlertForClass,
                  DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
              hideSoftKeyboard(NameActivity.this);
              return true;
            } else return false;
          }
        });
  }

  /**
   * Hides keyboard from the current screen
   *
   * @param activity - where the method call happens Code is taken from
   *     https://stackoverflow.com/questions/1109022/close-hide-android-soft-keyboard Document was
   *     access on 15-Jan-2020
   *     <p>by Alvee
   */
  private void hideSoftKeyboard(Activity activity) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
  } // hideSoftKeyboard

  /**
   * Calls AlertBox to show on screen that page is being inactive, options given Yes, No. Yes to
   * stay on the screen, No to send back to Main Activity screen
   *
   * <p>Code is taken from - (Modified by Alvee)
   * https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
   * Document access date is 18-Jan-2020
   *
   * <p>by Alvee
   */
  public void callAlertDialogBoxToInformUsersInactiveScreen() {

    AlertDialog.Builder builder = new AlertDialog.Builder(NameActivity.this);

    // Set a title for alert dialog
    builder.setTitle("Inactive Screen");

    // Ask the final question
    builder.setMessage("Are you still with us?");

    // Set the alert dialog yes button click listener
    builder.setPositiveButton(
        "      Yes      ",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            // Do something when user clicked the Yes button
            System.out.println("ALERT BOX APPEARING --  is screen being touched "+isScreenBeingTouched);
  
            isScreenBeingTouched = true;
            System.out.println("ALERT BOX DISAPPEARING ---  is screen being touched "+isScreenBeingTouched);
  
            handler.postDelayed(
                inActiveScreenAlertForClass,
                DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN);
            handler.removeCallbacks(closeAlertBox);
            
          }
        });

    dialog = builder.create();
    // Display the alert dialog on interface
    dialog.show();
    handler.postDelayed(closeAlertBox, 5000);
    
  } // callAlertDialogBoxToInformUsersInactiveScreen

  private Runnable closeAlertBox =
      new Runnable() {
        @Override
        public void run() {

          if (dialog.isShowing()) {
            
            System.out.println("INSIDE DIALOG SHOWING ---- is screen being touched "+isScreenBeingTouched);
            
            dialog.dismiss();
            changeActivityToMainActivity();
          }
          handler.postDelayed(this, 5000);
        }
      };

  /** Goes to system screen to Main Activity screen */
  private void changeActivityToMainActivity() {
    handler.removeCallbacks(closeAlertBox);
    handler.removeCallbacks(inActiveScreenAlertForClass);
    Intent goToMainActivity = new Intent(NameActivity.this, MainActivity.class);
    NameActivity.this.startActivity(goToMainActivity);
    NameActivity.this.finish();
  } // changeActivityToMainActivity
} // NameActivity
