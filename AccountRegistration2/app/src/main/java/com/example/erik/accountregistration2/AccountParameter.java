package com.example.erik.accountregistration2;

import com.example.erik.accountregistration2.Algorithm.FieldAlgorithmInterface;
import com.example.erik.accountregistration2.Algorithm.TextFieldInput;

/**
 * Created by Erik on 2017-09-18.
 */

/**
 * This class sets and returns the account parameter's field name and algorithm
 */

public class AccountParameter {

    private FieldAlgorithmInterface fieldAlgorithmInterface;
    private String text;
    static boolean validAccount;
    public AccountParameter(String theText)
    {
        text = theText;
    }
    public String getName()
    {
        return text;
    }

    public void setAlgorithm(FieldAlgorithmInterface theFieldAlgorithmInterface)
    {
        fieldAlgorithmInterface = theFieldAlgorithmInterface;
    }

    public boolean hasAlgorithm()
    {
        if (fieldAlgorithmInterface != null)
            return true;

        return false;
    }

    public void setStatus(boolean status)
    {
        if (hasAlgorithm())
        {
            if (status) // if feedback is ok
                validAccount = true;

            else
                validAccount = false;
        }

    }

    public boolean getStatus(){
        return validAccount;
    }

    public FieldAlgorithmInterface getFieldAlgorithm()
    {
        return fieldAlgorithmInterface;
    }
}
