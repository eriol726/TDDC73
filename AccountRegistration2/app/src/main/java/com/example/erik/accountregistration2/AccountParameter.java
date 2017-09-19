package com.example.erik.accountregistration2;

import com.example.erik.accountregistration2.Algorithm.FieldAlgorithmInterface;

/**
 * Created by Erik on 2017-09-18.
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

    public FieldAlgorithmInterface getFieldAlgorithm()
    {
        return fieldAlgorithmInterface;
    }
}
