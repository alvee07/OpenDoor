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

import android.os.Handler;
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
    private Workers selectedWorker;
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
        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.services));

        //specifiy that it is a drop down list
        //services.setBackgroundColor(R.color.openDoorBackGroundColor);
        servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(servicesAdapter);
        //=============================================================================

        //Workers
        //==============================================================================
        worker =(Spinner) findViewById(R.id.workerSpinner);
        // Container that hold the values and integrate them with the spinner
        ArrayAdapter<String> workerAdapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        worker.setAdapter(workerAdapter);
        //======================================================================================
        services.setOnItemSelectedListener(this);
        worker.setOnItemSelectedListener(this);

        startMainActivity();
    } // end of onCreate

    /**
     *
     * @param parent
     * @param v
     * @param position
     * @param id
     *
     * by Arnold
     */
    public void onItemSelected (AdapterView<?> parent, View v, int position, long id) {

        services.setPopupBackgroundResource(R.color.openDoorYellowColor);
        worker.setPopupBackgroundResource(R.color.openDoorYellowColor);

        if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
            services.setEnabled(true);
            worker.setEnabled(true);

        } else {

            switch (parent.getId()) {
                case R.id.servicesSpinner:
                    Toast.makeText(parent.getContext(),
                            "Services selected is : " + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                    selectedServices = parent.getItemAtPosition(position).toString();
                    worker.setEnabled(false);
                    break;

                case R.id.workerSpinner:
                    Toast.makeText(parent.getContext(),
                            "Worker selected is : " + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                    //selectedWorker = parent.getItemAtPosition(position).toString();
                    services.setEnabled(false);
                    break;

            }// end of switch

        }// end of else

    }// end of onItemSelected

    public void onNothingSelected(AdapterView<?> arg0) {}

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

    /**
     * Starts MainActivity class in 1000000000000 seconds
     *
     * by Alvee
     */
    public void startMainActivity(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent goBackToMainActivity = new Intent(ServicesActivity.this, MainActivity.class);
               ServicesActivity.this.startActivity(goBackToMainActivity);
               ServicesActivity.this.finish();
            }
        }, 10000);
    } // startMainActivity
}

/**
 * To work on after supper:
 *
 *
 * Setting a black color once the second option has been disabled
 * Setting up an arrow within the spinners
 * Clean up code
 * Modify the timer to go on when screen not touched
 *
 *
 *
 */
