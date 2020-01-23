/**
 * GetDataTask.java
 * By Arnold Gihozo
 * <p>
 * Code by admin from The Crazy Coders Club.
 * followed their tutorial which is why there is a class within a class
 * found from
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
 * <p>
 * -- NOTE --
 * - Please note that this class is still in development in order to get the execel to work in
 * the application. Therefore, you may find commented out code as the class is still in
 * testing. Everything will be cleaned up before the final release of the application
 */


package com.example.opendoorapp;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.opendoorapp.model.Service;
import com.example.opendoorapp.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Creating Get Data Task for Getting Data From Web
 */
class GetDataTask extends AsyncTask<Void, Void, Void> {

    int serviceIndex;
    int staffIndex;
    int serviceListSize;
    int staffListSize;
    LoadingDialog loading;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        serviceListSize = ServicesActivity.getList().size();
        staffListSize = ServicesActivity.getWorkerlistList().size();

        setIndex(serviceListSize, serviceIndex);
        setIndex(staffListSize, staffIndex);

        // Setting the loading screen as the data is being gathered
        loading = ServicesActivity.loadingDialog;
        loading.startLoadingDialog();

    } // end of onPreExecute


    @SuppressLint("WrongThread")
    @Nullable
    @Override
    protected Void doInBackground(Void... params) {

        /**
         * Getting JSON Object from Web Using okHttp
         */
        JSONObject serviceJsonObject = JSONParser.getDataFromWeb();
        JSONObject staffJsonObject = JSONParser.getDataFromWeb2();


        try {

            if (serviceJsonObject != null) {

                // check for length
                if (serviceJsonObject.length() > 0 && staffJsonObject.length() > 0) {

                    //==============================================================================
                    // Service Data

                    JSONArray serviceArray = serviceJsonObject.getJSONArray(Keys.KEY_FILE);
                    int lenServiceArray = serviceArray.length();
                    if (lenServiceArray > 0) {
                        for (; serviceIndex < lenServiceArray; serviceIndex++) {


                            Service modelStaff = new Service();

                            // getting inner object from serviceArray from that
                            // we will get name of the service
                            JSONObject innerObject = serviceArray.getJSONObject(serviceIndex);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            //String email = innerObject.getString(Keys.KEY_EMAIL);
                            //Boolean isEmotion = innerObject.getBoolean(Keys.KEY_ISEMOTION);

                            modelStaff.getEmail();
                            modelStaff.setName(name);
                            //model.setEmail(email);
                            //model.setIsEmotion(isEmotion);

                            /**
                             * Adding name and phone concatenation in List...
                             */
                            ServicesActivity.getList().add(modelStaff);


                        } // end of iterating for loop
                    } // end of inner if
                    // ============================================================================
                    // Staff Data


                    JSONArray staffArray = staffJsonObject.getJSONArray(Keys.KEY_FILE);
                    int lenStaffArray = staffArray.length();

                    if (lenStaffArray > 0) {
                        for (; staffIndex < lenStaffArray; staffIndex++) {

                            // Creating a new object at every interation and adding to the list
                            Service staffModel = new Service();

                            /**
                             * Getting Inner Object from contacts serviceArray...
                             * and
                             * From that We will get Name of that Contact
                             *
                             */
                            JSONObject innerObject = staffArray.getJSONObject(staffIndex);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            //String email = innerObject.getString(Keys.KEY_EMAIL);
                            //Boolean isEmotion = innerObject.getBoolean(Keys.KEY_ISEMOTION);

                            // getting the objects

                            //model.getEmail();
                            staffModel.setName(name);
                            //model.setEmail(email);
                            //model.setIsEmotion(isEmotion);

                            // Adding objects to list
                            ServicesActivity.getWorkerlistList().add(staffModel);

                        } // end of inner for loop iterating to add objects in list

                    } // end of inner if checking if empty array

                } // end of outer if checking if len > 0

            } // end of outer if checking if object empty


        } catch (JSONException je) {
            Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
        }
        return null;
    } // end of doInBackground method

    @Override
    protected void onPostExecute(Void aVoid) {

        loading.dismissDialog();

        // setting both datas to ececute
        ServicesActivity.serviceAdapter.notifyDataSetChanged();
        ServicesActivity.workerAdapter.notifyDataSetChanged();

    }

    private void setIndex(int listSize, int index) {
        index = listSize;
    }
}
