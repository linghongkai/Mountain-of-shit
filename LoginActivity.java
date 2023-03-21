package com.example.medicalretrieval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalretrieval.pojo.User;
import com.example.medicalretrieval.utils.Result;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText account;
    private EditText password;
    private TextView register;
    private TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = findViewById(R.id.input_account);
        password = findViewById(R.id.input_password);

        Button login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        forgetPassword = findViewById(R.id.forget_password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.login:
                String acc = account.getText().toString();
                String pass = password.getText().toString();
                login(acc,pass);
                break;
            case R.id.register:
                intent = new Intent(this,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                System.out.println("注册被点击");
                break;
            case R.id.forget_password:
                break;
        }

    }

    private void login(String acc,String pass) {
        new Thread(() ->{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.43.144:8080/user/?account="+acc+"&password="+pass+"")
                    .get()
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String Data = response.body().string();

                Result result;
                result = new Gson().fromJson(Data,Result.class);
                if (result.getCode()==1){
                    JsonElement jsonElement = JsonParser.parseString(Data);
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    User user = new Gson().fromJson(jsonObject.getAsJsonObject("data"),User.class);
                    MyApplication.getInstance().application.put("user",user);
                    Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
    private void showData(String data){
        runOnUiThread(() -> {
            Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //登录成功
            System.out.println(data);
            MyApplication.getInstance().application.put("account","");//登录信息

            startActivity(intent);

        });
    }

}