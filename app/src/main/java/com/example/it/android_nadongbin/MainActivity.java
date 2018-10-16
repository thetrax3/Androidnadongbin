package com.example.it.android_nadongbin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idView = (TextView) findViewById(R.id.idView);
        TextView pwdView = (TextView) findViewById(R.id.pwdView);
        TextView welcomeView = (TextView) findViewById(R.id.welcomeView);
        Button mngBtn = (Button) findViewById(R.id.mngBtn);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPwd = intent.getStringExtra("userPwd");

        String msg = "환영합니다 " + userID +"님!";

        idView.setText(userID);
        pwdView.setText(userPwd);
        welcomeView.setText(msg);

        /**
         * 관리자만 회원관리 버튼이 보이도록 설정
         */
        if(!userID.equals("admin")){
            mngBtn.setVisibility(View.GONE);
        }

        mngBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://thetrax3.cafe24.com/List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!=null){
                    stringBuilder.append(temp +"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            intent.putExtra("userList", result);
            MainActivity.this.startActivity(intent);

        }
    }
}
