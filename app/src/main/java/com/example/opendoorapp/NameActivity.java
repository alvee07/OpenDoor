/**
 * By Arnold Gihozo
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


public class NameActivity extends AppCompatActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);
  
  
    hideKeyboardAfterTypingName();

  
  
  
  
  

    
    
  }
  
  
  


  
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
      //return;
    }
    User.userName = userNameFromInput;
    Intent nextActivity = new Intent(NameActivity.this, ServicesActivity.class);
    startActivity(nextActivity);
    finish();
  } // checkInBtnClicked
  
  
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
   * Hides Keyboard after user touches anywhere one the screen when EditText is on focus.
   */
  private void hideKeyboardAfterTypingName(){
    
    View myView = findViewById(R.id.nameActivityXML);
    final EditText userNameInput = findViewById(R.id.userName);
    
    myView.setOnTouchListener(
            new View.OnTouchListener() {
              
              public boolean onTouch(View v, MotionEvent event) {
                if (userNameInput.hasFocus()) {
                  
                  Toast.makeText(getApplicationContext(), "what up", Toast.LENGTH_SHORT).show();
                  hideSoftKeyboard(NameActivity.this);
                  return true;
                }
                else
                  return false;
              }
            });
  }
  
  
}
