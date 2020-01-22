package com.example.opendoorapp.model;

import java.util.Arrays;

public class Service {
    private String name;
    private String email;
    private boolean isEmotion;

    Service(String name, boolean isEmotion, String email){
        this.name = name;
        this.isEmotion = isEmotion;
        this.email = email;

    }//standard constructor



    public Service() {

        this.name = "place holder";
        this.email = "example@example.com";
        this.isEmotion = false;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmotion() {
        return isEmotion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmotion(boolean emotion) {
        isEmotion = emotion;
    }


    @Override
    public String toString() {
        return name;
    }
}








