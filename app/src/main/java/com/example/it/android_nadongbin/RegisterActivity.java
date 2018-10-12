package com.example.it.android_nadongbin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText idEdit;
    private EditText pwdEdit;
    private EditText nameEdit;
    private EditText ageEdit;
    private String tag;
    private ArrayList<EditText> textList;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idEdit = (EditText) findViewById(R.id.idEdit);
        pwdEdit = (EditText) findViewById(R.id.pwdEdit);
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        ageEdit = (EditText) findViewById(R.id.ageEdit);

        textList = new ArrayList<>();

        textList.add(idEdit);
        textList.add(pwdEdit);
        textList.add(nameEdit);
        textList.add(ageEdit);

        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String userID = idEdit.getText().toString();
        final String userPwd = pwdEdit.getText().toString();
        final String userName = nameEdit.getText().toString();
        final String userAge = ageEdit.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /**
                 * 회원가입 버튼을 누른 후 인터넷이 실행되며
                 * response를 받아 저장하는 클래스
                 * php의 결과값이 {"success":true} 이어야 접근가능
                 */
                Log.d("try", "test1");
                try {
                    Log.d("try", "test2");
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.d("try", String.valueOf(success));
                    if (success) {
                        Log.d(userID, "LoginActivity1: ");
                        builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Enable")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                })
                                .create();
                        builder.show();


                        //new DialogDismisser();
                    } else {
                        Log.d(userID, "LoginActivity2: ");
                        builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("UnEnable")
                                .setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(userID, userPwd, userName, userAge, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(registerRequest);//registerRequest에서 받은 정보를 추가하는 부분

        Log.d(userID, "LoginActivity3: ");
    }
    public class DialogDismisser{
        public void dismiss(DialogInterface d){
            if(d==null)return;
            try{
                if(d instanceof  AlertDialog){
                    if(((AlertDialog) d).isShowing())
                        ((AlertDialog)d).dismiss();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
