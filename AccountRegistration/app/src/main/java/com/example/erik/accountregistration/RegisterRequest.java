package com.example.erik.accountregistration;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 2017-01-26.
 */

public class RegisterRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://192.168.0.102/accountActivation/Register.php";
    //private static final String REGISTER_REQUEST_URL = "http://accountregistration.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, int age, String password, int passwordscore, Response.Listener<String> listener){
        //send data to php
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        //put the data to the hashMap
        Log.d("tag", "passwordScore: " + passwordscore);
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("passwordScore", passwordscore + "");
        params.put("age", age + "");
    }

    //when the request is executed, volley will call getParams and return params
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
