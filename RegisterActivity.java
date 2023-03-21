package com.example.medicalretrieval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button register;
    private EditText registerAccount;
    private EditText registerPassword;
    private EditText registerRepeatPassword;
    private EditText registerEmail;
    private EditText registerPhone;
    private RadioGroup sexGroup;
    private int sex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.button_register);
        register.setOnClickListener(this);

        registerAccount = findViewById(R.id.register_account);
        registerPassword = findViewById(R.id.register_password);
        registerRepeatPassword = findViewById(R.id.register_repeat_password);
        registerEmail = findViewById(R.id.register_email);
        registerPhone = findViewById(R.id.register_phone);

        //性别
        sexGroup = findViewById(R.id.sex_group);
        sexGroup.check(R.id.sex_btn_man);
        sexGroup.setOnCheckedChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.button_register:
                HttpRegister();
                break;
        }
    }

    private void HttpRegister() {
        new Thread(()->{
            JSONObject object = new JSONObject();
            try {
                object.put("account",registerAccount.getText().toString());
                object.put("password",registerPassword.getText().toString());
                object.put("email",registerEmail.getText().toString());
                object.put("telephone",registerPhone.getText().toString());
                object.put("permissionGroupId",0);
                object.put("avatar","默认头像");
                object.put("disabled",0);
                object.put("sex",sex);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, "你的json");
            RequestBody requestBody = RequestBody.create(JSON,object.toString());
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.43.144:8080/user/")
                    .post(requestBody)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*String Data = null;
            try {
                assert Objects.requireNonNull(response).body() != null;
                Data = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(Data);*/

        }).start();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.sex_btn_man:
                sex = 0;
                break;
            case R.id.sex_btn_female:
                sex = 1;
                break;
        }
    }
}