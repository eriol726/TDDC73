package com.example.erik.accountregistration2;

import android.util.Log;

/**
 * Created by Erik on 2017-10-05.
 */

public class MyButton {
    MyListener ml;

    // constructor
    MyButton(MyListener ml) {
        this.ml = ml;
    }

    public boolean MyLogicToIntimateOthere() {
        Log.d("tag", "tjaaa");

        ml.callback(this, "success");
        return true;
    }
}
