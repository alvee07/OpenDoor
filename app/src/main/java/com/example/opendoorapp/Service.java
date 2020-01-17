package com.example.opendoorapp;

import java.util.Arrays;

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

    public String getName() {
        return name;
    }

    public boolean isIsEmotion() {
        return isIsEmotion;
    }

    public Workers[] getWorkerList() {
        return workerList;
    }

    public Service[] getServicesList() {
        return servicesList;
    }



    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", isIsEmotion=" + isIsEmotion +
                ", workerList=" + Arrays.toString(workerList) +
                ", servicesList=" + Arrays.toString(servicesList) +
                '}';
    }
}


