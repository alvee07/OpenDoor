package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmotionsCheck extends AppCompatActivity {

  private String selectedEmotion;


  Button happyButton;
  Button contentButton;
  Button neutralButton;
  Button upsetButton;
  Button sadButton;
  Button angryButton;
  Button submitButton;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emotions_check);

    selectedEmotion = "null";

    happyButton = findViewById(R.id.happyButton);
    contentButton = findViewById(R.id.contentButton);
    neutralButton = findViewById(R.id.neutralButton);
    upsetButton = findViewById(R.id.upsetButton);
    sadButton = findViewById(R.id.sadButton);
    angryButton = findViewById(R.id.angryButton);
    submitButton = findViewById(R.id.submitButton);


    happyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "happy";

        happyButton.setPressed(true);
        checkOtherButtons(selectedEmotion);
      }
    });

    contentButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "content";

        contentButton.setPressed(true);
        checkOtherButtons(selectedEmotion);
      }
    });

    neutralButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "neutral";

        neutralButton.setPressed(true);
        checkOtherButtons(selectedEmotion);
      }
    });

    upsetButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "upset";

        upsetButton.setPressed(true);
        checkOtherButtons(selectedEmotion);
      }
    });

    sadButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "sad";

        sadButton.setPressed(true);
        checkOtherButtons(selectedEmotion);
      }
    });

    angryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "angry";

        angryButton.setPressed(true);
        checkOtherButtons(selectedEmotion);
      }
    });

    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


      }
    });

  }//onCreate


}
