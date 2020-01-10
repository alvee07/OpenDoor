
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
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ConfirmationActivity extends AppCompatActivity {
  /**
   * userName - String value - Store user name
   * */
  String userName;
  
  /**
   * When the program starts.
   * 1) Set up 'userName' variable to given name from 'Services/Emotion Activity' class.
   * 2) Shows the confirmation string with name.
   * 3) This activity stays active for 10000000000000000 seconds and go back to 'Main Activity' class.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);
    
    // getting userName from
    userName = getIntent().getStringExtra("userName");
    

    // Start Main Activity in 5 seconds

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        final Intent goBackToMainActivity = new Intent(ConfirmationActivity.this, MainActivity.class);
        ConfirmationActivity.this.startActivity(goBackToMainActivity);
        ConfirmationActivity.this.finish();
      }
    }, 5000);
  
  
  
    Toast.makeText(getApplicationContext(),"this is confirmation page",Toast.LENGTH_SHORT).show();
  
  
  } // onCreate
  
  
} // ConfirmationActivity
