package com.example.erik.accountregistration2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FieldAdapter fieldAdapter ;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "tjaaa hej");


        //formFactory = (FormFactory) findViewById(R.id.FormFactory);
        linearLayout = new LinearLayout(this);
        List<AccountParameter> params = new ArrayList<AccountParameter>();
        params.add(new AccountParameter("Email"));

        FormFactory formFactory = new FormFactory(this, params);

    //    fieldAdapter = new FieldAdapter(this);
/*
        formFactory.addTextField("Username");
        formFactory.addTextField("Name");
        formFactory.addTextField("Email");
        formFactory.addAge("Age", true);
        formFactory.addPasswordFiled("Password");
        formFactory.addSubmitButton("Submit");

        //passing a new adapter to formFactory class that can hold all the fields
        formFactory.setAdapter(fieldAdapter);*/
        linearLayout.addView(formFactory);
        setContentView(linearLayout);



    }
}
