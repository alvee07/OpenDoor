/**
 * GetDataTask.java
 * By Arnold Gihozo
 *
 * Code by admin from The Crazy Coders Club.
 * followed their tutorial which is why there is a class within a class
 * found from
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
 *
 * -- NOTE --
 *    - Please note that this class is still in development in order to get the execel to work in
 *      the application. Therefore, you may find commented out code as the class is still in
 *      testing. Everything will be cleaned up before the final release of the application
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

    int jIndex;
    int workerIndex;
    int serviceListSize;
    int staffListSize;
    LoadingDialog loading;



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       

        serviceListSize = ServicesActivity.getList().size();
        staffListSize = ServicesActivity.getWorkerlistList().size();

        if (serviceListSize == 0)
            jIndex = 0;
        else
            jIndex = serviceListSize;

        if (staffListSize == 0)
            workerIndex = 0;
        else
            workerIndex = staffListSize;
        loading = ServicesActivity.loadingDialog;


        loading.startLoadingDialog();







    }

    @SuppressLint("WrongThread")
    @Nullable
    @Override
    protected Void doInBackground(Void... params) {

        /**
         * Getting JSON Object from Web Using okHttp
         */
        JSONObject jsonObject = JSONParser.getDataFromWeb();
        JSONObject workerObject = JSONParser.getDataFromWeb2();


        try {
            /**
             * Check Whether Its NULL???
             */
            if (jsonObject != null) {
                /**
                 * Check Length...
                 */
                if (jsonObject.length() > 0 && workerObject.length() > 0) {
                    /**
                     * Getting Array named "contacts" From MAIN Json Object
                     */
                    JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);
                    JSONArray workerarray = workerObject.getJSONArray("Sheet1");

                    /**
                     * Check Length of Array...
                     */


                    int lenArray = array.length();
                    int workerArrayLen = workerarray.length();

                    // Gathering all the data from service excel sheet
                    if (lenArray > 0) {
                        for (; jIndex < lenArray; jIndex++) {

                            /**
                             * Creating Every time New Object
                             * and
                             * Adding into List
                             */
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

                    // gathering all the data from staff excell sheet

                    if (workerArrayLen > 0) {
                        for (; workerIndex < workerArrayLen; workerIndex++) {

                            /**
                             * Creating Every time New Object
                             * and
                             * Adding into List
                             */

                            Service staffModel = new Service();

                            /**
                             * Getting Inner Object from contacts array...
                             * and
                             * From that We will get Name of that Contact
                             *
                             */
                            JSONObject innerObject = workerarray.getJSONObject(workerIndex);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            //String email = innerObject.getString(Keys.KEY_EMAIL);
                            //Boolean isEmotion = innerObject.getBoolean(Keys.KEY_ISEMOTION);

                            /**
                             * Getting Object from Object "phone"
                             */
                            //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                            //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                            //model.getEmail();
                            staffModel.setName(name);
                            //model.setEmail(email);
                            //model.setIsEmotion(isEmotion);

                            /**
                             * Adding name and phone concatenation in List...
                             */
                            ServicesActivity.getWorkerlistList().add(staffModel);
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

        loading.dismissDialog();


        ServicesActivity.serviceAdapter.notifyDataSetChanged();
        ServicesActivity.workerAdapter.notifyDataSetChanged();

    }
}
