
/**
 * ConfirmationActivity.java
 *
 * <p>This Class is the confirmation view on the tablet. It shows the user that his/her request
 * has been accepted by Camrose Open Door.
 *
 * option 1 - https://coderanch.com/t/674882/Automatically-starting-activity-time
 * option 2 - https://stackoverflow.com/questions/6304035/how-to-display-an-activity-automatically-after-5-seconds
 *
 * @version 1.0
 * @since 2020-01-10
 */
package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfirmationActivity extends AppCompatActivity {
  
  /**
   * Variable list
   *
   * thanksUser - String value
   * confirmationMessage - String value
   * userName - String value - Store user name
   * services - String value - Store what type of services user chose
   * workers - String value - Store who they chose to talk
   * emotions - String value - Store how they are feeling today
   * localTime - LocalTime value - Store system current time
   */
  TextView thanksUser, confirmationMessage;
  String userName, services, workers, emotions;
  LocalTime localTime;
  
  
  Session session = null;

  
  
  final String rec = "highpressure93@gmail.com";
  final String subject = "First subject";
  final String textMessage = "This is one more try";
  
  
  
  /**
   * When the program starts.
   * 1) Set up 'userName' variable to given name from 'Services/Emotion Activity' class.
   * 2) Shows the confirmation message string on the screen with userName.
   * 3) This activity stays active for 10000000000000000 seconds and goes back to 'Main Activity' class.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);
    
    
  
    setThanksUser();
    setConfirmationMessage();
    
    startMainActivity();
  
  

    
    
  
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port", "465");
    
    session = Session.getDefaultInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("camopenthedoor@gmail.com", "openthedoor!");
      }
    });
    
    RetrieveFeedTask task = new RetrieveFeedTask();
    task.execute();
    
    
    
    
  
  
  
  
  
  
  
  
    //Toast.makeText(getApplicationContext(),"You will be moved to Main Activity page pretty soon",Toast.LENGTH_LONG).show();
  
  
  } // onCreate
  
  //port 587
  
  //
  class RetrieveFeedTask extends AsyncTask<String, Void, String>{
  
    @Override
    protected String doInBackground(String... strings) {
    
      try {
      
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("camopenthedoor@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
        message.setSubject(subject);
        message.setContent(textMessage, "text/html; charset=utf-8");
  
        Toast.makeText(getApplicationContext(),"Maybe Before Transport", Toast.LENGTH_SHORT).show();
  
        Transport.send(message);
        Toast.makeText(getApplicationContext(),"Maybe After Transport", Toast.LENGTH_SHORT).show();
  
  
      } catch (Exception e){
        e.printStackTrace();
      }
    
      return null;
    }
  
    @Override
    protected void onPostExecute(String result){
      Toast.makeText(getApplicationContext(),"Maybe On Post Execute", Toast.LENGTH_SHORT).show();
    }
  
  
  }
  
  
  /**
   * Sets userName, services, workers, emotions from previous activity - (Services/Emotion Activity)
   */
  private void setUserCredentials(){
    userName = User.userName;
    services = User.serviceName;
    workers = User.workerName;
    emotions = User.emotionName;
    localTime = User.localTime;
    // for testing
    userName = "AAB";
  }
  /**
   * Gets thanksUser textView from XML
   * Sets thanks user message with userName variable on the screen
   */
  private void setThanksUser(){
    setUserCredentials();
    thanksUser = findViewById(R.id.thanksUser);
    thanksUser.setText(buildThanksUserMessage(userName));
  }
  
  /**
   * Gets confirmationMessage textView from XML
   * Sets confirmationMessage message on the screen
   */
  private void setConfirmationMessage(){
    confirmationMessage = findViewById(R.id.confirmationMessage);
    confirmationMessage.setText(R.string.confirmationMessage);
  }
  
  /**
   * Takes the userName, adds userName to resource string with '!'
   * @param userName - String value
   * @return message - String value
   */
  public String buildThanksUserMessage(String userName){
    String confirmationMessage01 = getString(R.string.thanksUser);
    StringBuilder confirmationMessage = new StringBuilder(confirmationMessage01);
    confirmationMessage.append(userName);
    confirmationMessage.append(" !");
    String message = confirmationMessage.toString();
    return message;
  }
  
  /**
   * Starts MainActivity class in 1000000000000 seconds
   */
  public void startMainActivity(){
  
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        final Intent goBackToMainActivity = new Intent(ConfirmationActivity.this, MainActivity.class);
        ConfirmationActivity.this.startActivity(goBackToMainActivity);
        ConfirmationActivity.this.finish();
      }
    }, 10000);
  } // startMainActivity
  
  
} // ConfirmationActivity
