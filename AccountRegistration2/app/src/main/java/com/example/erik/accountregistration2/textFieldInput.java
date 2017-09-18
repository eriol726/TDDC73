package com.example.erik.accountregistration2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by Erik on 2017-09-18.
 */

public class TextFieldInput extends LinearLayout {

    //Context context;

    public EditText editText;
    public TextFieldInput(Context theContext, EditText theEditText){
        super(theContext);
        editText = theEditText;
        //context = theContext;
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
                Log.d("tag", "init TextFieldInput");
                checkText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void addTextField(String txt){
        editText.setHint(txt);
    }

    public EditText getTextField(){
        return editText;
    }

    void checkText(String text){
        Log.d("tag", "check");
    }
}
