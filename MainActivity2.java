package com.example.medicalretrieval;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getOkhttp();
    }

    private void getOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client =new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    String Data = response.body().string();
//                    showData(Data);
                    showData(Data);
                    System.out.println("成功~~~~~");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showData(String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = findViewById(R.id.tv);
                tv.setText(data);
            }
        });
    }


}