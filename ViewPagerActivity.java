package com.example.medicalretrieval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.medicalretrieval.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ViewPager viewPager = findViewById(R.id.vp);
        List<Integer> list = new ArrayList<>();
        list.add(R.layout.activity_search);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,list);

        viewPager.setAdapter(adapter);

    }
}