package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Services extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);

        Spinner services = (Spinner) findViewById(R.id.servicesSpinner);

        //container that hold the values and integrate them with the spinner
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Services.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.services));

        //specifiy that it is a drop down list
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(myadapter);
    }





}
