package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmotionsCheck extends AppCompatActivity {

  private String selectedEmotion;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emotions_check);

    selectedEmotion = "null";

    Button happyButton = findViewById(R.id.happyButton);
    Button contentButton = findViewById(R.id.contentButton);
    Button neutralButton = findViewById(R.id.neutralButton);
    Button upsetButton = findViewById(R.id.upsetButton);
    Button sadButton = findViewById(R.id.sadButton);
    Button angryButton = findViewById(R.id.angryButton);
    Button submitButton = findViewById(R.id.submitButton);


    happyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
  }


}
