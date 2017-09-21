package com.example.erik.accountregistration2.Algorithm;

/**
 * Created by Erik on 2017-02-07.
 */

/**
 *  Validation functions for each algorithm
 */

public interface FieldAlgorithmInterface {
    boolean checkField(String fieldText);

    int getPasswordScore(String fieldText);

}
