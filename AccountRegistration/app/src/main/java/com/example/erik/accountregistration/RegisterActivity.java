package com.example.erik.accountregistration;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    ProgressBar progressBar;
    boolean upperLetter = false;
    boolean lowerLetter = false;
    boolean number = false;
    boolean specialLetter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editAge = (EditText) findViewById(R.id.editAge);
        final EditText editName = (EditText) findViewById(R.id.editName);
        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        progressBar = (ProgressBar) findViewById(R.id.passwordStrength);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editName.getText().toString();
                final String username = editUsername.getText().toString();
                final int age = Integer.parseInt(editAge.getText().toString());
                final String password = editPassword.getText().toString();

                //create a request
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        // everything in here happens when the response is executed
                        //gets the string that volley has created and converts it to a jasonobject
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else{
                                //create error message
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register faild")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name,username,age,password,responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")){
                    checkPasswordStrength(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    void checkPasswordStrength(String password){
        String specialCharacter = "!£$%&_@#?";
        int passwordScore = 0;

        Log.d("tag", password);

        String currentChar = password.substring(password.length() - 1);

        //check if password contains special character
        if(!currentChar.matches("[A-Za-z1-9][^.]*")){
            Log.d("tag", "found");

            passwordScore+=20;
        }


        if(!password.equals(password.toLowerCase()) ){
            passwordScore+=20;
        }



        if(!password.equals(password.toUpperCase()) ){
            passwordScore+=20;
        }


        if(password.matches(".*\\d+.*")){
            passwordScore+=20;
        }

        if(password.length() >= 8){
            passwordScore+=20;
        }



        if(passwordScore>=100){
            progressBar.setProgress(4);
            progressBar.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        else if(passwordScore>=80){
            Log.d("tag", "80p: " );
            progressBar.setProgress(3);
            progressBar.getProgressDrawable().setColorFilter(
                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        else if(passwordScore>=60){
            progressBar.setProgress(2);
            progressBar.getProgressDrawable().setColorFilter(
                    Color.MAGENTA, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(passwordScore<60){
            progressBar.setProgress(1);
            progressBar.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }


        Log.d("tag", "Score: " + passwordScore);


    }
}
