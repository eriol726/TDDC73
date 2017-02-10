package com.example.erik.accountregistration2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Erik on 2017-02-04.
 */

public class PasswordHolder extends LinearLayout{
    ProgressBar progressBar;
    TextView strengthText;
    EditText editPassword;
    PasswordAlgorithm passwordAlgorithm;
    static int passwordScore = 0;


    public PasswordHolder(Context context) {
        super(context);
        init(context);
    }

    public PasswordHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.registartion_form, this);

        Log.d("tag", "init password field");

        editPassword = (EditText) findViewById(R.id.editPassword);
        strengthText = (TextView) findViewById(R.id.textStrength);
        progressBar = (ProgressBar) findViewById(R.id.passwordStrength);

        passwordAlgorithm = new PasswordAlgorithm();

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



        Log.d("tag", password);

        progressBar.setProgress(0);
        strengthText.setText("");
        progressBar.setScaleY(3f);
        progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));

        passwordScore = passwordAlgorithm.calculateScore(password);

        Log.d("tag", "passwordScore: " + passwordScore);
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

    public int getPasswordScore(){
        Log.d("tag", "getPassword: " + passwordScore);
        return passwordScore;

    }


    public void addPasswordFieldText(String txt){
        editPassword.setHint(txt);
    }
}
