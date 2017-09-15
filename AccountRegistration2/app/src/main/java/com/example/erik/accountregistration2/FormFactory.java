package com.example.erik.accountregistration2;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 2017-02-03.
 */

public class FormFactory extends LinearLayout {

    Context context;
    LinearLayout formLinearLayout;
    Button submitButton;
    EditText textView;
    FieldAdapter fieldAdapter;
    boolean active = false;

    List<TextView> textViewList;
    List<LayoutParams> layoutTextViewList;
    List<LayoutParams> layoutEditTextList;
    List<LayoutParams> layoutFeedbackList;

    List<InteractiveField> interactiveFields;
    List<AccountParameter> params;

    private ArrayList<EditText> textFileds;
    int passwordScore = 0;
    private InteractiveField interactiveField;


    public FormFactory(Context theContext, List<AccountParameter> theAccountParameters) {

        super(theContext);
        Log.d("tag", "first constructor");
        params = new ArrayList<AccountParameter>();
        params = theAccountParameters;

        init();
    }

    public FormFactory(Context context, AttributeSet attrs) {

        super(context, attrs);
        Log.d("tag", "second constructor");
        init();
    }

    public FormFactory(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        init();
    }


    public void init(){

        textViewList = new ArrayList<TextView>();
        //interactiveField = new InteractiveField(context);
        layoutTextViewList = new ArrayList<LayoutParams>();
        layoutEditTextList = new ArrayList<LayoutParams>();
        interactiveFields = new ArrayList<InteractiveField>();

        textFileds = new ArrayList<EditText>();



        fieldAdapter = new FieldAdapter(context);



        for (int i = 0; i < params.size(); i++)
        {
            //textViewList.add(new TextView(context));
            //textViewList.get(i).setText(params.get(i).getName());
            //interactiveField = new InteractiveField(context, params.get(i));
            //interactiveFields.add(new InteractiveField(context, params.get(i)));
            setTextView(params.get(i).getName());

        }

   //    formLinearLayout = (LinearLayout)findViewById(R.id.FormFactory);
  //     formLinearLayout.setOrientation(LinearLayout.VERTICAL);


    }

    public void addTextField(String fieldName){

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


       // textFileds.add(textView);
       // fieldAdapter.addFiled(textView);
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



/*
    public void addPasswordFiled(String hint){
        InteractiveField passwordHolder = new InteractiveField(context);
        Log.d("tag", "init password field 1");
        passwordHolder.addPasswordFieldText(hint);

        formLinearLayout.addView(passwordHolder);
        fieldAdapter.addFiled(passwordHolder);

    }*/

    public void fieldValidation(){

        passwordScore = interactiveField.getPasswordScore();

        boolean blankFields = false;

        //check if some field is blank
        for(int i = 0; i < textFileds.size(); i++){
            if(textFileds.get(i).getText().toString().equals("")){
                Log.d("tag", "Label: " + textFileds.get(i).getHint());
                blankFields = true;
            }
        }
        //boolean usernameIsBlank = textView.getText().toString().equals("");

        Context c = getContext();

        //MainActivity main = new MainActivity();
        //Log.d("tag",  "field" + main.formFactory.getChildAt(0));

        if (passwordScore >= 40 && !blankFields) {
            //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            Log.d("tag", "Register ok!");
            //RegisterActivity.this.startActivity(intent);
            Toast.makeText(getContext(), "User is registered",
                    Toast.LENGTH_LONG).show();
        }
        else if (blankFields){
            Log.d("tag", "Some field is blank");
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Some field is blank")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
        else if(!textFileds.get(3).getText().toString().matches("[0-9]+") && active){
            Log.d("tag", "age must be numbers");
        }
        else if ( interactiveField.getPasswordScore() < 40 ){
            Log.d("tag", "Passwordscore: " + interactiveField.getPasswordScore());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Password is too waek")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }

        else {
            //create error message
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Register faild")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();

        }
    }


    public void setAdapter(FieldAdapter fields) {
        fieldAdapter = fields;
    }
}
