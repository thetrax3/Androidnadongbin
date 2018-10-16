package com.example.it.android_nadongbin;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.activity_user, null);
        TextView userID = (TextView) v.findViewById(R.id.userID);
        TextView userPwd = (TextView) v.findViewById(R.id.userPwd);
        TextView userName = (TextView) v.findViewById(R.id.userName);
        TextView userAge = (TextView) v.findViewById(R.id.userAge);

        userID.setText(userList.get(i).getUserID());
        userPwd.setText(userList.get(i).getUserPwd());
        userName.setText(userList.get(i).getUserName());
        userAge.setText(userList.get(i).getUserAge());
        v.setTag(userList.get(i).getUserID());

        Log.d(userList.get(i).getUserID(), "ID: ");
        Log.d(userList.get(i).getUserPwd(), "Pwd: ");
        Log.d(userList.get(i).getUserName(), "Name: ");
        Log.d(userList.get(i).getUserAge(), "Age: ");

        return v;
    }
}