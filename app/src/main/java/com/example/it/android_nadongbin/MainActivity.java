package com.example.it.android_nadongbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idView = (TextView) findViewById(R.id.idEdit);
        TextView pwdView = (TextView) findViewById(R.id.pwdEdit);
        TextView welcomeView = findViewById(R.id.welcomeView);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPwd = intent.getStringExtra("userPwd");
        String msg = "환영합니다" + userID +"님!";

        idView.setText(userID);
        pwdView.setText(userPwd);
        welcomeView.setText(msg);
    }
}
