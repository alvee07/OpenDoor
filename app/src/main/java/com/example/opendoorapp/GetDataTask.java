/**
 * GetDataTask.java
 * By Arnold Gihozo
 *
 * Code by admin from The Crazy Coders Club.
 * followed their tutorial which is why there is a class within a class
 * found from
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
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
    int x;
    int worker;
    LoadingDialog loading;



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /**
         * Progress Dialog for User Interaction
         */

        x = ServicesActivity.getList().size();
        worker = ServicesActivity.getWorkerlistList().size();

        if (x == 0)
            jIndex = 0;
        else
            jIndex = x;

        if (worker == 0)
            workerIndex = 0;
        else
            workerIndex = worker;
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

                    if (workerArrayLen > 0) {
                        for (; workerIndex < workerArrayLen; workerIndex++) {

                            /**
                             * Creating Every time New Object
                             * and
                             * Adding into List
                             */
                            // maybe add InternetConnection??????????
                            Service modelW = new Service();

                            /**
                             * Getting Inner Object from contacts array...
                             * and
                             * From that We will get Name of that Contact
                             *
                             */
                            JSONObject innerObject = workerarray.getJSONObject(workerIndex);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            String email = innerObject.getString(Keys.KEY_EMAIL);
                            Boolean isEmotion = innerObject.getBoolean(Keys.KEY_ISEMOTION);

                            /**
                             * Getting Object from Object "phone"
                             */
                            //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                            //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                           modelW.getName();
                           modelW.setName(name);


                            /**
                             * Adding name and phone concatenation in List...
                             */
                            ServicesActivity.getWorkerlistList().add(modelW);
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
        System.out.println(ServicesActivity.getWorkerlistList());


        ServicesActivity.serviceAdapter.notifyDataSetChanged();
        ServicesActivity.workerAdapter.notifyDataSetChanged();

    }
}
