package com.example.erik.accountregistration2.Algorithm;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.erik.accountregistration2.AccountParameter;
import com.example.erik.accountregistration2.R;


/**
 * Created by Erik on 2017-09-18.
 */

/**
 * This class sends the input characters form the fields to its belonging param-algorithm
 */

public class TextFieldInput extends LinearLayout {


    public boolean validField = false;

    public EditText editText;
    AccountParameter accountParameter;
    public TextFieldInput(Context theContext, EditText theEditText, AccountParameter theAccountParameter ){
        super(theContext);
        editText = theEditText;
        accountParameter = theAccountParameter;
        init(theContext);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.registartion_form, this);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (accountParameter.hasAlgorithm()) {
                    Log.d("tag", "has algorithm");

                    validField = accountParameter.getFieldAlgorithm().checkField(s.toString());


                }
                else{
                    Log.d("tag", "has no algorithm");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



    public EditText getTextField(){

        return editText;
    }

    public boolean isValidField(){

        return validField;
    }


}
