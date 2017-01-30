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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView strengthText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


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
                final String name = editName.getText().toString();
                final String username = editUsername.getText().toString();
                final int age = Integer.parseInt(editAge.getText().toString());
                final String password = editPassword.getText().toString();

                //create a request
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // everything in here happens when the response is executed
                        //gets the string that volley has created and converts it to a jasonobject
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
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

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void checkPasswordStrength(String password) {
        String specialCharacter = "!£$%&_@#?";
        int passwordScore = 0;

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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
