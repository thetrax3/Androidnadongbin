package com.example.it.android_nadongbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
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
    private List<User> saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();
        saveList = new ArrayList<User>();

        /**
         * test code
         */
        //userList.add(new User("ads","asd","asd","123"));

        adapter = new UserListAdapter(getApplicationContext(), userList, this, saveList);
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

                User user = new User(userID, userPwd, userName, userAge);
                if(!userID.equals("admin")) {
                    userList.add(user);
                    saveList.add(user);
                }
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        EditText userSearch = findViewById(R.id.userSearch);
        userSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //텍스트가 바뀔 때 마다 실행되는 함수
                searchUser(charSequence.toString());
            }

            private void searchUser(String search) {
                userList.clear();
                for(int i =0; i<saveList.size(); i++){
                    if(saveList.get(i).getUserID().contains(search)){
                        userList.add(saveList.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
