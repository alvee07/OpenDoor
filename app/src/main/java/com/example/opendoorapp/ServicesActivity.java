/**
 * AUCSC 320
 * ServicesActivity.java
 * By Arnold Gihozo
 *
 *
 * This class will give the opportunity to the user to choose between either a service
 * or a worker. If a specific services is taken, it will then lead the user to choose
 * their emotion.
 *
 */

package com.example.opendoorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opendoorapp.model.Service;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

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
  private ArrayList<Service> list;
  private MyArrayAdapter adapter;
  public URL url;
  Service service ;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_services);


    /**
     * Array List for Binding Data from JSON to this List
     */
    list = new ArrayList<>();
    /**
     * Binding that List to Adapter
     */
    adapter = new MyArrayAdapter(this, list);

    /**
     * Checking Internet Connection
     */
    if (InternetConnection.checkConnection(getApplicationContext())) {
      new GetDataTask().execute();
    } else {
      //Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
    }





//========================================================
    // SPINNERS
    servicesSpinner();
    workerSpinner();

    services.setOnItemSelectedListener(this);
    worker.setOnItemSelectedListener(this);

    //======================= spinnners=========================


  } // end of onCreate

  public String[] getServiceInformation(){

    String[] stringArray = new String[serviceArray.length];
    int position = 0;
    while (position < serviceArray.length){
      stringArray[position] = serviceArray[position].getName();
      position ++;

    }
    return stringArray;
  }

//  public String[] getServiceFromJson(){
//
//  }


  /**
   * Sets up the services spinner (with the items from the string.xml file) and sets up
   * the drop down menu
   *
   * by Arnold
   *
   * Initial setup of this has been inspired from this source
   * https://developer.android.com/guide/topics/ui/controls/spinner.
   * Accessed on January 11, 2020
   */
  public void servicesSpinner(){
    services =  findViewById(R.id.servicesSpinner);
    //container that hold the values and integrate them with the spinner
//    ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
//            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

    ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(ServicesActivity.this,
            android.R.layout.simple_list_item_1, getServiceInformation());

    //Drop down list of services stored in .xml file
    //servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    services.setAdapter(servicesAdapter);
  } // end of servicesSpinner

  /**
   * Sets up the worker spinner (with the items from the string.xml file) and sets up
   * the drop down menu
   *
   * by Arnold
   *
   * Initial setup of this has been inspired from this source
   * https://developer.android.com/guide/topics/ui/controls/spinner.
   * Accessed on January 11, 2020
   */
  public void workerSpinner(){
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
   * @param parent -- adapter view of where the selection has happened
   * @param view -- the view that was selected
   * @param position -- position of the view
   * @param id -- the id of the item selected
   *
   * To excecute this section, I looked over the code on StackOver flow on Saturday January 11, 2020
   * https://stackoverflow.com/questions/4476379/spinner-switch-case-problem
   * Changes have been made to fit this project and refactoring the code
   *
   * by Arnold
   */
  public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {

    setBackgroundColor();

    if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
      enableSpinners();
      isSelectedOption = false;


    } else {

      switch (parent.getId()) {
        case R.id.servicesSpinner:
          isSelectedOption = true;
          disableSpinner(worker);
          break;
        case R.id.workerSpinner:
          isSelectedOption = true;
          disableSpinner(services);
          break;
      }// end of switch
      getSelectedServices(parent, position);

    }// end of else

  }// end of onItemSelected

  /**
   * Sets the background of the drop down menu to white (for both the works and staff)
   *
   * by Arnold
   */
  public void setBackgroundColor(){
    services.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
    worker.setPopupBackgroundResource(R.color.servicesSpinnerBackground);
  }

  /**
   * Disables a given spinner and greys out the spinner
   * @param currentSpinner-- the spinner to be disabled
   *
   * by Arnold
   */
  public void disableSpinner(Spinner currentSpinner){
    currentSpinner.setEnabled(false);
    currentSpinner.setAlpha(0.3f);
  }

  /**
   * Enables both the worker and staff spinner
   * by Arnold
   */
  public void enableSpinners(){
    services.setEnabled(true);
    worker.setEnabled(true);
    services.setAlpha(1f);
    worker.setAlpha(1f);
  }

  /**
   * Triggered when a view disaperars from the screen or when you have an empty adapter
   *
   * @param parent -- it is an adapter view (with no selected item)
   *
   * by Arnold
   */
  public void onNothingSelected(AdapterView<?> parent) {}

  /**
   * Gets the selected services
   *
   * @param parent -- adapter view of where the selection happens
   * @param position -- position of the view
   * @return -- returns the selected item
   *
   * by Arnold
   */
  public String getSelectedServices(AdapterView<?> parent, int position){
    return selectedServices = parent.getItemAtPosition(position).toString();

  }

  /**
   * Takes user to next Activity - 'Emotions Activity' or, 'Confirmation Activity'
   * @param view - View object - Button object in this scenario
   * by Alvee
   *
   */
  public void servicesContinueBtnClicked (View view){

    if (isSelectedOption){
      Intent name = new Intent(ServicesActivity.this, EmotionsCheck.class);
      startActivity(name);
      finish();
    }else{
      Context context = getApplicationContext();
      CharSequence text = "Please select a service or a staff to see!";
      int duration = Toast.LENGTH_SHORT;
      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    }


  } // servicesContinueBtnClicked

  /**
   * Starts MainActivity class in 10 seconds
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


}// end of file

// START OF NEW CLASS

/**
 * Creating Get Data Task for Getting Data From Web
 */
class GetDataTask extends AsyncTask<Void, Void, Void> {

  ProgressDialog dialog;
  int jIndex;
  int x;

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    /**
     * Progress Dialog for User Interaction
     */

    x = list.size();

    if (x == 0)
      jIndex = 0;
    else
      jIndex = x;

    dialog = new ProgressDialog(MainActivity.this);
    dialog.setTitle("Hey Wait Please..." + x);
    dialog.setMessage("I am getting your JSON");
    dialog.show();
  }

  @Nullable
  @Override
  protected Void doInBackground(Void... params) {

    /**
     * Getting JSON Object from Web Using okHttp
     */
    JSONObject jsonObject = JSONParser.getDataFromWeb();

    try {
      /**
       * Check Whether Its NULL???
       */
      if (jsonObject != null) {
        /**
         * Check Length...
         */
        if (jsonObject.length() > 0) {
          /**
           * Getting Array named "contacts" From MAIN Json Object
           */
          JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);

          /**
           * Check Length of Array...
           */


          int lenArray = array.length();
          if (lenArray > 0) {
            for (; jIndex < lenArray; jIndex++) {

              /**
               * Creating Every time New Object
               * and
               * Adding into List
               */
              InternetConnection.MyDataModel model = new InternetConnection.MyDataModel();

              /**
               * Getting Inner Object from contacts array...
               * and
               * From that We will get Name of that Contact
               *
               */
              JSONObject innerObject = array.getJSONObject(jIndex);
              String name = innerObject.getString(Keys.KEY_NAME);
              //String country = innerObject.getString(Keys.KEY_COUNTRY);

              /**
               * Getting Object from Object "phone"
               */
              //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
              //String phone = phoneObject.getString(Keys.KEY_MOBILE);

              model.setName(name);
              //model.setCountry(country);

              /**
               * Adding name and phone concatenation in List...
               */
              list.add(model);
            }
          }
        }
      } else {

      }
    } catch (JSONException je) {
      Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
    }
    return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    dialog.dismiss();
    /**
     * Checking if List size if more than zero then
     * Update ListView
     */
    if (list.size() > 0) {
      adapter.notifyDataSetChanged();
    } else {
      Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
    }
  }
}

}

