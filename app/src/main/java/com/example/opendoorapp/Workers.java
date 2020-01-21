package com.example.opendoorapp;

import java.util.ArrayList;

import static com.example.opendoorapp.User.workerName;

public class Workers {
  
  private String name;
  private ArrayList<String> email;
  private boolean isEmotion;


  /**
   * Worker object class for individual staff at the open door
   * @param name  name of the staff member
   * @param email email of the staff member
   * @param isEmotion  boolean for if the staff requires the emotion of the user
   */
  public Workers(String name, ArrayList<String> email, boolean isEmotion) {
    this.name = name;
    this.email = email;
    this.isEmotion = isEmotion;
  private String email;
  private Boolean isEmotion;
  private Workers[] workerList;

  Workers(String workerName, String workerEmail, Boolean isEmotion, Workers[] workerList){
    name = workerName;
    email = workerEmail;
    this.isEmotion = isEmotion;
    this.workerList = workerList;
  }

  public Workers(String name, String email, Boolean isEmotion) {
    this.name = name;
    this.email = email;
    this.isEmotion = isEmotion;
  }

  public Workers(Workers[] workerList) {
    this.workerList = workerList;
  }

  Workers(){
    name = "Worker";
    email = "worker@email.com";
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Boolean isIsEmotion() {
    return isEmotion;
  }

  public Workers[] getWorkerList() {
    return workerList;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEmotion(Boolean emotion) {
    isEmotion = emotion;
  }

  public void setWorkerList(Workers[] workerList) {
    this.workerList = workerList;
  }

  @Override
  public String toString() {
    return "Workers{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
