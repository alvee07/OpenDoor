package com.example.opendoorapp;

public class User {

  String name;
  String service;
  String worker;
  String emotion;

  User(){

    name = "No name";
    service = "Not Applicable";
    worker = "Not Applicable";
    emotion = "Not Applicable";

  }//User defaultConstructor

  User(String inputName){

    name = inputName;
    service = "Not Applicable";
    worker = "Not Applicable";
    emotion = "Not Applicable";
  }

  public void addService(String inputService){

    service = inputService;

  }//addService

  public void addWorker(String inputWorker){

    worker = inputWorker;

  }
}
