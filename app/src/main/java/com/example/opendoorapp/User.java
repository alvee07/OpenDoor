package com.example.opendoorapp;

import java.time.LocalTime;

public class User {

  private String name;
  private String service;
  private String worker;
  private String emotion;
  private LocalTime timeInputted;

  User(){

    name = "No name";
    service = "Not Applicable";
    worker = "Not Applicable";
    emotion = "Not Applicable";
    timeInputted = timeInputted.now();


  }//User defaultConstructor

  User(String inputName){

    name = inputName;
    service = "Not Applicable";
    worker = "Not Applicable";
    emotion = "Not Applicable";
    timeInputted = timeInputted.now();
  }

  public void addService(String inputService){

    service = inputService;

  }//addService

  public void addWorker(String inputWorker){

    worker = inputWorker;

  }

  public void addEmotion(String inputEmotion){

    emotion = inputEmotion;

  }

  public String getName(){

    return name;
  }

  public String getService(){

    return service;

  }

  public String getWorker(){

    return worker;

  }

  public String getEmotion(){

    return emotion;

  }

  public String getTime(){

    return timeInputted.toString();

  }

}//User
