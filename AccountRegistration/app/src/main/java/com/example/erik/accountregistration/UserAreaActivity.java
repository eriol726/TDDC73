package com.example.erik.accountregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText editAge = (EditText) findViewById(R.id.editAge);
        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final TextView welcomeMessenger = (TextView) findViewById(R.id.textViewWelcomeMessage);

        //Retrieve the data from the LoginActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        String message =  "Welcome " + name + " to your user area";
        welcomeMessenger.setText(message);
        editUsername.setText(username);
        editAge.setText(age +"");

    }
}
