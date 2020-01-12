/**
 * By Arnold Gihozo
 */

package com.example.opendoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        name = "alvee";
        User.userName = name;


        Button submitBtn = (Button) findViewById(R.id.nameContinue);
        final EditText nameTxt = (EditText) findViewById(R.id.userName);
        submitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameTxt.getText().toString();
                currentUser.setName(name);
            }
        });

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
