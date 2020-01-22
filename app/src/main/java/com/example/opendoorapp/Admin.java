package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class Admin extends AppCompatActivity {
  
  Timer timeout;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin);
    
    Button backButton = (Button) findViewById(R.id.backTo);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        goToHome();
      }
    });

  }


  /**
   * Method for setting a timer for the admin activity
   * @param milliseconds amount of time the timer will run
   * by Alex Taylor
   */
  private void startTimer(int milliseconds) {
    timeout = new Timer();
    timeout.schedule(new TimerTask() {
      @Override
      public void run() {
        goToHome();
      }
    }, milliseconds);
    
  }

  /**
   * Stops the timer if required from finishing its run
   * by Alex Taylor
   */
  private void stopTimer() {
    timeout.cancel();
  }

  /**
   * Method returns to the main page
   */
  private void goToHome(){
    finish();
  }
}
