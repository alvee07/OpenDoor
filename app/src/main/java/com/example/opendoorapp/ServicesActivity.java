package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Services spinner!
        Spinner services = (Spinner) findViewById(R.id.servicesSpinner);

        //container that hold the values and integrate them with the spinner
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.services));

        //specifiy that it is a drop down list
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(myadapter);



        // Drop down menu for the names

        Spinner names = (Spinner) findViewById(R.id.namesSpinner);

        // Container that hold the values and integrate them with the spinner
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        names.setAdapter(namesAdapter);

    }
}
