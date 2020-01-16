/**
 * ConfirmationActivity.java
 *
 * <p>This Class is the confirmation view on the tablet. It shows the user that his/her request has
 * been accepted by Camrose Open Door.
 *
 * <p>It also gives User a Feedback by thanking him/her on the screen.
 *
 * <p>Go to different activity after a certain amount of time code taken from - (modified by Alvee)
 * https://stackoverflow.com/questions/6304035/how-to-display-an-activity-automatically-after-5-seconds
 * Document was access on 10-Jan-2020
 *
 * Methods names are-
 *
 * - onCreate(Bundle savedInstanceState)
 *    Start of Activity
 * - setUserCredentials()
 *    Get user inputted values here
 * - setThanksUser()
 *    Set Thanks user to screen
 * - setConfirmationMessage()
 *    Set confirmation message that request has been accepted
 * - buildThanksUserMessage(String userName)
 *    Building String message from user name and resources String
 * - startMainActivity()
 *    After 10 seconds this activity will send user to Main Activity
 *
 * @author Alvee Hassan Akash
 * @version 1.0
 * @since 2020-01-10
 */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;

public class ConfirmationActivity extends AppCompatActivity {

  /**
   * Variable list
   *
   * <p>thanksUser - String value confirmationMessage - String value userName - String value - Store
   * user name services - String value - Store what type of services user chose workers - String
   * value - Store who they chose to talk emotions - String value - Store how they are feeling today
   * localTime - LocalTime value - Store system current time
   * delayTimeToStartActivity - Integer value - 10 seconds
   */
  TextView thanksUser, confirmationMessage;

  String userName, services, workers, emotions;
  LocalTime localTime;
  
  Integer delayTimeToStartActivity = 100000;
  
  
  
  
  
  
  
  
  /**
   * When the program starts. 1) Set up 'userName' variable to given name from 'Services/Emotion
   * Activity' class. 2) Shows the confirmation message string on the screen with userName. 3) This
   * activity stays active for 10 seconds and goes back to 'Main Activity' class.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);

    // Set Thank User to the screen
    setThanksUser();

    // Set general Confirmation message
    setConfirmationMessage();
  
  
  
  
  

  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // Go back to Main Activity screen
    startMainActivity();
  } // onCreate

  /** Sets userName, services, workers, emotions from User class */
  private void setUserCredentials() {
    userName = User.userName;
    services = User.serviceName;
    workers = User.workerName;
    emotions = User.emotionName;
    localTime = User.localTime;
  }

  /**
   * Gets thanksUser textView from XML, Sets thanks user message with userName variable on the screen
   */
  private void setThanksUser() {
    setUserCredentials();
    thanksUser = findViewById(R.id.thanksUser);
    thanksUser.setText(buildThanksUserMessage(userName));
  }

  /** Gets confirmationMessage textView from XML Sets confirmationMessage message on the screen */
  private void setConfirmationMessage() {
    confirmationMessage = findViewById(R.id.confirmationMessage);
    confirmationMessage.setText(R.string.confirmationMessage);
  }

  /**
   * Takes the userName, adds userName to resource string with '!'
   *
   * @param userName - String value - user inputted name from Name Activity
   * @return message - String value - Build a string in format of 'Thanks "Benjamin" !'
   */
  private String buildThanksUserMessage(String userName) {
    String confirmationMessage01 = getString(R.string.thanksUser);
    StringBuilder confirmationMessage = new StringBuilder(confirmationMessage01);
    confirmationMessage.append(userName);
    confirmationMessage.append(" !");
    String message = confirmationMessage.toString();
    return message;
  }

  /**
   * Starts MainActivity class in 10 seconds after showing Confirmation class
   */
  private void startMainActivity() {
    new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                final Intent goBackToMainActivity = new Intent(ConfirmationActivity.this, MainActivity.class);
                ConfirmationActivity.this.startActivity(goBackToMainActivity);
                ConfirmationActivity.this.finish();
              }
            }, delayTimeToStartActivity);
  } // startMainActivity
  
  
} // ConfirmationActivity
