package com.example.erik.accountregistration2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


/**
 * Created by Erik on 2017-09-18.
 */

public class textFieldInput extends EditText {

    Context context;
    public textFieldInput(Context theContext){
        super(theContext);
        context = theContext;
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }




}
