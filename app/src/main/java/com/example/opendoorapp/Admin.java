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
//        startTimer();
  }

//    public void onUserInteraction(){
//        super.onUserInteraction();
//        stopTimer();
//        startTimer();
//    }
  
  private void startTimer(int milliseconds) {
    timeout = new Timer();
    timeout.schedule(new TimerTask() {
      @Override
      public void run() {
        goToHome();
      }
    }, milliseconds);
    
  }
  
  private void stopTimer() {
    timeout.cancel();
  }
  
  private void goToHome(){
    finish();
  }
}
