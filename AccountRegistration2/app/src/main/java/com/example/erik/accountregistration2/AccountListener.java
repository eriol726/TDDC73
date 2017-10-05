package com.example.erik.accountregistration2;

import android.view.View;

/**
 * Created by Erik on 2017-10-03.
 */

public interface AccountListener {
    void setAccountStatus(boolean theStatus);

    boolean getAccountStatus();

    void callback(View view, String result);

}
