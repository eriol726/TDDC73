package com.example.erik.accountregistration2;

import com.example.erik.accountregistration2.Algorithm.FieldAlgorithmInterface;

/**
 * Created by Erik on 2017-09-18.
 */

/**
 * This class sets and returns the account parameter's field name and algorithm
 */

public class AccountParameter {
    private FieldAlgorithmInterface fieldAlgorithmInterface;
    private String text;
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

    public FieldAlgorithmInterface getFieldAlgorithm()
    {
        return fieldAlgorithmInterface;
    }
}
