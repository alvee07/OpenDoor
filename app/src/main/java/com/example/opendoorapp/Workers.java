package com.example.opendoorapp;

import java.util.ArrayList;

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
  }

  /**
   * Default Constructor
   * by Alex Taylor
   */
  Workers(){
    name = "Worker";
    isEmotion = false;

  }




  public String getName() {
    return name;
  }

  public ArrayList<String> getEmail() {
    return email;
  }

  public boolean isEmotion() {
    return isEmotion;
  }

  @Override
  public String toString() {
    return "Workers{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", isEmotion=" + isEmotion +
            '}';
  }
}