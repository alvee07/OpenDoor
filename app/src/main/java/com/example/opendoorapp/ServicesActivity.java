/**
 * AUCSC 320
 * ServicesActivity.java
 * By Arnold Gihozo
 * <p>
 * <p>
 * This class will give the opportunity to the user to choose between either a service
 * or a worker. If a specific services is taken, it will then lead the user to choose
 * their emotion.
 * <p>
 * <p>
 * -- NOTE --
 * - Please note that this class is still in development in order to get the execel to work in
 * the application. Therefore, you may find commented out code as the class is still in
 * testing. Everything will be cleaned up before the final release of the application
 */

package com.example.opendoorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opendoorapp.adapter.ServiceAdapter;
import com.example.opendoorapp.model.Service;
import com.example.opendoorapp.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements OnItemSelectedListener {

    private String selectedServices;
    private String selectedWorker;
    private User currentUser;
    private Workers[] workerArray;
    private Service[] serviceArray;
    private Spinner services;
    private Spinner worker;
    private boolean isSelectedOption;
    private GestureDetectorCompat mDetector;
    public String[] test;
    public static ArrayList<Service> serviceList;
    public static ArrayList<Service> workerlist;
    public static ServiceAdapter serviceAdapter;
    public static ServiceAdapter workerAdapter;
    //public static ProgressBar progressbar;
    public static LoadingDialog loadingDialog;

    public URL url;
    Service service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        loadingDialog = new LoadingDialog(ServicesActivity.this);

        // Array List for Binding Data from JSON to this List
        serviceList = new ArrayList<>();
        workerlist = new ArrayList<>();


        // checking internet connect
        if (InternetConnection.checkConnection(getApplicationContext())) {
            new GetDataTask().execute();
        }


        services = findViewById(R.id.servicesSpinner);
        worker = findViewById(R.id.workerSpinner);

        // Putting the Service Data into service spinner
        serviceAdapter = new ServiceAdapter(ServicesActivity.this, serviceList);
        services.setAdapter(serviceAdapter);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        services.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ServicesActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        workerAdapter = new ServiceAdapter(ServicesActivity.this, workerlist);
        worker.setAdapter(workerAdapter);
        workerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        worker.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ServicesActivity.this, "Clicked Service", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        services.setOnItemSelectedListener(this);
        worker.setOnItemSelectedListener(this);


    } // end of onCreate


    /**
     * Sets up the services spinner (with the items from the string.xml file) and sets up
     * the drop down menu
     * <p>
     * by Arnold
     * <p>
     * Initial setup of this has been inspired from this source
     * https://developer.android.com/guide/topics/ui/controls/spinner.
     * Accessed on January 11, 2020
     */
    public void servicesSpinner() {


        ArrayAdapter<Service> servicesAdapter = new ArrayAdapter<Service>(ServicesActivity.this, android.R.layout.simple_list_item_1, getList());
        //servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        services.setAdapter(servicesAdapter);
    } // end of servicesSpinner

    /**
     * Sets up the worker spinner (with the items from the string.xml file) and sets up
     * the drop down menu
     * <p>
     * by Arnold
     * <p>
     * Initial setup of this has been inspired from this source
     * https://developer.android.com/guide/topics/ui/controls/spinner.
     * Accessed on January 11, 2020
     */
    public void workerSpinner() {
        //Workers
        //==============================================================================
        worker = findViewById(R.id.workerSpinner);
        // Container that hold the values and integrate them with the spinner
        ArrayAdapter<String> workerAdapter = new ArrayAdapter<String>(ServicesActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        workerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        worker.setAdapter(workerAdapter);

    }// end of workerSpinner

    /**
     * Checks the selections of the user. If user selects services, it disables the staff
     * lists and vice versa.
     *
     * @param parent   -- adapter view of where the selection has happened
     * @param view     -- the view that was selected
     * @param position -- position of the view
     * @param id       -- the id of the item selected
     *                 <p>
     *                 To excecute this section, I looked over the code on StackOver flow on Saturday January 11, 2020
     *                 https://stackoverflow.com/questions/4476379/spinner-switch-case-problem
     *                 Changes have been made to fit this project and refactoring the code
     *                 <p>
     *                 by Arnold
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        enableSpinners();
        if (parent.getItemAtPosition(position).toString().equals("-- Choose an option --")) {
            enableSpinners();
            isSelectedOption = false;


        } else {

            switch (parent.getId()) {
                case R.id.servicesSpinner:
                    if (parent.getItemAtPosition(position).toString().equals("-- Choose an option --")) {
                        //worker.setEnabled(true);
                        //enableSpinners();

                    } else {
                        isSelectedOption = true;
                        CharSequence text = "Services selected!" + parent.getItemAtPosition(position).toString();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(this, text, duration);
                        toast.show();
                        disableSpinner(worker);

                    }
                    break;
                case R.id.workerSpinner:

                    if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
                        //services.setEnabled(true);
                        //enableSpinners();
                    } else {
                        isSelectedOption = true;
                        Toast toast2 = Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT);
                        toast2.show();
                        disableSpinner(services);

                    }

                    break;
            }// end of switch
            getSelectedServices(parent, position);

        }// end of else

    }// end of onItemSelected

    /**
     * Sets the background of the drop down menu to white (for both the works and staff)
     * <p>
     * by Arnold
     */
    public void setBackgroundColor() {
        services.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
        worker.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
    }

    /**
     * Disables a given spinner and greys out the spinner
     *
     * @param currentSpinner-- the spinner to be disabled
     *                         <p>
     *                         by Arnold
     */
    public void disableSpinner(Spinner currentSpinner) {
        currentSpinner.setEnabled(false);
        currentSpinner.setAlpha(0.3f);
    }

    /**
     * Enables both the worker and staff spinner
     * by Arnold
     */
    public void enableSpinners() {
        services.setEnabled(true);
        worker.setEnabled(true);
        services.setAlpha(1f);
        worker.setAlpha(1f);
    }

    /**
     * Triggered when a view disaperars from the screen or when you have an empty adapter
     *
     * @param parent -- it is an adapter view (with no selected item)
     *               <p>
     *               by Arnold
     */
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Gets the selected services
     *
     * @param parent   -- adapter view of where the selection happens
     * @param position -- position of the view
     * @return -- returns the selected item
     * <p>
     * by Arnold
     */
    public String getSelectedServices(AdapterView<?> parent, int position) {
        return selectedServices = parent.getItemAtPosition(position).toString();

    }

    /**
     * Takes user to next Activity - 'Emotions Activity' or, 'Confirmation Activity'
     *
     * @param view - View object - Button object in this scenario
     *             <p>
     *             <p>
     *             Modified by Arnold to add the not continue
     *             by Alvee
     */
    public void servicesContinueBtnClicked(View view) {

        if (isSelectedOption) {
            Intent name = new Intent(ServicesActivity.this, EmotionsCheck.class);
            startActivity(name);
            finish();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Please select a service or a staff to see!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    } // servicesContinueBtnClicked

    /**
     * Starts MainActivity class in 10 seconds
     * <p>
     * by Alvee
     */
    public void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent goBackToMainActivity = new Intent(ServicesActivity.this, MainActivity.class);
                ServicesActivity.this.startActivity(goBackToMainActivity);
                ServicesActivity.this.finish();
            }
        }, 10000);
    } // startMainActivity

    public static ArrayList<Service> getList() {
        return serviceList;
    }

    public static ArrayList<Service> getWorkerlistList() {
        return workerlist;
    }


}// end of file






