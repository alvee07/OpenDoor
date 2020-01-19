package com.example.opendoorapp;

/**
 * Service object class. used to store the information of each Service
 *
 * Benjamin Wilson
 */
public class Service {

  private String departmentName;
  private String emailList;
  private boolean isEmotion;

  /**
   * constructor for individual service object
   * @param departmentName name of the Service/ what they provide
   * @param emailList list of emails of people who help with service
   * @param isEmotion boolean on if the user must be checked for emotion
   */
  public Service(String departmentName, String emailList, boolean isEmotion) {

    this.departmentName = departmentName;
    this.emailList = emailList;
    this.isEmotion = isEmotion;

  }//service constructor

  public Service() {

    this.departmentName = "no name";
    this.emailList = "No email";
    this.isEmotion = false;

  }//default contructor

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public void setEmailList(String emailList) {
    this.emailList = emailList;
  }

  public void setEmotion(boolean emotion) {
    isEmotion = emotion;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public String getEmailList() {
    return emailList;
  }

  public boolean isEmotion() {
    return isEmotion;
  }

  @Override
  public String toString() {
    return "Service{" +
            "departmentName='" + departmentName + '\'' +
            ", emailList='" + emailList + '\'' +
            ", isEmotion=" + isEmotion +
            '}';
  }
}
