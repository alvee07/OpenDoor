package com.example.opendoorapp;

public class Service {
    private String name;
    private boolean isIsEmotion;
    private Workers[] workerList;
    private Service[] servicesList;

    Service(String name, boolean isIsEmotion, Workers[] workerList, Service[] servicesList){
        this.name = name;
        this.isIsEmotion = isIsEmotion;
        this.workerList = workerList;
        this.servicesList = servicesList;

    }

    Service(Service[] servicesList){
        this(null,false,null, servicesList);

    }

    Service(String name, boolean isIsEmotion, Workers[] workerList){
        this(name, isIsEmotion, workerList, null);


    }

}


