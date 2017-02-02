package com.example.erik.accountregistration;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 2017-01-26.
 */

public class LoginRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://192.168.0.102/accountActivation/Login.php";
    private Map<String, String> params;

    /**
     *
     * @param username
     * @param password
     * @param listener
     */
    public LoginRequest(String username, String password, Response.Listener<String> listener){
        //send data to php
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        //pust the datat to the hashMap
        params.put("username", username);
        params.put("password", password);
    }

    //when the request is executed, volley will call getParams and return params
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
