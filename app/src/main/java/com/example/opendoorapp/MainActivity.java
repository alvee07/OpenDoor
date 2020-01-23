/**
 * MainActivity.class
 *
 * <p>The class starts the application interface, it also has access to admin activity alert box.
 *
 * <p>It welcomes user and shows option to go to next activity to accept Camrose Open Door services.
 *
 * @author Alex. Unless otherwise specified, all code was written by Alex.
 * @since 2020-01-20
 *     <p>Modified by Alvee Hassan Akash. (Documentation and Specific methods)
 */
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

public class MainActivity extends AppCompatActivity {

  /**
   * Variable list
   *
   * <p>password - string value - this is a literal string for admin password
   */
  private String password = "the0p3nD0or";

  /**
   * When the program starts, 1) Checks if there is no internet connection to this application, it
   * will show on the screen that there is no internet connection and disable user check-In Button.
   * 2) Else, it will show all the functionality available on this screen. 3) Admin security
   * activity page is also hidden here. (Jessica Hutton knows it, hidden on the door)
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ConfirmationActivity.clearStaticVariables();

    noInternetConnectionView();

    Button admin = findViewById(R.id.admin);

    admin.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            openAdminDialog();
          }
        });
  } // onCreate

  /**
   * Method to open the admin login dialog box Takes in an entered password, and if the password is
   * incorrect it will refresh the dialog box by Alex
   */
  private void openAdminDialog() {
    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    View subView = inflater.inflate(R.layout.password, null);
    final EditText subEditText = subView.findViewById(R.id.dialogEditText);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(subView);
    AlertDialog adminDialog = builder.create();

    builder.setPositiveButton(
        "Login",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

            if (subEditText.getText().toString().equals(password)) {
              Intent goToAdmin = new Intent(getApplicationContext(), Admin.class);
              startActivity(goToAdmin);
            } else {
              openAdminDialog();
            }
          }
        });

    builder.setNegativeButton(
        "Cancel",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
        });

    builder.show();
  }
  
  
  
  
  /**
   *
   * Starting here, everything is written by Alvee Hassan Akash
   *
   */
  
  
  /**
   * Takes user to next Activity - 'Name Activity'
   *
   * @param view - View object - Button object in this scenario
   *     <p>by Alvee Hassan Akash
   */
  public void checkInBtnClicked(View view) {
    Intent goToNextActivity = new Intent(MainActivity.this, NameActivity.class);
    startActivity(goToNextActivity);
    finish();
  } // checkInBtnClicked

  /**
   * Takes the context to notify that if there is an internet connection
   *
   * @param context - activity
   * @return Boolean Value - internet connection true or false Code is taken from -
   *     https://developer.android.com/training/basics/network-ops/managing
   *     <p>Modified by Alvee Hassan Akash
   */
  public boolean isThereAnyInternetConnection(Context context) {
    try {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (cm != null) {
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  } // isThereAnyInternetConnection

  /**
   * If there is no Internet Connection, it will not show on the screen the buttons to go forward It
   * will disable and invisible views and show the 'Need Internet Connection' 'Contact front desk'
   *
   * <p>by Alvee Hassan Akash
   */
  private void noInternetConnectionView() {
    // this 'if' produces false statement = No internet connection
    if (!isThereAnyInternetConnection(this)) {
      // Need internet Connection showing here
      TextView internetConnection = findViewById(R.id.internetConnection);
      internetConnection.setText(R.string.internetConnection);
      internetConnection.setVisibility(View.VISIBLE);

      // Need Help from help-desk
      TextView needHelpFromHelpDesk = findViewById(R.id.needHelpFromHelpDesk);
      needHelpFromHelpDesk.setText(R.string.emailSendFailed);
      needHelpFromHelpDesk.setVisibility(View.VISIBLE);

      // Disable checkIn button and how can we help you today Views
      TextView question = findViewById(R.id.Question);
      Button checkIn = findViewById(R.id.checkIn);
      question.setVisibility(View.INVISIBLE);
      checkIn.setVisibility(View.INVISIBLE);
      checkIn.setEnabled(false);
    } // if
  } // noInternetConnectionView
  
  /** Disabling the back button, so user can not go back to Main Screen just by clicking it */
  @Override
  public void onBackPressed() {
    // disable the android app back button
  } // onBackPressed
} // MainActivity
