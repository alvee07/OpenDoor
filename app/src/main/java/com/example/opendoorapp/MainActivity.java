package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button admin = (Button) findViewById(R.id.admin);
    admin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToAdmin = new Intent(getApplicationContext(), Admin.class);
        startActivity(goToAdmin);
      }
    });
  }
  /**
   * Takes user to next Activity - 'Name Activity'
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   */
  public void checkInBtnClicked(View view){
    Intent name = new Intent(MainActivity.this, NameActivity.class);
    startActivity(name);
    finish();
  } // checkInBtnClicked
  
  
}
