package com.example.erik.accountregistration2;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by Erik on 2017-02-03.
 */

public class FormFactory extends LinearLayout {

    LinearLayout formLinearLayout;
    Button submitButton;
    EditText textView;



    private ArrayList<EditText> textFileds;
    int passwordScore = 0;
    private PasswordHolder ps;


    public FormFactory(Context context) {
        super(context);
        init(context);
    }

    public FormFactory(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FormFactory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context){

        textFileds = new ArrayList<EditText>();

        ps = new PasswordHolder(getContext());

      /*  formLinearLayout = new LinearLayout(getContext());

        RelativeLayout.LayoutParams linearLayoutParam = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        formLinearLayout.setLayoutParams(linearLayoutParam);

        linearLayoutParam.addRule(RelativeLayout.BELOW, formLinearLayout.getId());*/


        formLinearLayout = (LinearLayout)findViewById(R.id.FormFactory);
        formLinearLayout.setOrientation(LinearLayout.VERTICAL);


    }

    public void addTextField(String fieldName){

        textView = new EditText(getContext());
        textView.setHint(fieldName);
        LinearLayout.LayoutParams fieldLabellp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout linearLayoutHorizontal = new LinearLayout(getContext());
        linearLayoutHorizontal.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textView.setLayoutParams(fieldLabellp);


        linearLayoutHorizontal.addView(textView,0);

        formLinearLayout.addView(linearLayoutHorizontal);


        textFileds.add(textView);

    }

    public void addSubmitButton(String buttonLabel){
        submitButton = new Button(getContext());
        submitButton.setText(buttonLabel);

        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "click");
                fieldValidation();
            }
        });

        formLinearLayout.addView(submitButton);
    }




    public void addPasswordFiled(String hint){
        PasswordHolder passwordHolder = new PasswordHolder(getContext());
        Log.d("tag", "init password field 1");
        passwordHolder.addPasswordFieldText(hint);

        formLinearLayout.addView(passwordHolder);

    }

    public void fieldValidation(){

        passwordScore = ps.getPasswordScore();
        boolean usernameIsBlank = textView.getText().toString().equals("");

        if (passwordScore >= 40 && !usernameIsBlank) {
            //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            Log.d("tag", "Register ok!");
            //RegisterActivity.this.startActivity(intent);
            Toast.makeText(getContext(), "User is registered",
                    Toast.LENGTH_LONG).show();
        }
        else if (usernameIsBlank){
            Log.d("tag", "Some field is blank");
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Some field is blank")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
        else if ( ps.getPasswordScore() < 40 ){
            Log.d("tag", "Passwordscore: " + ps.getPasswordScore());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Password is too waek")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
        else {
            //create error message
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Register faild")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();

        }
    }


}
