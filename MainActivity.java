package com.example.medicalretrieval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText account = findViewById(R.id.input_account);
        EditText password = findViewById(R.id.input_password);

        Button login = findViewById(R.id.login);
        TextView register = findViewById(R.id.register);
        TextView forgetPassword = findViewById(R.id.forget_password);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                break;
            case R.id.register:
                break;
            case R.id.forget_password:
                break;
        }

    }
}