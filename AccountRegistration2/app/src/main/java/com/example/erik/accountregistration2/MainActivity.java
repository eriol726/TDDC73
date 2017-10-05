package com.example.erik.accountregistration2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.EventLog.Event;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Here is the fields added to the form. If a field should have an algorithm, a new factory class
 * has to be created and a new state in AlgorithmFactory
 */

public class MainActivity extends AppCompatActivity implements  MyListener  {


    FormVisualizer formVisualizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formVisualizer = (FormVisualizer) findViewById(R.id.FormVisualizer);
        MyButton m = new MyButton(this);


        List<AccountParameter> params = new ArrayList<AccountParameter>();
        params.add(new AccountParameter("Username"));
        params.add(new AccountParameter("Email"));
        params.add(new AccountParameter("Age"));
        params.add(new AccountParameter("Name"));
        params.add(new AccountParameter("Password"));
        formVisualizer.setLayout(params);

        formVisualizer.addSubmitButton("Submit");


        //FormVisualizer formVisualizer2 = new FormVisualizer(getApplicationContext());
        Log.d("tag", formVisualizer.submitButton.getText().toString());
        formVisualizer.getResponseMessage();

        //Log.d("tag", "Acc:  " + formVisualizer.accountRegistration.accountParameters.get(0).getName().toString());

    }



    @Override
    public boolean callback(MyButton view, String result) {

        Log.d("tag", result);
        return true;
    }
}
