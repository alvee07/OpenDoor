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
    submitButton = findViewById(R.id.emotionsCheckSubmit);


    happyButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){

        selectedEmotion = "happy";

        if(v == happyButton){

          happyButton.setBackground(getDrawable(R.drawable.happy_emoji_selected));

          contentButton.setBackground(getDrawable(R.drawable.content_emoji));
          neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
          upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
          sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
          angryButton.setBackground(getDrawable(R.drawable.angry_emoji));

        }

      }
    });

    contentButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){

        selectedEmotion = "content";

        if(v == contentButton){

          contentButton.setBackground(getDrawable(R.drawable.content_emoji_selected));

          happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
          neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
          upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
          sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
          angryButton.setBackground(getDrawable(R.drawable.angry_emoji));

        }

      }
    });

    neutralButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){

        selectedEmotion = "neutral";

        if(v == neutralButton){

          neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji_selected));

          happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
          contentButton.setBackground(getDrawable(R.drawable.content_emoji));
          upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
          sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
          angryButton.setBackground(getDrawable(R.drawable.angry_emoji));

        }

      }
    });

    upsetButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){

        selectedEmotion = "upset";

        if(v == upsetButton){

          upsetButton.setBackground(getDrawable(R.drawable.upset_emoji_selected));

          happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
          contentButton.setBackground(getDrawable(R.drawable.content_emoji));
          neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
          sadButton.setBackground(getDrawable(R.drawable.sad_emoji));
          angryButton.setBackground(getDrawable(R.drawable.angry_emoji));
        }

      }
    });

    sadButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){

        selectedEmotion = "sad";

        if(v == sadButton){

          sadButton.setBackground(getDrawable(R.drawable.sad_emoji_selected));

          happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
          contentButton.setBackground(getDrawable(R.drawable.content_emoji));
          neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
          upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
          angryButton.setBackground(getDrawable(R.drawable.angry_emoji));

        }

      }
    });

    angryButton.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){

        selectedEmotion = "angry";

        if(v == angryButton){

          angryButton.setBackground(getDrawable(R.drawable.angry_emoji_selected));

          happyButton.setBackground(getDrawable(R.drawable.happy_emoji));
          contentButton.setBackground(getDrawable(R.drawable.content_emoji));
          neutralButton.setBackground(getDrawable(R.drawable.neutral_emoji));
          upsetButton.setBackground(getDrawable(R.drawable.upset_emoji));
          sadButton.setBackground(getDrawable(R.drawable.sad_emoji));

        }

      }
    });


    
  } // onCreate


  /**
   * Takes user to next Activity - 'Confirmation Activity'
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   */
  public void submitBtnClicked(View view){
    Intent name = new Intent(EmotionsCheck.this, ConfirmationActivity.class);
    startActivity(name);
    finish();
    System.out.println("Alveeeeeeeejddhchchhdhdhdh");
  } // continueBtnClicked
}
