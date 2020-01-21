/**
 * User.java
 *
 * <p>This class contains the user information to store in an instant object of User.
 *
 * <p>In future, this can be a model of putting data into database.
 *
 * @author Benjamin Wilson
 * @version 1.0
 * @since 2020-01-10 Unless otherwise specified, all code was written by Benjamin Wilson
 *     <p>Modified by Alvee Hassan Akash (Documentation and Specific methods)
 */
package com.example.opendoorapp;

import java.time.LocalTime;

public class User {

  private String name;
  private String service;
  private String worker;
  private String emotion;
  private LocalTime timeInputted;

  User() {

    name = "No name";
    service = "Not Applicable";
    worker = "Not Applicable";
    emotion = "Not Applicable";
    timeInputted = timeInputted.now();
  } // User defaultConstructor

  User(String inputName) {

    name = inputName;
    service = "Not Applicable";
    worker = "Not Applicable";
    emotion = "Not Applicable";
    timeInputted = timeInputted.now();
  }

  public void setName(String userName) {
    this.name = userName;
  }

  public void addService(String inputService) {

    service = inputService;
  } // addService

  public void addWorker(String inputWorker) {

    worker = inputWorker;
  }

  public void addEmotion(String inputEmotion) {

    emotion = inputEmotion;
  }

  public String getName() {

    return name;
  }

  public String getService() {

    return service;
  }

  public String getWorker() {

    return worker;
  }

  public String getEmotion() {

    return emotion;
  }

  public String getTime() {

    return timeInputted.toString();
  }

  /**
   * These static variables are used for storing data throughout the application run time.
   *
   * <p>These variables are also re-initialize to null after use of them once
   *
   * <p>By Alvee Hassan Akash
   */
  public static String userName;
  public static String serviceName;
  public static String workerName;
  public static String emotionName;
  public static LocalTime localTime;
} // User
