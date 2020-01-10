package com.example.opendoorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String password = "hello";
    EditText passwordInput = new EditText(MainActivity.this);
    Button admin = (Button) findViewById(R.id.admin);
    admin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToAdmin = new Intent(getApplicationContext(), Admin.class);
        AlertDialog.Builder password = new AlertDialog.Builder(getApplicationContext());
        password.setTitle("Admin ");
        password.setMessage("Please Enter Admin Password");
        startActivity(goToAdmin);
      }
    });
  }
}
