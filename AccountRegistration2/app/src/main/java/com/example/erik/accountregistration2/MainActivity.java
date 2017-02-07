package com.example.erik.accountregistration2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FormFactory formFactory = (FormFactory) findViewById(R.id.FormFactory);

        formFactory.addTextField("Username");
        formFactory.addTextField("Name");
        formFactory.addTextField("Age");
        formFactory.addPasswordFiled("Password");
        formFactory.addSubmitButton("Submit");
    }
}
