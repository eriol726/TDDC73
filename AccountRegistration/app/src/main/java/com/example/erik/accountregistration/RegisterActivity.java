package com.example.erik.accountregistration;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView strengthText;
    int passwordScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editAge = (EditText) findViewById(R.id.editAge);
        final EditText editName = (EditText) findViewById(R.id.editName);
        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        strengthText = (TextView) findViewById(R.id.strengthText);
        progressBar = (ProgressBar) findViewById(R.id.passwordStrength);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editName.getText().toString().trim();
                final String username = editUsername.getText().toString().trim();
                final int age;
                if(editAge.getText().length() > 0){
                    age = Integer.parseInt(editAge.getText().toString().trim());
                }
                else{
                    age = 0;
                }

                final String password = editPassword.getText().toString().trim();

                //create a request
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // everything in here happens when the response is executed
                        //gets the string that volley has created and converts it to a jasonobject
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            boolean usernameExist = false;
                            boolean fieldIsEmpty = false;
                            boolean weakPassword = false;
                            usernameExist = jsonResponse.getBoolean("usernameExist");
                            fieldIsEmpty = jsonResponse.getBoolean("fieldEmpty");
                            weakPassword = jsonResponse.getBoolean("weakPassword");

                            Log.d("tag", "Username exist: " + usernameExist);

                            if (success && passwordScore >= 40 && !usernameExist) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                Log.d("tag", "Register ok!");
                                RegisterActivity.this.startActivity(intent);
                                Toast.makeText(getApplicationContext(), "User is registered",
                                        Toast.LENGTH_LONG).show();
                            }
                            else if (usernameExist){
                                Log.d("tag", "Username exist");
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Username exist")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else if (weakPassword){
                                Log.d("tag", "Password is too waek");
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Password is too waek")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else if (fieldIsEmpty){
                                Log.d("tag", "Some field is empty");
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Some field is empty")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else {
                                //create error message
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register faild")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            Log.d("tag", "registartion error");
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, passwordScore, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    checkPasswordStrength(s.toString());
                }
                else{
                    progressBar.setProgress(0);
                    strengthText.setText("");

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void checkPasswordStrength(String password) {

        passwordScore = 0;

        Log.d("tag", password);

        progressBar.setProgress(0);
        strengthText.setText("");
        progressBar.setScaleY(3f);
        progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));

        String currentChar = password.substring(password.length() - 1);

        //check if password contains special character
        if (!currentChar.matches("[A-Za-z1-9][^.]*")) {
            Log.d("tag", "found");

            passwordScore += 20;
        }


        if (!password.equals(password.toLowerCase())) {
            passwordScore += 20;
        }


        if (!password.equals(password.toUpperCase())) {
            passwordScore += 20;
        }


        if (password.matches(".*\\d+.*")) {
            passwordScore += 20;
        }

        if (password.length() >= 8) {
            passwordScore += 20;
        }


        if (passwordScore >= 100) {
            strengthText.setText("Strong");
            progressBar.setProgress(4);
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        } else if (passwordScore >= 80) {
            strengthText.setText("Medium");
            progressBar.setProgress(3);
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (passwordScore >= 60) {
            strengthText.setText("Weak");
            progressBar.setProgress(2);
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.MAGENTA));
        } else if (passwordScore < 60) {
            strengthText.setText("Very Weak");
            Log.d("tag", "<60: " + passwordScore);
            progressBar.setProgress(1);
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));

        }



        Log.d("tag", "Score: " + passwordScore);
        Log.d("tag", "password: " + password);


    }

}
