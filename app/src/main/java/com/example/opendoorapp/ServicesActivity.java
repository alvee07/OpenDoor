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
    private Spinner services;
    private Spinner worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

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
        worker =(Spinner) findViewById(R.id.namesSpinner);
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
                    if (worker.isEnabled()) {
                        worker.setEnabled(false);
                    } else {
                        worker.setEnabled(true);
                    }
                    break;

                case R.id.namesSpinner:
                    Toast.makeText(parent.getContext(),
                            "Worker selected is : " + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                    if (services.isEnabled()) {
                        services.setEnabled(false);
                    } else {
                        services.setEnabled(true);
                    }
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
     * After lunch break work on :
     *
     * Getting the switch between spinners (which one selected)
     * Getting the info of the spinner saved into a variable
     * Refactoring the code (make it pretty) and cleaning it up
     * Add comments
     */
}
