package com.example.medicalretrieval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.medicalretrieval.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private List<ImageButton> navButtonList;
    private ViewPager viewPager;
    private ImageButton navSearch;
    private ImageButton navHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.vp);
        List<Integer> list = new ArrayList<>();
        list.add(R.layout.activity_search);
        list.add(R.layout.activity_home);
        navButtonList = new ArrayList<>();
        navSearch = findViewById(R.id.nav_search);
        navHome = findViewById(R.id.nav_home);
        navSearch.setOnClickListener(this);
        navButtonList.add((ImageButton) findViewById(R.id.nav_search));
        navButtonList.add((ImageButton) findViewById(R.id.nav_home));
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,list);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navButtonList.get(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_search:
                viewPager.setCurrentItem(0);
                navSearch.setSelected(true);
                break;
            case R.id.nav_home:
                viewPager.setCurrentItem(1);
                navSearch.setSelected(true);
                break;
        }
    }
}