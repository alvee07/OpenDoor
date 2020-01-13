/**
 * By Arnold Gihozo
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;


public class NameActivity extends AppCompatActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_name);
    
    
  }
  
  
  
  /**
   * Checks the EditText input empty or not, then takes it value and stores it in the
   * User class for using email purpose.
   * Also Takes user to next Activity - 'Services Activity'
   *
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   */
  public void whatDoINeedHelpBtnClicked(View view){
    
    EditText textInputFromUser = findViewById(R.id.userName);
    String userNameFromInput = textInputFromUser.getText().toString().trim();
    if (userNameFromInput.isEmpty()){
      return;
    }
    User.userName = userNameFromInput;
    Intent nextActivity = new Intent(NameActivity.this, ServicesActivity.class);
    startActivity(nextActivity);
    finish();
  } // checkInBtnClicked
}
