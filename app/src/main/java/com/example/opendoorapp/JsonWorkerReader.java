package com.example.opendoorapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class JsonWorkerReader {
    private Context context;




    public JsonWorkerReader(Context context) {
        this.context = context;
    }

    public InputStream findResource(){
        return context.getResources().openRawResource(R.raw.services);
    }

    public void workerReader(

    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        //Path path = FileSystems.getDefault().getPath("raw", "services.json");
        InputStream input = findResource();
        try {
            Service service = objectMapper.readValue(new File(context.getFilesDir() + "/" + "raw/services.json"), Service.class);
            System.out.print("==============!!!!!!!!!!!!!" + service);

        } catch (IOException e) {
            e.printStackTrace();
            //raw/services.json
        }



    }

}
