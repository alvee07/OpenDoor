/**
 * ConfirmationActivity.java
 *
 * <p>This Class is the confirmation view on the tablet. It shows the user that his/her request has
 * been accepted by Camrose Open Door.
 *
 * <p>It also gives User a Feedback by thanking him/her on the screen.
 *
 * <p>Go to different activity after a certain amount of time code taken from - (modified by Alvee)
 * https://stackoverflow.com/questions/6304035/how-to-display-an-activity-automatically-after-5-seconds
 * Document access date is 10-Jan-2020
 *
 * Methods names are-
 *
 * - onCreate(Bundle savedInstanceState)
 *    Start of Activity
 * - setUserCredentials()
 *    Get user inputted values here
 * - setThanksUser()
 *    Set Thanks user to screen
 * - setConfirmationMessage()
 *    Set confirmation message that request has been accepted
 * - buildThanksUserMessage(String userName)
 *    Building String message from user name and resources String
 * - startMainActivity()
 *    After 10 seconds this activity will send user to Main Activity
 *
 * @author Alvee Hassan Akash
 * @version 1.0
 * @since 2020-01-10
 */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.example.opendoorapp.Sending_GMail_Files.MailSender;

import java.util.ArrayList;
import java.util.List;


public class ConfirmationActivity extends AppCompatActivity {

  /**
   * Variable list
   *
   * <p>thanksUser - String value confirmationMessage - String value userName - String value - Store
   * user name services - String value - Store what type of services user chose workers - String
   * value - Store who they chose to talk emotions - String value - Store how they are feeling today
   * localTime - LocalTime value - Store system current time DELAY_TIME_TO_START_MAIN_ACTIVITY - Integer
   * value - 10 seconds
   */
  private TextView thanksUser, confirmationMessage, emailSendFailed;

  private String userNameForEmail, servicesForEmail, workerForEmail, emotionsForEmail;
  //private LocalTime localTime; // future implementation

  private final Integer DELAY_TIME_TO_START_MAIN_ACTIVITY_FROM_CONFIRMATION_PAGE = 10000;

  private Handler MAIN_HANDLER_ON_THIS_ACTIVITY = new Handler(Looper.getMainLooper());

  /**
   * When the program starts. 1) Set up 'userName' variable to given name from 'Services/Emotion
   * Activity' class. 2) Shows the confirmation message string on the screen with userName. 3) This
   * activity stays active for 10 seconds and goes back to 'Main Activity' class.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);
    setUserCredentials();


    List<String> email = new ArrayList<>(ServicesActivity.selectedEmailList);

    // JUST NOT SENDING UNNECESSARY EMAILS TO CAMROSE OPEN DOOR PEOPLE
    //listOfEmailSendingOverTheNetwork(email);


    startMainActivity();

  } // onCreate

  /**
   * Sends Email to Appropriate Staff when User Check-In
   *
   * @param recipients - List (String) of Email addresses email needs to send
   */
  private void listOfEmailSendingOverTheNetwork(List<String> recipients) {

    for (String temp : recipients) {
      sendGMailToStaffs(temp, emailBodyCreation(), emailSubjectCreation());
    }
  }


  /**
   * Higher Method of sub-methods what to show on the screen, if Email is sent
   */
  private void setEmailSendTextOnScreen() {
    // Set Thank User to the screen
    setThanksUser();

    // Set general Confirmation message
    setConfirmationMessage();

    // Go back to Main Activity screen
    startMainActivity();
  } // emailSendingOverTheNetwork

  /** Sets userName, services, workers, emotions from User class */
  private void setUserCredentials() {
    userNameForEmail = User.userName;
    servicesForEmail = User.serviceName;
    workerForEmail = User.workerName;
    emotionsForEmail = User.emotionName;
    //localTime = User.localTime;


  } // setUserCredentials

  /**
   * Gets thanksUser textView from XML, Sets thanks user message with userName variable on the
   * screen
   */
  private void setThanksUser() {
    setUserCredentials();
    thanksUser = findViewById(R.id.thanksUser);
    thanksUser.setText(buildThanksUserMessage(userNameForEmail));
  }

  /** Gets confirmationMessage textView from XML Sets confirmationMessage message on the screen */
  private void setConfirmationMessage() {
    confirmationMessage = findViewById(R.id.confirmationMessage);
    confirmationMessage.setText(R.string.confirmationMessage);
  } // setConfirmationMessage

  /**
   * Takes the userName, adds userName to resource string with '!'
   *
   * @param userName - String value - user inputted name from Name Activity
   * @return message - String value - Build a string in format of 'Thanks "Benjamin" !'
   */
  private String buildThanksUserMessage(String userName) {
    String confirmationMessage01 = getString(R.string.thanksUser);
    StringBuilder confirmationMessage = new StringBuilder(confirmationMessage01);
    confirmationMessage.append(userName);
    confirmationMessage.append(" !");
    String message = confirmationMessage.toString();
    return message;
  } // buildThanksUserMessage

  /**
   * Sets Thanks user and show 'Please Contact Front Desk', if email send is failed
   */
  private void setEmailSendFailedTextOnScreen() {
    setThanksUser();
    emailSendFailed = findViewById(R.id.emailSendFailed);
    emailSendFailed.setText(R.string.emailSendFailed);
    startMainActivity();
  } // setEmailSendFailedTextOnScreen


  /** Starts MainActivity class in 10 seconds after showing Confirmation class */
  private void startMainActivity() {
    MAIN_HANDLER_ON_THIS_ACTIVITY
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                final Intent goBackToMainActivity =
                    new Intent(ConfirmationActivity.this, MainActivity.class);
                ConfirmationActivity.this.startActivity(goBackToMainActivity);
                ConfirmationActivity.this.finish();
              }
            },
                DELAY_TIME_TO_START_MAIN_ACTIVITY_FROM_CONFIRMATION_PAGE);
  } // startMainActivity

  @Override
  protected void onDestroy() {
    MAIN_HANDLER_ON_THIS_ACTIVITY.removeCallbacksAndMessages(null);
    super.onDestroy();
  }


  /**
   * Runs a new thread, thread sends an email.
   * If the email is sent, it will show 'Thanks user and Confirmation message'
   * If the email is NOT sent, it will show 'Thanks user and Contact Front Desk'
   *
   * @param oneRecipients - String value - Email address of the person is getting the email
   * @param emailBody - String value - Email's body what to show on the actual Email
   */
  private void sendGMailToStaffs(final String oneRecipients, final String emailBody, final String emailSubject) {
    new Thread(new Runnable() {
              @Override
              public void run() {
                try {
                  MailSender sender = new MailSender();
                  final Boolean isEmailSent =
                      sender.sendMail(emailSubject, emailBody, oneRecipients);
                  whatToShowOnScreenBasedOnEmailSend(isEmailSent);
                } catch (Exception e) {
                  Log.e("SendMail", e.getMessage(), e);
                }
              } // run
            }) // sendGMailToStaffs
        .start();
  } // sendGMailToStaffs

  /**
   * Runs a thread that shows what to show on screen based on Email Sent or Not
   *
   * @param isEmailSentFromHigherMethod - Boolean Value - email sent true or false
   */
  private void whatToShowOnScreenBasedOnEmailSend(final Boolean isEmailSentFromHigherMethod) {
    MAIN_HANDLER_ON_THIS_ACTIVITY.post(
        new Runnable() {
          @Override
          public void run() {
            if (isEmailSentFromHigherMethod) setEmailSendTextOnScreen();
            else setEmailSendFailedTextOnScreen();
          } // run
        });// Runnable
  } // whatToShowOnScreenBasedOnEmailSend

  /**
   * Returns a String, Email Body which is formatted HTML, and contains all the info user inputted
   *
   * @return - String value - Email's body in HTML format
   */
  private String emailBodyCreation(){
    String message =
          "<h>"
              + userNameForEmail
              + " is here.</h3></br>"
              + "<h4>Service needs "
              + servicesForEmail
              + "</h4></br>"
              + "Feeling "
              + emotionsForEmail
              + "</br>";
    return message;
  } //emailBodyCreation

  /**
   * Returns a String, how the email subject is formatted.
   *
   * @return - String value - Email's subject
   */
  private String emailSubjectCreation() {
    if (User.serviceName == null) return "Youth " + userNameForEmail + " has checked-in to see " + workerForEmail;
    else return "Youth " + userNameForEmail + " has checked-in for " + User.serviceName;
  } // emailSubjectCreation


} // ConfirmationActivity
