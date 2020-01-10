package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;


public class Admin extends AppCompatActivity {

    Timer timeout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        timeout = new Timer();
        timeout.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent homePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(homePage);
                finish();
            }
        }, 15000);
    }
}
