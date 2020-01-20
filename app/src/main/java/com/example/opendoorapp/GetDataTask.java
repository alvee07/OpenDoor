/**
 * AUCSC 320
 * by Arnold Gihozo
 *
 * This class will gt the data from our Staff Excel file and Service Excel file and
 * converts it into JSON data that is stored inside ArrayList<Service>.
 *
 * These operations happen in the background (on a seperate thread), however, the data is brought
 * on the main threat onPostExecute.
 *
 *
 * Code by admin from The Crazy Coders Club.
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
 * Accessed on January 17th, 2019
 *
 * Modified component
 *   - Changed this class from beinf an inner class to a outer class
 *   - Changed variable names (for service list)
 *   - Added an onPostExecute in order to get our data selectable on the main thread
 *   - Added emotions, service, service name, email and staff (workers)
 *   - Changed the overall look of the code to make it more clean
 */

package com.example.opendoorapp;




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
    int serviceListSize;
    int staffListSize;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /**
         * Progress Dialog for User Interaction
         */

        serviceListSize = ServicesActivity.getServiceList().size();
        staffListSize = ServicesActivity.getStaffList().size();

        if (serviceListSize == 0)
            jIndex = 0;
        else
            jIndex = serviceListSize;


        if (staffListSize == 0)
            jIndex = 0;
        else
            jIndex = staffListSize;


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

                            Service serviceModel = new Service();
                            Service staffModel = new Service();

                            /**
                             * Getting Inner Object from contacts array...
                             * and
                             * From that We will get Name of that Contact
                             *
                             */
                            JSONObject innerObject = array.getJSONObject(jIndex);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            String email = innerObject.getString(Keys.KEY_EMAIL);
                            Boolean isEmotion = innerObject.getBoolean(Keys.KEY_ISEMOTION);

                            staffModel.getEmail();
                            serviceModel.setName(name);
                            staffModel.setEmail(email);
                            staffModel.setIsEmotion(isEmotion);

                            /**
                             * Adding name and phone concatenation in List...
                             */
                            ServicesActivity.getServiceList().add(serviceModel);
                            ServicesActivity.getStaffList().add(staffModel);
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