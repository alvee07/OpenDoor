package com.example.opendoorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class   MainActivity extends AppCompatActivity {

  private String password = "the0p3nD0or";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    noInternetConnectionView();

    Button admin = findViewById(R.id.admin);

    admin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //openAdminDialog();
      }
    });
  } // onCreate



  /**
   * Method to open the admin login dialog box
   * Takes in an entered password, and if the password is incorrect it will refresh the dialog box
   * by Alex
   */
  private void openAdminDialog(){
    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    View subView = inflater.inflate(R.layout.password, null);
    final EditText subEditText = subView.findViewById(R.id.dialogEditText);

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
    //finish();
  } // checkInBtnClicked
  
  
  /**
   * Takes the context to notify that if there is an internet connection
   *
   * @param context - activity
   * @return Boolean Value - internet connection true or false
   * Code is taken from -
   * https://developer.android.com/training/basics/network-ops/managing
   *
   * Modified by Alvee
   */
  public boolean isThereAnyInternetConnection(Context context) {
    try {
      ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (cm != null) {
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  } // isThereAnyInternetConnection

  /**
   * If there is no Internet Connection, it will not show on the screen the buttons to go forward
   * by Alvee
   */
  private void noInternetConnectionView(){
    if(!isThereAnyInternetConnection(this)){
      TextView internetConnection = findViewById(R.id.internetConnection);
      internetConnection.setText(R.string.internetConnection);
      internetConnection.setVisibility(View.VISIBLE);
      TextView question = findViewById(R.id.Question);
      Button checkIn = findViewById(R.id.checkIn);
      question.setVisibility(View.INVISIBLE);
      checkIn.setVisibility(View.INVISIBLE);
    } // if

  } // noInternetConnectionView


} // MainActivity
