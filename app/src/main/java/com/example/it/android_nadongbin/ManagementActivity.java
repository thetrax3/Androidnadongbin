package com.example.it.android_nadongbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();

        /**
         * test code
         */
        //userList.add(new User("ads","asd","asd","123"));

        adapter = new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0;
            String userID, userPwd, userName, userAge;

            while(count< jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPwd = object.getString("userPwd");
                userName = object.getString("userName");
                userAge = object.getString("userAge");

                Log.d(userID, "ID: ");
                Log.d(userPwd, "Pwd: ");
                Log.d(userName, "Name: ");
                Log.d(userAge, "Age: ");

                User user = new User(userID, userPwd, userName, userAge);
                userList.add(user);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
