/**
 * AUCSC 320
 * ServicesActivity.java
 * By Arnold Gihozo
 *
 *
 * This class will give the opportunity to the user to choose between either a service
 * or a worker.
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ServicesActivity extends AppCompatActivity implements OnItemSelectedListener {

    private String selectedServices;
    //private Worker selectedWorker;
    private User currentUser;
    private Boolean isSelectedService;
    private Boolean isSelectedWorker;


    public Spinner services;
    private Spinner names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Services spinner!
        services = (Spinner) findViewById(R.id.servicesSpinner);


        //container that hold the values and integrate them with the spinner
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.services));

        //specifiy that it is a drop down list
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(myadapter);



        // Drop down menu for the workers
        names =(Spinner) findViewById(R.id.namesSpinner);



        // Container that hold the values and integrate them with the spinner
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        names.setAdapter(namesAdapter);

    } // end of onCreate


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void setSelectedServices (){

        if (isSelectedWorker = true){
            //services.setVisibility(View.INVISIBLE);
            isSelectedWorker = false;
        }else{
            isSelectedService = true;
        }
    }// end of setSelectedServices

    private void setSelectedWorker(){

    }
}
