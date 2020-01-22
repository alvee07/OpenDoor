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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
  private final Integer DELAY_TIME_TO_START_MAIN_ACTIVITY = 60000;
  private Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

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

    changeActivityToMainActivity();
  } // onCreate
  
  
  /**
   *
   * Starting here, everything is written by Alvee Hassan Akash
   *
   */
  
  
  /**
   * Gets all the components on the activity screen to control touch on screen
   *
   * <p>by Alvee Hassan Akash
   */
  private void getNameActivityXMLViews() {
    nameActivityXMLView = findViewById(R.id.nameActivityXML);
    userNameInput = findViewById(R.id.userName);
  } // getNameActivityXMLViews

  /**
   * Checks the EditText input empty or not, then takes it value and stores it in the User class for
   * using email purpose. Also Takes user to next Activity - 'Services Activity'
   *
   * @param view - View object - Button object in this scenario
   *     <p>by Alvee Hassan Akash
   */
  public void whatDoINeedHelpBtnClicked(View view) {

    EditText textInputFromUser = findViewById(R.id.userName);
    String userNameFromInput = textInputFromUser.getText().toString().trim();
    if (userNameFromInput.isEmpty()) {
      Toast.makeText(getApplicationContext(), R.string.pleaseEnterYourName, Toast.LENGTH_SHORT)
          .show();
      return;
    } // if
    User.userName = userNameFromInput;

    Intent goToNextActivity = new Intent(NameActivity.this, ServicesActivity.class);
    NameActivity.this.startActivity(goToNextActivity);
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
   * <p>by Alvee Hassan Akash
   */
  private void changeActivityToMainActivity() {
    MAIN_HANDLER.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            final Intent goBackToMainActivity = new Intent(NameActivity.this, MainActivity.class);
            NameActivity.this.startActivity(goBackToMainActivity);
            NameActivity.this.finish();
          } // run
        },
        DELAY_TIME_TO_START_MAIN_ACTIVITY);
  } // changeActivityToMainActivity
  
  /**
   * Last task of this activity to happen before destroying it fully
   */
  @Override
  protected void onDestroy() {
    MAIN_HANDLER.removeCallbacksAndMessages(null);
    System.out.println("Name Activity is done forever-----------no handler");
    super.onDestroy();
  } // onDestroy
  
  /**
   * Disabling the back button, so user can not go back to Main Screen just by clicking it
   */
  @Override
  public void onBackPressed() {
    // disable the android app back button
  }
} // NameActivity
