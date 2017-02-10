package com.example.erik.accountregistration2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FieldAdapter fieldAdapter = new FieldAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FormFactory formFactory = (FormFactory) findViewById(R.id.FormFactory);

        List<FieldAdapter> params = new ArrayList<FieldAdapter>();

        formFactory.addTextField("Username");
        formFactory.addTextField("Name");
        formFactory.addTextField("Age");
        formFactory.addPasswordFiled("Password");
        formFactory.addSubmitButton("Submit");

        //fieldAdapter.put()
    }
}
