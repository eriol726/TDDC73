package com.example.erik.accountregistration2;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 2017-10-05.
 */

public class AccountRegistration {
    List<AccountParameter> params;
    Context context;
    List<AccountParameter> accountParameters;
    public AccountRegistration(Context theContext, List<AccountParameter> theParams)
    {
        context = theContext;
        params = new ArrayList<AccountParameter>();
        params = theParams;
    }

    public void createAccount()
    {
        String text;
        newAccount(params);
        text = "Account successfully created";


        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    public Account newAccount( List<AccountParameter> params)
    {
        return new Account(params);

    }
}
