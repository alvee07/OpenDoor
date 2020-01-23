/**
 * JSONParser.java
 * by Arnold Gihozo
 * <p>
 * Code by admin from The Crazy Coders Club.
 * followed their tutorial which is why there is a class within a class found from:
 * https://www.crazycodersclub.com/android/using-google-spread-sheet-as-database-for-android-application-part-1/
 */

package com.example.opendoorapp.parser;

import android.util.Log;

import androidx.annotation.NonNull;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class JSONParser {

    private static final String MAIN_URL = "https://script.googleusercontent.com/a/macros/ualberta.ca/echo?user_content_key=yoc7ZdiarCPaAwRkuiaGOGnK0c2AZBrI3BEnHikxCEavkJNhBhzsL-TfBMMkdL4O-t_PSbOw7lFpPe5pWubcrZDj_8nz7_nUOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKAglIVEdYoEkp7ei4iVNOTz1X4897iSixbywob23dSxBctfhUxgCPwKvDfanQTKoL0SNl5M3_4VFPKLDKkgs-p-rWi7AHZdRU4hCDNYvd56PsAhh98Kdr6hECJPmeWG8-k7i9bsdtc5LRMIUgxK1xuXLPmk-GWZxoCoaGK_iiRLzKgIkpmQyeqzQzvQt_V_CZ4&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
    private static final String WORKER_URL = "https://script.googleusercontent.com/macros/echo?user_content_key=Jk3B9x3mLwDbMF-4xkn74VPG2nKQmb-Qi_8HDIYPAqaYodEq_JIgm1GoU4qUMumgb_0SR6QwpxyzVWGxcYhA7ZZirmSeNiFEOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwU4Jh3saR_QUxWX7Hv0nwUoI4vZ9W0mqiIxPqLBbmnQlGx62JTQzhCEsZqgFgD0q755y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
    public static final String TAG = "TAG";

    private static final String KEY_USER_ID = "user_id";

    private static Response response;

    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataFromWeb2() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(WORKER_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}
