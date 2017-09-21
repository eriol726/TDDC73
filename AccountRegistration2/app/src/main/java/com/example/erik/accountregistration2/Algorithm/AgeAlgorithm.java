package com.example.erik.accountregistration2.Algorithm;

import android.util.Log;

/**
 * Created by Erik on 2017-09-21.
 */

/**
 * Simple algorithm that determines if age is an number or not
 */

public class AgeAlgorithm implements FieldAlgorithmInterface {
    @Override
    public boolean checkField(String age) {

        try {
            int num = Integer.parseInt(age);
            Log.d("tag",num+" is a number");
            return true;
        } catch (NumberFormatException e) {
            Log.d("tag",age+" is not a number");
            return false;
        }

    }

    @Override
    public int getPasswordScore(String fieldText) {
        return 0;
    }
}
