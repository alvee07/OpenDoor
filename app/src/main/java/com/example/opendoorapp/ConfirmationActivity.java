
/**
 * ConfirmationActivity.java
 *
 * <p>This Class is the confirmation view on the tablet. It shows the user that his/her request
 * has been accepted by Camrose Open Door.
 *
 * option 1 - https://coderanch.com/t/674882/Automatically-starting-activity-time
 * option 2 - https://stackoverflow.com/questions/6304035/how-to-display-an-activity-automatically-after-5-seconds
 *
 * @version 1.0
 * @since 2020-01-10
 */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationActivity extends AppCompatActivity {
  
  /**
   * Variable list
   *
   * thanksUser - String value
   * confirmationMessage - String value
   * userName - String value - Store user name
   * services - String value - Store what type of services user chose
   * workers - String value - Store who they chose to talk
   * emotions - String value - Store how they are feeling today
   */
  TextView thanksUser, confirmationMessage;
  String userName, services, workers, emotions;
  
  /**
   * When the program starts.
   * 1) Set up 'userName' variable to given name from 'Services/Emotion Activity' class.
   * 2) Shows the confirmation message string on the screen with userName.
   * 3) This activity stays active for 10000000000000000 seconds and goes back to 'Main Activity' class.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);
    
    
  
    setThanksUser();
    setConfirmationMessage();
    
    startMainActivity();
  
  
    Toast.makeText(getApplicationContext(),"this is confirmation page",Toast.LENGTH_SHORT).show();
  
  
  } // onCreate
  
  
  
  
  /**
   * Sets userName, services, workers, emotions from previous activity - (Services/Emotion Activity)
   */
  private void setUsercredentials(){
    if (getIntent().hasExtra("USERNAME") &&
            getIntent().hasExtra("SERVICES") &&
            getIntent().hasExtra("WORKERS") &&
            getIntent().hasExtra("EMOTIONS")){
  
      userName = getIntent().getStringExtra("USERNAME");
      services = getIntent().getStringExtra("SERVICES");
      workers = getIntent().getStringExtra("WORKERS");
      emotions = getIntent().getStringExtra("EMOTIONS");

    } // if
  //for testing
    userName = "AAB";
  }
  
  
  
  /**
   * Gets thanksUser textView from XML
   * Sets thanks user message with userName variable on the screen
   */
  private void setThanksUser(){
    setUsercredentials();
    thanksUser = findViewById(R.id.thanksUser);
    thanksUser.setText(buildThanksUserMessage(userName));
  }
  
  /**
   * Gets confirmationMessage textView from XML
   * Sets confirmationMessage message on the screen
   */
  private void setConfirmationMessage(){
    confirmationMessage = findViewById(R.id.confirmationMessage);
    confirmationMessage.setText(R.string.confirmationMessage);
  }
  
  /**
   * Takes the userName, adds userName to resource string with !
   * @param userName - String value
   * @return message - String value
   */
  public String buildThanksUserMessage(String userName){
    String confirmationMessage01 = getString(R.string.thanksUser);
    StringBuilder confirmationMessage = new StringBuilder(confirmationMessage01);
    confirmationMessage.append(userName);
    confirmationMessage.append(" !");
    String message = confirmationMessage.toString();
    return message;
  }
  
  /**
   * Starts MainActivity class in 10000000 seconds
   */
  public void startMainActivity(){
  
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        final Intent goBackToMainActivity = new Intent(ConfirmationActivity.this, MainActivity.class);
        ConfirmationActivity.this.startActivity(goBackToMainActivity);
        ConfirmationActivity.this.finish();
      }
    }, 100000);
  } // startMainActivity
  
  
} // ConfirmationActivity
