package com.example.erik.accountregistration2.Algorithm;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Erik on 2017-09-13.
 */

public class EmailAlgorithm implements FieldAlgorithmInterface {


    public boolean checkField(String email) {


        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            //Toast.makeText(getApplicationContext(),"valid email address", Toast.LENGTH_SHORT).show();
            Log.d("tag", "too short");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getPasswordScore(String fieldText) {
        return 0;
    }
}
