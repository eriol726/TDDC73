package com.example.erik.accountregistration2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FieldAdapter fieldAdapter ;
    FormFactory formFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formFactory = (FormFactory) findViewById(R.id.FormFactory);

        fieldAdapter = new FieldAdapter(this);

        formFactory.addTextField("Username");
        formFactory.addTextField("Name");
        formFactory.addTextField("Email");
        formFactory.addAge("Age", true);
        formFactory.addPasswordFiled("Password");
        formFactory.addSubmitButton("Submit");

        //passing a new adapter to formFactory class that can hold all the fields
        formFactory.setAdapter(fieldAdapter);




    }
}
