package com.example.opendoorapp.model;

public class Workers {
  
  private String name;
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
