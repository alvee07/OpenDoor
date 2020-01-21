package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class EmotionsCheck extends AppCompatActivity {

  private String selectedEmotion;
  private Boolean emotionSelected;

  Button happyButton;
  Button contentButton;
  Button neutralButton;
  Button upsetButton;
  Button sadButton;
  Button angryButton;
  Button submitButton;
  Button underInfluenceButton;
  Button overwhelmedButton;
  Button depressedButton;
  private final Integer DELAY_TIME_TO_START_MAIN_ACTIVITY = 60000;
  private Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emotions_check);

    selectedEmotion = "null";
    emotionSelected = false;

    happyButton = findViewById(R.id.happyButton);
    contentButton = findViewById(R.id.contentButton);
    neutralButton = findViewById(R.id.neutralButton);
    upsetButton = findViewById(R.id.upsetButton);
    sadButton = findViewById(R.id.sadButton);
    angryButton = findViewById(R.id.angryButton);
    underInfluenceButton = findViewById(R.id.under_influence);
    overwhelmedButton = findViewById(R.id.overwhelmedButton);
    depressedButton = findViewById(R.id.depressedButton);
    submitButton = findViewById(R.id.emotionsCheckSubmit);

    happyButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            selectedEmotion = "happy";
            emojiSelected();

            if (v == happyButton) {

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji_selected));

              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    contentButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            selectedEmotion = "content";
            emojiSelected();

            if (v == contentButton) {

              contentButton.setBackground(getDrawable(R.drawable.content_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    neutralButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            selectedEmotion = "neutral";
            emojiSelected();

            if (v == neutralButton) {

              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    upsetButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            selectedEmotion = "upset";
            emojiSelected();

            if (v == upsetButton) {

              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    sadButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            selectedEmotion = "sad";
            emojiSelected();

            if (v == sadButton) {

              sadButton.setBackground(getDrawable(R.drawable.sad_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    angryButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            selectedEmotion = "angry";
            emojiSelected();

            if (v == angryButton) {

              angryButton.setBackground(getDrawable(R.drawable.angry_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    /** Modified by Alex Taylor */
    underInfluenceButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            selectedEmotion = "Under Influence";
            emojiSelected();

            if (v == underInfluenceButton) {

              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    depressedButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            selectedEmotion = "Depressed";
            emojiSelected();

            if (v == depressedButton) {

              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji));
            }
          }
        });

    overwhelmedButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            selectedEmotion = "Overwhelmed";
            emojiSelected();

            if (v == overwhelmedButton) {

              overwhelmedButton.setBackground(getDrawable(R.drawable.overwhelmed_emoji_selected));

              happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
              contentButton.setBackground(getDrawable(R.drawable.content_emoji));
              neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
              upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
              sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
              angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
              underInfluenceButton.setBackground(getDrawable(R.drawable.woozy_emoji));
              depressedButton.setBackground(getDrawable(R.drawable.depressed_emoji));
            }
          }
        });

    changeActivityToMainActivity();
  } // onCreate

  /** Used if an emotion button is pressed by Alex */
  public void emojiSelected() {
    emotionSelected = true;
  }

  
  /** Starting here, everything is written by Alvee Hassan Akash */

  
  /**
   * Takes user to next Activity - 'Confirmation Activity'
   *
   * @param view - View object - Button object in this scenario by Alvee Hassan Akash
   */
  public void submitBtnClicked(View view) {
    User.emotionName = selectedEmotion;
    Intent name = new Intent(EmotionsCheck.this, ConfirmationActivity.class);
    if (!emotionSelected) {
      Toast.makeText(getApplicationContext(), "Please Select an Emotion", Toast.LENGTH_SHORT).show();
      return;
    } else {
      startActivity(name);
      // finish();
    }
  } // continueBtnClicked

  /**
   * Goes back to Main Activity screen after 1 min Assuming, mostly user can input their name in one
   * min
   *
   * <p>by Alvee Hassan Akash
   */
  private void changeActivityToMainActivity() {
    MAIN_HANDLER.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            final Intent goBackToMainActivity = new Intent(EmotionsCheck.this, MainActivity.class);
            EmotionsCheck.this.startActivity(goBackToMainActivity);
            EmotionsCheck.this.finish();
          } // run
        },
        DELAY_TIME_TO_START_MAIN_ACTIVITY);
  } // changeActivityToMainActivity

  /** Last task of this activity to happen before destroying it fully */
  @Override
  protected void onDestroy() {
    MAIN_HANDLER.removeCallbacksAndMessages(null);
    super.onDestroy();
  } // onDestroy

  /** Disabling the back button, so user can not go back to Main Screen just by clicking it */
  @Override
  public void onBackPressed() {
    // disable the android app back button
  } // onBackPressed
}
