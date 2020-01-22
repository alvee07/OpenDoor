/**
 * NameActivity.java
 *
 * <p>This class takes user name as an input and have an option to go to next activity to choose
 * Services or workers.
 *
 * <p>If the user does not input any name, and clicks button, it will show a toast message on the
 * screen.
 *
 * <p>If this activity stays active over 1 minute, it will automatically go MainActivity.
 *
 * <p>- onCreate() Starts the Activity on the Android system
 *
 * <p>- getNameActivityXMLViews() Initialize the views on the screen
 *
 * <p>- whatDoINeedHelpBtnClicked() Button takes it to the next activity
 *
 * <p>- hideKeyboardAfterTypingName() While EditText is on focus, checks clicks anywhere on view
 *
 * <p>- hideSoftKeyboard(Activity) Android Application keyboard hides from the screen
 *
 * <p>- changeActivityToMainActivity() After staying this screen for 1 min, it will move to Main
 * Activity screen
 *
 * <p>- onDestroy() When the Activity destroys, it will remove callbacks runnable
 *
 * @author Arnold Gihozo. Unless otherwise specified, all code was written by Arnold Gihozo.
 * @since 2020-01-20
 *     <p>Modified by Alvee Hassan Akash. (Documentation and Specific methods)
 */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {
  /**
   * Variables list
   *
   * <p>nameActivityXMLView - View - root view of this activity
   *
   * <p>userNameInput - EditText - Input field of taking user's name
   *
   * <p>DELAY_TIME_TO_START_MAIN_ACTIVITY - Integer - Constant variable of 1 min
   *
   * <p>MAIN_HANDLER - Handler - handles the runnable to send to another activity
   */
  private View nameActivityXMLView;

  private EditText userNameInput;
  private final Integer DELAY_TIME_TO_SHOW_ALERT_BOX_AND_CHECK_INACTIVE_SCREEN = 60000;
  private Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
  private Handler handler = new Handler();
  private Boolean isScreenBeingTouched;
  AlertDialog dialog;
  
  /**
   * When the program starts
   *
   * <p>1) assign the root view and name input field
   *
   * <p>2) initializing root view onclick listener for keyboard hiding method
   *
   * <p>3) Start a Runnable to go to main Activity
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);

    getNameActivityXMLViews();
    hideKeyboardAfterTypingName();

    isScreenBeingTouched = true;
  
    editTextInputFieldTypingListening();
    
    lastTimeTouchOnScreen();

    inActiveScreenAlertForClass.run();
    
    
    
  } // onCreate
  
  /**
   * Last task of this activity to happen before destroying it fully
   */
  @Override
  protected void onDestroy() {
    if (dialog != null && dialog.isShowing()) {
      dialog.dismiss();
    }
    handler.removeCallbacks(inActiveScreenAlertForClass);
    handler.removeCallbacks(closeAlertBox);
    super.onDestroy();
  }
  
  
  /**
   * Gets all the components on the activity screen to control touch on screen
   *
   * <p>by Alvee Hassan Akash
   */
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
   * @param view - View object - Button object in this scenario
   *     <p>by Alvee Hassan Akash
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

  /**
   * Hides Keyboard after user touches anywhere one the screen when EditText is on focus.
   *
   * <p>by Alvee Hassan Akash
   */
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
   *     <p>by Alvee Hassan Akash
   */
  private void hideSoftKeyboard(Activity activity) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
  } // hideSoftKeyboard

  /**
   * Goes back to Main Activity screen after 1 min Assuming, mostly user can input their name in one
   * min
   *
   * <p>Code is taken from - (Modified by Alvee)
   * https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
   * Document access date is 18-Jan-2020
   *
   * <p>by Alvee Hassan Akash
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
  


  
  /**
   * Disabling the back button, so user can not go back to Main Screen just by clicking it
   */
  @Override
  public void onBackPressed() {
    // disable the android app back button
  }
} // NameActivity
