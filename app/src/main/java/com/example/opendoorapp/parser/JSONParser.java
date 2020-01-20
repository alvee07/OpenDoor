/**
 * AUCSC 320
 * By Arnold Gihozo
 *
 * Add Description HERE
 *
 * Code by admin from The Crazy Coders Club.
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
 * Accessed on January 17th, 2019
 *
 * Modified component:
 *   - Refactored variable names
 *   - Added the Worker URL
 *   - Added functions to support Worker retrieve of data
 */

package com.example.opendoorapp.parser;
import android.util.Log;

import androidx.annotation.NonNull;
//
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;




import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class JSONParser {

    private static final String SERVICE_URL = "https://script.googleusercontent.com/a/macros/ualberta.ca/echo?user_content_key=yoc7ZdiarCPaAwRkuiaGOGnK0c2AZBrI3BEnHikxCEavkJNhBhzsL-TfBMMkdL4O-t_PSbOw7lFpPe5pWubcrZDj_8nz7_nUOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKAglIVEdYoEkp7ei4iVNOTz1X4897iSixbywob23dSxBctfhUxgCPwKvDfanQTKoL0SNl5M3_4VFPKLDKkgs-p-rWi7AHZdRU4hCDNYvd56PsAhh98Kdr6hECJPmeWG8-k7i9bsdtc5LRMIUgxK1xuXLPmk-GWZxoCoaGK_iiRLzKgIkpmQyeqzQzvQt_V_CZ4&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
    private static final String STAFF_URL = "https://script.googleusercontent.com/a/macros/ualberta.ca/echo?user_content_key=hm0HmMER6PGyL2bQOyzL5Y8ERXF6D1IpVxNbL3U6AurDYwy-a3rYfxA5SRdNbxDlS3oGlGbz1rpqOfsf2zlXysaBY8hQA5dEOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKAglIVEdYoEkp7ei4iVNOTz1X4897iSixbywob23dSxBctfhUxgCPwKvDfanQTKoL0SNl5M3_4VFPKLDKkgs-p-rWi7AHZdRU4hCDNYvd56PpHqnIIoQ2s0dzg5hmO2Kgs_n9TevpRwM56JC-vd-3H59E0W6PB5juJ271JQcTdI_6gIkpmQyeqzQzvQt_V_CZ4&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

    public static final String TAG = "TAG";

    private static final String KEY_SERVICE_ID = "service_id";
    private static final String KEY_STAFF_ID = "staff_id";

    private static Response serviceResponse;
    private static Response staffResponse;


    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(SERVICE_URL)
                    .build();
            serviceResponse = client.newCall(request).execute();
            return new JSONObject(serviceResponse.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataFromWebStaff() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(STAFF_URL)
                    .build();
            staffResponse = client.newCall(request).execute();
            return new JSONObject(staffResponse.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataByIdService(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_SERVICE_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(SERVICE_URL)
                    .post(formBody)
                    .build();

            serviceResponse = client.newCall(request).execute();
            return new JSONObject(serviceResponse.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataByIdStaff(int staffId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_STAFF_ID, Integer.toString(staffId))
                    .build();

            Request request = new Request.Builder()
                    .url(STAFF_URL)
                    .post(formBody)
                    .build();

            staffResponse = client.newCall(request).execute();
            return new JSONObject(staffResponse.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


}
