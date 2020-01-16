package com.example.opendoorapp;

import android.content.res.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonWorkerReader {

    public static void workerReader(){
        ObjectMapper objectMapper = new ObjectMapper();
        

        Service services = objectMapper.readValue(new File());

    }

}
