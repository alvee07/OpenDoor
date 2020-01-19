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
  private final static String user = "camopnethedoor@gmail.com";
  private final static String password = "Alberta2020!";
  
  
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
    
    
    session = Session.getDefaultInstance(properties, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
      }
    });
    
    session.setDebug(true);
  }

  public synchronized Boolean sendMail(String subject, String body, String recipients) {
    MimeMessage message = new MimeMessage(session);
    DataHandler handler = new DataHandler(
                new ByteArrayDataSource(body.getBytes(),
                        "text/html; charset=utf-8"));
    
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
