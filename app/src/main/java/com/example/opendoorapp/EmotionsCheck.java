package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

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
        checkOtherButtons();
      }
    });

    contentButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "content";

        contentButton.setPressed(true);
        checkOtherButtons();
      }
    });

    neutralButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "neutral";

        neutralButton.setPressed(true);
        checkOtherButtons();
      }
    });

    upsetButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "upset";

        upsetButton.setPressed(true);
        checkOtherButtons();
      }
    });

    sadButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "sad";

        sadButton.setPressed(true);
        checkOtherButtons();
      }
    });

    angryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectedEmotion = "angry";

        angryButton.setPressed(true);
        checkOtherButtons();
      }
    });

//    submitButton.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//
//
//      }
//    });

  }//onCreate

  private void checkOtherButtons(){

    happyButton = findViewById(R.id.happyButton);
    contentButton = findViewById(R.id.contentButton);
    neutralButton = findViewById(R.id.neutralButton);
    upsetButton = findViewById(R.id.upsetButton);
    sadButton = findViewById(R.id.sadButton);
    angryButton = findViewById(R.id.angryButton);

    switch (selectedEmotion){

      case "happy":
        contentButton.setPressed(false);
        neutralButton.setPressed(false);
        upsetButton.setPressed(false);
        sadButton.setPressed(false);
        angryButton.setPressed(false);

      case "content":
        happyButton.setPressed(false);
        neutralButton.setPressed(false);
        upsetButton.setPressed(false);
        sadButton.setPressed(false);
        angryButton.setPressed(false);

      case "neutral":
        happyButton.setPressed(false);
        contentButton.setPressed(false);
        upsetButton.setPressed(false);
        sadButton.setPressed(false);
        angryButton.setPressed(false);

      case "upset":
        happyButton.setPressed(false);
        contentButton.setPressed(false);
        neutralButton.setPressed(false);
        sadButton.setPressed(false);
        angryButton.setPressed(false);

      case "sad":
        happyButton.setPressed(false);
        contentButton.setPressed(false);
        neutralButton.setPressed(false);
        upsetButton.setPressed(false);
        angryButton.setPressed(false);

      case "angry":
        happyButton.setPressed(false);
        contentButton.setPressed(false);
        neutralButton.setPressed(false);
        upsetButton.setPressed(false);
        sadButton.setPressed(false);
    }//switch

  }//checkOtherButtons
  /**
   * Takes user to next Activity - 'Confirmation Activity'
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   */
  public void continueBtnClicked(View view){
    Intent name = new Intent(EmotionsCheck.this, ConfirmationActivity.class);
    startActivity(name);
    finish();
    System.out.println("Alveeeeeeeejddhchchhdhdhdh");
  } // continueBtnClicked
}
