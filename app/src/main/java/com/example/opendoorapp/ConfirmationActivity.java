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
 * <p>Methods names are-
 *
 * <p>- onCreate(Bundle savedInstanceState) Start of Activity
 *
 * <p>- setUserCredentials() Get user inputted values here
 *
 * <p>- setThanksUser() Set Thanks user to screen
 *
 * <p>- setConfirmationMessage() Set confirmation message that request has been accepted
 *
 * <p>- buildThanksUserMessage(String userName) Building String message from user name and resources
 * String
 *
 * <p>- startMainActivity() After 10 seconds this activity will send user to Main Activity
 *
 * <p>- listOfEmailSendingOverTheNetwork() Sends one email at a time by iterating through the loop
 * of email list
 *
 * <p>- setEmailSendTextOnScreen() After successfully sending the email what to show on the screen
 *
 * <p>- setEmailSendFailedTextOnScreen() if the email does not send what to show on the screen
 *
 * <p>- onDestroy() End of this activity and clearing out the static variables that used to get data
 *
 * <p>- sendGMailToStaffs() Creates thread to send email to recipients
 *
 * <p>- whatToShowOnScreenBasedOnEmailSend() Boolean to check if the email is send or not
 *
 * <p>- emailBodyCreation() Email's body, what would email receiver will get on their screen
 *
 * <p>- emailSubjectCreation() Email's subject, what email receiver will see on their subject line
 *
 * <p>- firstLineOfEmailBody() First line of the email's body
 *
 * <p>- secondLineOfEmailBody() Second line of the email's body
 *
 * <p>- thirdLineOfEmailBody() Third line of the email's body
 *
 * <p>- clearStaticVariables() Re-initializing the static variables to NULL to re-use for next input
 *
 * @author Alvee Hassan Akash
 * @version 1.0
 * @since 2020-01-10 Unless otherwise specified, all code was written by Alvee Hassan Akash.
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
   * Variables list
   *
   * <p>thanksUser - String value - Show the User's name on the screen with thanks
   *
   * <p>confirmationMessage - String value - Show the confirmation of the email Sent
   *
   * <p>userNameForEmail - String value - Store user name
   *
   * <p>servicesForEmail - String value - Store what type of services user chose
   *
   * <p>workerForEmail - String value - Store who they chose to talk
   *
   * <p>emotionsForEmail - String value - Store how they are feeling today
   *
   * <p>DELAY_TIME_TO_START_MAIN_ACTIVITY - Integer - value - 10 seconds
   *
   * <p>MAIN_HANDLER_ON_THIS_ACTIVITY - Handler - handles the runnable to send to another activity
   */
  private TextView thanksUser, confirmationMessage, emailSendFailed;

  private String userNameForEmail, servicesForEmail, workerForEmail, emotionsForEmail;
  // private LocalTime localTime; // future implementation

  private final Integer DELAY_TIME_TO_START_MAIN_ACTIVITY_FROM_CONFIRMATION_PAGE = 15000;

  private Handler MAIN_HANDLER_ON_THIS_ACTIVITY = new Handler(Looper.getMainLooper());

  /**
   * When the program starts.
   *
   * <p>1) Set up 'userName' variable to given name from 'Services/Emotion Activity' class.
   *
   * <p>2) Shows the confirmation message string on the screen with userName.
   *
   * <p>3) This activity stays active for 10 seconds and goes back to 'Main Activity' class.
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
    // listOfEmailSendingOverTheNetwork(email);

    List<String> rec = new ArrayList<>();
    rec.add("camopnethedoor@gmail.com");

    listOfEmailSendingOverTheNetwork(rec);

    // remove when uncomment list<>
    //startMainActivity();
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
   * Higher Method of sub-methods what to show on the screen, if Email is sent successfully It
   * contains other three sub methods of setting TextView on the screen and getting back to Main
   * Activity in constant time
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
    // localTime = User.localTime;

  } // setUserCredentials

  /**
   * Gets thanksUser textView from XML, Sets thanks user message with userName variable on the
   * screen
   */
  private void setThanksUser() {
    setUserCredentials();
    thanksUser = findViewById(R.id.thanksUser);
    thanksUser.setText(buildThanksUserMessage(userNameForEmail));
    thanksUser.setPadding(30,10,30,10);
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

  /** Sets Thanks user and show 'Please Contact Front Desk', if email send is failed */
  private void setEmailSendFailedTextOnScreen() {
    setThanksUser();
    emailSendFailed = findViewById(R.id.emailSendFailed);
    emailSendFailed.setText(R.string.emailSendFailed);
    startMainActivity();
  } // setEmailSendFailedTextOnScreen

  /**
   * Handler creates a post delayed task to send back system to Main Activity screen
   *
   * <p>Handler posts this in 15 seconds delay time frame to Execute the code
   */
  private void startMainActivity() {
    MAIN_HANDLER_ON_THIS_ACTIVITY.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            clearStaticVariables();
            final Intent goBackToMainActivity =
                new Intent(ConfirmationActivity.this, MainActivity.class);
            ConfirmationActivity.this.startActivity(goBackToMainActivity);
            ConfirmationActivity.this.finish();
          }
        },
        DELAY_TIME_TO_START_MAIN_ACTIVITY_FROM_CONFIRMATION_PAGE);
  } // startMainActivity

  /** Last task of this activity to happen before destroying it fully */
  @Override
  protected void onDestroy() {
    MAIN_HANDLER_ON_THIS_ACTIVITY.removeCallbacksAndMessages(null);
    clearStaticVariables();
    super.onDestroy();
  } // onDestroy
  
  /** Disabling the back button, so user can not go back to Main Screen just by clicking it */
  @Override
  public void onBackPressed() {
    // disable the android app back button
  } // onBackPressed

  /**
   * Runs a new thread, thread sends an email. If the email is sent, it will show 'Thanks user and
   * Confirmation message' If the email is NOT sent, it will show 'Thanks user and Contact Front
   * Desk'
   *
   * @param oneRecipients - String value - Email address of the person is getting the email
   * @param emailBody - String value - Email's body what to show on the actual Email
   */
  private void sendGMailToStaffs(
      final String oneRecipients, final String emailBody, final String emailSubject) {
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  MailSender sender = new MailSender();
                  Boolean isEmailSent =
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
        }); // Runnable
  } // whatToShowOnScreenBasedOnEmailSend

  /**
   * Returns a String, Email Body which is formatted HTML, and contains all the info user inputted
   *
   * @return - String value - Email's body in HTML format
   */
  private String emailBodyCreation() {
    return firstLineOfEmailBody() + secondLineOfEmailBody() + thirdLineOfEmailBody();
  } // emailBodyCreation

  /**
   * Returns a String, how the email subject is formatted.
   *
   * @return - String value - Email's subject
   */
  private String emailSubjectCreation() {
    if (User.serviceName == null)
      return "OPEN_DOOR_APP  -  " + userNameForEmail + " has checked-in to see " + workerForEmail;
    else return "OPEN_DOOR_APP  -  " + userNameForEmail + " has checked-in for " + User.serviceName;
  } // emailSubjectCreation

  /**
   * Returns a String of user's name, how the first line of email body will look like in HTML format
   *
   * @return - String value - first line of email's body
   */
  private String firstLineOfEmailBody() {
    return "<h2>" + userNameForEmail + " is here.</h2></br>";
  } // firstLineOfEmailBody

  /**
   * Returns a String, how the second line of email body will look like in HTML format if the
   * services name is empty, then it will take worker's name for sure
   *
   * @return - String value - second line of email's body
   */
  private String secondLineOfEmailBody() {
    if (servicesForEmail == null) {
      return "<h3>" + userNameForEmail + " wants to see <u>" + workerForEmail + ".</u></h3></br>";
    } else return "<h3>" + userNameForEmail + " is looking for <u>" + servicesForEmail + ".</u></h3></br>";
  } // thirdLineOfEmailBody

  /**
   * Returns a String, how the third line of email body will look like in HTML format if the emotion
   * is empty, it will show null otherwise, show feelings name
   *
   * @return - String value - third line of email's body
   */
  private String thirdLineOfEmailBody() {
    if (emotionsForEmail == null) {
      return "";
    } else return "<h3>"+ userNameForEmail +" is feeling " + emotionsForEmail + "</h3></br>";
  } // thirdLineOfEmailBody

  /**
   * After using the static variables to send email, it will re-initialize them as null to re-use it
   * again for the next coming user's inputs.
   */
  private void clearStaticVariables() {
    User.userName = null;
    User.serviceName = null;
    User.workerName = null;
    User.emotionName = null;
  } // clearStaticVariables
} // ConfirmationActivity
