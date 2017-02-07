package com.example.erik.accountregistration2;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by Erik on 2017-02-03.
 */

public class FormFactory extends LinearLayout {

    LinearLayout formLinearLayout;



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

        EditText textView = new EditText(getContext());
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

    }

    public void addSubmitButton(String buttonLabel){
        Button button = new Button(getContext());
        button.setText(buttonLabel);

        formLinearLayout.addView(button);
    }


    public void addPasswordFiled(String hint){
        PasswordHolder passwordHolder = new PasswordHolder(getContext());
        Log.d("tag", "init password field 1");
        passwordHolder.addPasswordFieldText(hint);

        formLinearLayout.addView(passwordHolder);
    }
}
