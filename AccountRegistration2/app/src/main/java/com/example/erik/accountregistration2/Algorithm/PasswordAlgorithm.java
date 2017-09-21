package com.example.erik.accountregistration2.Algorithm;

import android.util.Log;

/**
 * Created by Erik on 2017-02-09.
 */

/**
 * This class determines how much scores each password character gives
 */

public class PasswordAlgorithm implements FieldAlgorithmInterface {
    @Override
    public boolean checkField(String fieldText) {

        if (getPasswordScore(fieldText) > 40){
            return true;
        }
        Log.d("tag", "password algorithm");
        return false;
    }

    @Override
    public int getPasswordScore(String password) {
        Log.d("tag", "getPasswordScore");
        int passwordScore = 0;
        if (!password.matches("[a-zA-Z1-9.? ]*")) {
            Log.d("tag", "found");

            passwordScore += 20;
        }


        if (!password.equals(password.toLowerCase())) {
            passwordScore += 20;
        }


        if (!password.equals(password.toUpperCase())) {
            passwordScore += 20;
        }


        if (password.matches(".*\\d+.*")) {
            passwordScore += 20;
        }

        if (password.length() >= 8) {
            passwordScore += 20;
        }

        return passwordScore;
    }




}
