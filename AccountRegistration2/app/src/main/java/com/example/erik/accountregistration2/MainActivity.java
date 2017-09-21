package com.example.erik.accountregistration2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Here is the fields added to the form. If a field should have an algorithm, a new factory class
 * has to be created and a new state in AlgorithmFactory
 */

public class MainActivity extends AppCompatActivity {


    FormVisualizer formVisualizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formVisualizer = (FormVisualizer) findViewById(R.id.FormVisualizer);



        List<AccountParameter> params = new ArrayList<AccountParameter>();
        params.add(new AccountParameter("Username"));
        params.add(new AccountParameter("Email"));
        params.add(new AccountParameter("Age"));
        params.add(new AccountParameter("Name"));
        params.add(new AccountParameter("Password"));
        formVisualizer.setLayout(params);


        formVisualizer.addSubmitButton("Submit");

    }
}
