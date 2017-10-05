package com.example.erik.accountregistration2;

import java.util.List;

/**
 * Created by Erik on 2017-10-05.
 */

public class Account {

    List<AccountParameter> accountParameters;
    public Account(List<AccountParameter> theAccountParameters)
    {
        accountParameters = theAccountParameters;
    }
    public List<AccountParameter> getAccountParameters()
    {
        return accountParameters;
    }
}
