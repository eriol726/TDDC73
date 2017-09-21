package com.example.erik.accountregistration2.Algorithm;

/**
 * Created by Erik on 2017-09-13.
 */

/**
 * States that determines if the parameter name belongs to an algorithm
 */

public class AlgorithmFactory {

    public FieldAlgorithmInterface getAlgorithm(String algorithmType)
    {
        if (algorithmType.equals("Email"))
            return new EmailAlgorithm();

        else if (algorithmType.equals("Password"))
            return new PasswordAlgorithm();

        else if (algorithmType.equals("Age"))
            return new AgeAlgorithm();

        return null;
    }
}
