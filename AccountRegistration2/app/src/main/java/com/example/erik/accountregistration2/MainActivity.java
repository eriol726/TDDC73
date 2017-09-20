package com.example.erik.accountregistration2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FieldAdapter fieldAdapter ;
    FormFactory formFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formFactory = (FormFactory) findViewById(R.id.FormFactory);

        fieldAdapter = new FieldAdapter(this);

        List<AccountParameter> params = new ArrayList<AccountParameter>();
        params.add(new AccountParameter("Username"));
        params.add(new AccountParameter("Email"));
        params.add(new AccountParameter("Age"));
        params.add(new AccountParameter("Name"));
        params.add(new AccountParameter("Password"));
        formFactory.setAdapter(params);


        //formFactory.addPasswordFiled("Password");
        formFactory.addSubmitButton("Submit");

        //passing a new adapter to formFactory class that can hold all the fields





    }
}
