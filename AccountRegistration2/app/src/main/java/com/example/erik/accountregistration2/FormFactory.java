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

public class FormFactory extends LinearLayout {

    LinearLayout formLinearLayout;
    Button submitButton;
    EditText textView;
    EditText textView2;
    FieldAdapter fieldAdapter;
    boolean active = false;

    List<AccountParameter> params;
    List<TextFieldInput> textFieldInput;

    private ArrayList<EditText> textFileds;
    int passwordScore = 0;
    private PasswordStrengthBar ps;


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

        ps = new PasswordStrengthBar(getContext());

        fieldAdapter = new FieldAdapter(context);

       // params = new ArrayList<AccountParameter>();
        //textFieldInput = new ArrayList<TextFieldInput>();

        //textFieldInput.add(new TextFieldInput(context));

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






        fieldAdapter.addFiled(textView);

        setTextView(fieldName);




    }
    public void addAge(String fieldName, boolean activeParam){
        active = activeParam;
        setTextView(fieldName);


    }


    public void setTextView(String fieldName){
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

        //TextFieldInput textFieldInput = new TextFieldInput(getContext(), textView, params.get(0));
        //textFieldInput.addTextField(fieldName);


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
        fieldAdapter.addFiled(submitButton);
    }




    public void addPasswordFiled(String hint){
        PasswordStrengthBar passwordHolder = new PasswordStrengthBar(getContext());
        Log.d("tag", "init password field 1");
        passwordHolder.addPasswordFieldText(hint);

        formLinearLayout.addView(passwordHolder);
        fieldAdapter.addFiled(passwordHolder);


    }

    public void fieldValidation(){

        boolean validPassword = ps.isValidPasswordField();

        boolean blankFields = false;
        boolean validAlgorithmFields = false;

        //check if some field is blank

        for(int i = 0; i < textFieldInput.size(); i++){
            Log.d("tag", "InputText: " + textFieldInput.get(i).getTextField().getText().toString().equals("")+ "\n");
            if(textFieldInput.get(i).getTextField().getText().toString().equals("")){
                Log.d("tag", "Label: " + textFieldInput.get(i).getTextField().getText()+ "\n");
                blankFields = true;
            }
            if( params.get(i).hasAlgorithm() && textFieldInput.get(i).isValidField()){
                Log.d("tag", "validAlgorithmFields:" + textFieldInput.get(i).getTextField().getText() + ": " + textFieldInput.get(i).isValidField() + "\n");
                validAlgorithmFields = true;
            }

        }

        if (!blankFields && validAlgorithmFields && validPassword) {
            Log.d("tag", "blankFields: " + blankFields);
            Log.d("tag", "Register ok!");
            //RegisterActivity.this.startActivity(intent);
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
            builder.setMessage("Email is not valid")
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


    public void setAdapter(List<AccountParameter> theAccountParameters) {

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();

        textFieldInput = new ArrayList<TextFieldInput>();

        params = new ArrayList<AccountParameter>();
        params = theAccountParameters;

        for(int i = 0; i < params.size(); i++){
            FieldAlgorithmInterface fieldAlgorithmInterface = algorithmFactory.getAlgorithm(params.get(i).getName());


            params.get(i).setAlgorithm(fieldAlgorithmInterface);
            textView2 = new EditText(getContext());
            textView2.setHint(params.get(i).getName());
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

                textView2.setLayoutParams(fieldLabellp);


                linearLayoutHorizontal.addView(textView2,0);


                formLinearLayout.addView(linearLayoutHorizontal);

                textFieldInput.add(new TextFieldInput(getContext(), textView2, params.get(i)));
            }

            textFileds.add(textView2);







        }




    }
}
