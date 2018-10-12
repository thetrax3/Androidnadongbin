package com.example.it.android_nadongbin;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    final static private String URL = "http://thetrax3.cafe24.com/Register-2.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPwd, String userName, String userAge, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();//파라미터 초기화
        parameters.put("userID", userID);
        parameters.put("userPwd", userPwd);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge);
        Log.d("registerRequest2", "123");
    }

    @Override
    public Map<String, String> getParams(){
        Log.d("registerRequest3", "123");
        return parameters;
    }
}
