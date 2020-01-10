package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  
  }
  
  public void goToConfirmation(View view){
    Intent confirmation = new Intent(this, ConfirmationActivity.class);
    startActivity(confirmation);
  }
  
}
