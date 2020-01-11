package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

public class NameActivity extends AppCompatActivity {
    
    //User user;
    String name;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        
        name = "alvee";
        
        
    }
    
    
    
    /**
     * Takes user to next Activity - 'Name Activity'
     * @param view - View object - Button object in this scenario
     * by Alvee
     *
     */
    public void whatDoINeedHelpBtnClicked(View view){
        User user = new User();
        user.setName(name);
        Intent name = new Intent(NameActivity.this, ServicesActivity.class);
        Bundle data = new Bundle();
        data.putSerializable("data", user);
        name.putExtras(data);
        
        startActivity(name);
        finish();
    } // checkInBtnClicked
}
