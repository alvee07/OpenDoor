package com.example.opendoorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class   MainActivity extends AppCompatActivity {

  private String password = "the0p3nD0or";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Button admin = (Button) findViewById(R.id.admin);

    admin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openAdminDialog();
      }
    });
  }

  /**
   * Method to open the admin login dialog box
   * Takes in an entered password, and if the password is incorrect it will refresh the dialog box
   * by Alex
   */
  private void openAdminDialog(){
    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    View subView = inflater.inflate(R.layout.password, null);
    final EditText subEditText = (EditText) subView.findViewById(R.id.dialogEditText);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(subView);
    AlertDialog adminDialog = builder.create();

    builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {

        if (subEditText.getText().toString().equals(password)){
          Intent goToAdmin = new Intent(getApplicationContext(), Admin.class);
          startActivity(goToAdmin);
        }
        else{
          openAdminDialog();
        }
      }
    });

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });

    builder.show();
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
