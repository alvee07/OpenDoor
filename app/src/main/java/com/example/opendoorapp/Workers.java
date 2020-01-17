package com.example.opendoorapp;

public class Workers {
  
  private String name;
  private String email;
  
  Workers(String workerName, String workerEmail){
    name = workerName;
    email = workerEmail;
  }
  
  Workers(){
    name = "Worker";
    email = "worker@email.com";
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getName() {
    return name;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Workers{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
