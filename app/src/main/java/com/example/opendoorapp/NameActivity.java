/**
 * By Arnold Gihozo
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;


public class NameActivity extends AppCompatActivity {

    private User currentUser;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Button submitBtn = (Button) findViewById(R.id.whatINeed);
        final EditText nameTxt = (EditText) findViewById(R.id.nameTextView);
        submitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameTxt.getText().toString();
                //currentUser.setName(name);
            }
        });

    }
    }

