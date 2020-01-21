/** By Arnold Gihozo */
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


  private View nameActivityXMLView;
  private EditText userNameInput;
  private final Integer DELAY_TIME_TO_START_MAIN_ACTIVITY = 60000;
  private Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);
  
    getNameActivityXMLViews();
    hideKeyboardAfterTypingName();
  
    changeActivityToMainActivity();
    
  } // onCreate
  
  
  /**
   * Gets all the components on the activity screen to control touch on screen
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
   *
   * by Alvee
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
   * Goes back to Main Activity screen after 1 min
   * Assuming, mostly user can input their name in one min
   *
   * by Alvee
   *
   */
  private void changeActivityToMainActivity() {
    MAIN_HANDLER.postDelayed(new Runnable() {
      @Override
      public void run() {
        System.out.println("this is moving away ----------------------------------------------------------------");
        final Intent goBackToMainActivity =
                new Intent(NameActivity.this, MainActivity.class);
        NameActivity.this.startActivity(goBackToMainActivity);
        NameActivity.this.finish();
      }
    }, DELAY_TIME_TO_START_MAIN_ACTIVITY);
  } // changeActivityToMainActivity
  
  @Override
  protected void onDestroy() {
    MAIN_HANDLER.removeCallbacksAndMessages(null);
    super.onDestroy();
  }
} // NameActivity
