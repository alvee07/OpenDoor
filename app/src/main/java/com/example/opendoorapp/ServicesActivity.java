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
import android.widget.AdapterView.OnItemSelectedListener;

import android.content.Intent;
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
    private Spinner services;
    private Spinner worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

    System.out.println(User.userName);

        // Services
        // ===========================================================================
        services = (Spinner) findViewById(R.id.servicesSpinner);
        //container that hold the values and integrate them with the spinner
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.services));

        //specifiy that it is a drop down list
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(myadapter);
        //=============================================================================

        //Workers
        //==============================================================================
        worker =(Spinner) findViewById(R.id.workerSpinner);
        // Container that hold the values and integrate them with the spinner
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        worker.setAdapter(namesAdapter);
        //======================================================================================
        services.setOnItemSelectedListener(this);
        worker.setOnItemSelectedListener(this);




    } // end of onCreate


    public void onItemSelected (AdapterView<?> parent, View v, int position, long id) {
        if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
            services.setEnabled(true);
            worker.setEnabled(true);

        } else {

            switch (parent.getId()) {


                case R.id.servicesSpinner:
                    Toast.makeText(parent.getContext(),
                            "Services selected is : " + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                    worker.setEnabled(false);
                    break;

                case R.id.workerSpinner:
                    Toast.makeText(parent.getContext(),
                            "Worker selected is : " + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                    services.setEnabled(false);
                    break;

            }// end of switch

        }// end of else

    }// end of onItemSelected

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void setSelectedServices (){

        if (isSelectedWorker = true){
            services.setVisibility(View.INVISIBLE);
            isSelectedWorker = false;
        }else{
            isSelectedService = true;
        }
    }// end of setSelectedServices

    private void setSelectedWorker(){

    }

    /**
     * Takes user to next Activity - 'Emotions Activity' or, 'Confirmation Activity'
     * @param view - View object - Button object in this scenario
     * by Alvee
     *
     */
    public void servicesContinueBtnClicked (View view){

        Intent name = new Intent(ServicesActivity.this, EmotionsCheck.class);
        startActivity(name);
        finish();
    } // servicesContinueBtnClicked
}
