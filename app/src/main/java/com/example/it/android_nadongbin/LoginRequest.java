package com.example.it.android_nadongbin;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest  extends StringRequest {
    final static private String URL = "http://thetrax3.cafe24.com/Login2.php";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPwd, Response.Listener<String> listener){
        super(Method.POST, URL, listener , null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPwd",userPwd);
    }

    public Map<String, String> getParams(){
        return parameters;
    }
}
