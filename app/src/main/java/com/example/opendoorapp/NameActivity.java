package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

public class NameActivity extends AppCompatActivity {
    
    String name;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        
        name = "alvee";
        User.userName = name;
        
    }
    
    
    
    /**
     * Takes user to next Activity - 'Name Activity'
     * @param view - View object - Button object in this scenario
     * by Alvee
     *
     */
    public void whatDoINeedHelpBtnClicked(View view){

        Intent name = new Intent(NameActivity.this, ServicesActivity.class);
        startActivity(name);
        finish();
    } // checkInBtnClicked
}
