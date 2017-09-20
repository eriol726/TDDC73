package com.example.erik.accountregistration2.Algorithm;

/**
 * Created by Erik on 2017-02-07.
 */

public interface FieldAlgorithmInterface {
    boolean checkField(String fieldText);

    int getPasswordScore(String fieldText);

    boolean getValidPassword(String s);
}
