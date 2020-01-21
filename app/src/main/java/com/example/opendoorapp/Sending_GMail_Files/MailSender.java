/**
 * This file is taken from a web page for sending GMail email.
 *
 * <p>I have changed the most of the properties of this file according to set up GMail settings.
 *
 * <p>If the email is sent successfully, it will return a Boolean value.
 *
 * <p>ONE IMPORTANT NOTE: this file contains GMail AUTHENTICATION Email ID and Password.
 *
 * <p>Code is taken from -
 *
 * <p>https://pepipost.com/tutorials/send-email-in-android-using-javamail-api/
 *
 * <p>Document was accessed on 2020-10-01
 *
 * <p>Collected by Alvee Hassan Akash
 *
 * <p>Modified by Alvee Hassan Akash
 */
package com.example.opendoorapp.Sending_GMail_Files;

import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends javax.mail.Authenticator {
  private Session session;
  private static final String user = "camopnethedoor@gmail.com";
  private static final String password = "Alberta2020!";

  static {
    Security.addProvider(new JSSEProvider());
  }

  public MailSender() {

    // sets SMTP server properties
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");

    session =
        Session.getDefaultInstance(
            properties,
            new Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
              }
            });

    // THIS IS TO SEE WHAT IS GOING ON IN THE BACKGROUND WHILE WE SEND EMAIL
    // OR OTHER SCENARIO OF THE SENDING EMAIL THROUGH THE NETWORK
    // UNCOMMENT TO SEE THE DEBUG RESULT
    // session.setDebug(true);
  }

  public synchronized Boolean sendMail(String subject, String body, String recipients) {
    MimeMessage message = new MimeMessage(session);
    DataHandler handler =
        new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/html; charset=utf-8"));

    try {
      message.setSender(new InternetAddress(user));
      message.setSubject(subject);
      message.setDataHandler(handler);

      message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

      Transport transport = session.getTransport("smtp");
      transport.connect(user, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      return true;
    } // try
    catch (Exception e) {
      e.printStackTrace();
      return false;
    } // catch
  } // sendMail
}
