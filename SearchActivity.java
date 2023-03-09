package com.example.medicalretrieval;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.room.util.StringUtil;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import okhttp3.internal.Util;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageFilterButton btnSearch;
    private EditText tvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tvSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                String searchContent = tvSearch.getText().toString().trim();
                if("".equals(searchContent)){
                    Snackbar.make(this,findViewById(R.id.search_root),"请输入关键词",Snackbar.LENGTH_SHORT)

                            .show();
                }
                break;
        }
    }
}