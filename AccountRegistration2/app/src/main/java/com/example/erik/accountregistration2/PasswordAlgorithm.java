package com.example.erik.accountregistration2;

import android.util.Log;

/**
 * Created by Erik on 2017-02-09.
 */

public class PasswordAlgorithm implements PasswordInterface {
    @Override
    public int calculateScore(String password) {
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
