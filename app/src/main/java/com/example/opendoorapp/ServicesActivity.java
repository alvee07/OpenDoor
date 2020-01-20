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
  public static ArrayList<Service> list;
  public static ServiceAdapter serviceAdapter;
  public static SpinnerAdapter spinnerAdapter;

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
     * Getting List and Setting List Adapter
     */


    /**
     * Checking Internet Connection
     */
    if (InternetConnection.checkConnection(getApplicationContext())) {
      new GetDataTask().execute();
    } else {
      //Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
    }


//    List<String> strings = new ArrayList<>(list.size());
//    for (Service object : list) {
//      strings.add(list.toString(object,null));
//    }

    services =  findViewById(R.id.servicesSpinner);




    serviceAdapter = new ServiceAdapter(ServicesActivity.this,list);
    services.setAdapter(serviceAdapter);
    //serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    //services.setAdapter(new SpinnerAdapter(list ,this));

    services.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ServicesActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });











   //========================================================
    // SPINNERS
    //services =  findViewById(R.id.servicesSpinner);
    //servicesSpinner();
    workerSpinner();
    //ArrayAdapter<Service> adapter = new ArrayAdapter<Service>(this, R.layout.layout_spinner,R.id.txt, list);
    //services.setAdapter(adapter);


    services.setOnItemSelectedListener(this);
    worker.setOnItemSelectedListener(this);

    //======================= spinnners=========================


  } // end of onCreate

  public List<String> getServiceInformation(){

    List<String> stringArray = new ArrayList<>();
    if (list != null) {
      for (int i=0;i< list.size();i++){
        stringArray.add(list.get(i).getName());
      }
    }

    return stringArray;

  }



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



    ArrayAdapter<Service> servicesAdapter = new ArrayAdapter<Service>(ServicesActivity.this, android.R.layout.simple_list_item_1, getList());
    //servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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


    if (parent.getItemAtPosition(position).equals("-- Choose an option --")) {
      //enableSpinners();
      isSelectedOption = false;


    } else {

      switch (parent.getId()) {
        case R.id.servicesSpinner:
          isSelectedOption = true;
          //disableSpinner(worker);

          CharSequence text = "Services selected!" + parent.getItemAtPosition(position).toString();
          int duration = Toast.LENGTH_SHORT;
          Toast toast = Toast.makeText(this, text, duration);
          toast.show();
          break;
        case R.id.workerSpinner:
          isSelectedOption = true;
           Toast toast2 = Toast.makeText(this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT);
           toast2.show();

           //disableSpinner(services);


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
   *
   *
   * Modified by Arnold to add the not continue
   * by Alvee
   *
   *
   *
   *
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

  public static ArrayList<Service> getList() {
    return list;
  }


}// end of file

// START OF NEW CLASS

/**
 * Code by admin from The Crazy Coders Club.
 * followed their tutorial which is why there is a class within a class
 * found from
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
 */

/**
 * Creating Get Data Task for Getting Data From Web
 */
class GetDataTask extends AsyncTask<Void, Void, Void> {

  int jIndex;
  int x;

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    /**
     * Progress Dialog for User Interaction
     */

    x = ServicesActivity.getList().size();

    if (x == 0)
      jIndex = 0;
    else
      jIndex = x;


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
              // maybe add InternetConnection??????????
              Service model = new Service();

              /**
               * Getting Inner Object from contacts array...
               * and
               * From that We will get Name of that Contact
               *
               */
              JSONObject innerObject = array.getJSONObject(jIndex);
              String name = innerObject.getString(Keys.KEY_NAME);
              //String email = innerObject.getString(Keys.KEY_EMAIL);
              //Boolean isEmotion = innerObject.getBoolean(Keys.KEY_ISEMOTION);

              /**
               * Getting Object from Object "phone"
               */
              //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
              //String phone = phoneObject.getString(Keys.KEY_MOBILE);

              model.getEmail();
              model.setName(name);
              //model.setEmail(email);
              //model.setIsEmotion(isEmotion);

              /**
               * Adding name and phone concatenation in List...
               */
              ServicesActivity.getList().add(model);
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
    ServicesActivity.serviceAdapter.notifyDataSetChanged();

  }
}



