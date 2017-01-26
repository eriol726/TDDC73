package com.example.erik.accountregistration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 2017-01-26.
 */

public class RegisterRequest extends StringRequest{
    private static final String REGISTER_REGUEST_URL = "";
    private Map<String, String> params;

    public RegisterRequest(String name, String Username, int age, String password, Response.Listener<String> listener){
        //send data to php
        super(Method.POST, REGISTER_REGUEST_URL, listener, null);
        params = new HashMap<>();
        //pust the datat to the hashMap
        params.put("name", name);
        params.put("username", Username);
        params.put("password", password);
        params.put("age", age + "");
    }

    //when the request is executed, volley will call getParams and return params
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
