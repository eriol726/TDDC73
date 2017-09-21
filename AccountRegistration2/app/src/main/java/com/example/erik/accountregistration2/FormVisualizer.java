package com.example.erik.accountregistration2;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.erik.accountregistration2.Algorithm.AlgorithmFactory;
import com.example.erik.accountregistration2.Algorithm.FieldAlgorithmInterface;
import com.example.erik.accountregistration2.Algorithm.TextFieldInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 2017-02-03.
 */

/**
 * This class creates a form and visualize it given the Account parameters from MainActivity.
 *
 * The fieldValidation function validates the user inputs for the Account
 */

public class FormVisualizer extends LinearLayout {

    LinearLayout formLinearLayout;
    Button submitButton;

    EditText textView;


    List<AccountParameter> params;
    List<TextFieldInput> textFieldInput;

    private ArrayList<EditText> textFileds;
    private PasswordStrengthBar ps;


    public FormVisualizer(Context context) {
        super(context);
        init(context);
    }

    public FormVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FormVisualizer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context){

        textFileds = new ArrayList<EditText>();

        ps = new PasswordStrengthBar(getContext());


        formLinearLayout = (LinearLayout)findViewById(R.id.FormVisualizer);
        formLinearLayout.setOrientation(LinearLayout.VERTICAL);
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
        PasswordStrengthBar passwordHolder = new PasswordStrengthBar(getContext());

        passwordHolder.addPasswordFieldText(hint);

        formLinearLayout.addView(passwordHolder);
    }

    public void fieldValidation(){

        boolean validPassword = ps.isValidPasswordField();

        boolean blankFields = false;
        boolean validAlgorithmFields = true;
        int invalidParamIndex = 0;

        for(int i = 0; i < textFieldInput.size(); i++){

            if(textFieldInput.get(i).getTextField().getText().toString().equals("")){

                blankFields = true;
            }
            if( params.get(i).hasAlgorithm() && !textFieldInput.get(i).isValidField()){

                validAlgorithmFields = false;
                invalidParamIndex = i;
            }

        }

        if (!blankFields && validAlgorithmFields && validPassword) {
            Log.d("tag", "blankFields: " + blankFields);
            Log.d("tag", "Register ok!");

            Toast.makeText(getContext(), "User is registered",
                    Toast.LENGTH_LONG).show();
        }

        else if (blankFields){
            Log.d("tag", "Some field is blank");
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Some field is blank")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
        else if ( !validPassword ){
            Log.d("tag", "Passwordscore: " + validPassword);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Password is too waek")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
        else if(!validAlgorithmFields){
            Log.d("tag", "blankFields: " + blankFields);
            Log.d("tag", "noValidFields: " + validAlgorithmFields);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage( params.get(invalidParamIndex).getName() + " is not valid")
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


    public void setLayout(List<AccountParameter> theAccountParameters) {

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();

        textFieldInput = new ArrayList<TextFieldInput>();

        params = new ArrayList<AccountParameter>();
        params = theAccountParameters;

        for(int i = 0; i < params.size(); i++){
            FieldAlgorithmInterface fieldAlgorithmInterface = algorithmFactory.getAlgorithm(params.get(i).getName());


            params.get(i).setAlgorithm(fieldAlgorithmInterface);
            textView = new EditText(getContext());
            textView.setHint(params.get(i).getName());
            if (params.get(i).getName().equals("Password")){

                addPasswordFiled(params.get(i).getName());
            }
            else{


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

                textFieldInput.add(new TextFieldInput(getContext(), textView, params.get(i)));
            }

            textFileds.add(textView);


        }




    }
}
