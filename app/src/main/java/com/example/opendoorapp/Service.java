package com.example.opendoorapp;

import java.util.Arrays;

public class Service {
    private String name;
    private String email;
    private boolean isIsEmotion;
    private Service[] servicesList;

    Service(String name, boolean isIsEmotion, String email, Service[] servicesList){
        this.name = name;
        this.isIsEmotion = isIsEmotion;
        this.email = email;
        this.servicesList = servicesList;

    }//standard constructor

    Service(Service[] servicesList){


        this(null,false,null, servicesList);

    }//service list constructor

    Service(String name, boolean isIsEmotion, String email) {
        this(name, isIsEmotion, email, null);


    }//service constructor

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIsEmotion() {
        return isIsEmotion;
    }

    public Service[] getServicesList() {
        return servicesList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsEmotion(boolean isEmotion) {
        isIsEmotion = isEmotion;
    }

    public void setServicesList(Service[] servicesList) {
        this.servicesList = servicesList;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isIsEmotion=" + isIsEmotion +
                ", servicesList=" + Arrays.toString(servicesList) +
                '}';
    }
}








