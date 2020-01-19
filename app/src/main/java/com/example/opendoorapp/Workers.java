package com.example.opendoorapp;

public class Workers {
  
  private String name;
  private String email;

  /**
   * Constructor for Workers class
   * @param workerName name of staff member
   * @param workerEmail email address
   * by Alex Taylor
   */
  Workers(String workerName, String workerEmail){
    name = workerName;
    email = workerEmail;
  }

  /**
   * Default Constructor
   * by Alex Taylor
   */
  Workers(){
    name = "Worker";
    email = "worker@email.com";
  }

  /**
   * Sets Workers name
   * @param name desired name
   * by Alex Taylor
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the workers email string
   * @return email
   * by Alex Taylor
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the workers name string
   * @return name
   * by Alex Taylor
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the email
   * @param email
   * by Alex Taylor
   */
  public void setEmail(String email) {
    this.email = email;
  }
}
