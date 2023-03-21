package com.example.medicalretrieval;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medicalretrieval.pojo.User;

public class HomeFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView avatar = view.findViewById(R.id.userHeadPicture);
        User user = (User) MyApplication.getInstance().application.get("user");
        assert user != null;
        String avatarUrl = user.getAvatar();

        // Use Glide to load image
        Glide.with(this)
                .load(avatarUrl)
                .error(R.drawable.default_avatar_man)
                .placeholder(R.drawable.default_avatar_man)
                .into(avatar);
        TextView userName = view.findViewById(R.id.userName);
        userName.setText(user.getAccount());

        TextView browsingHistory = view.findViewById(R.id.BrowsingHistory);
        browsingHistory.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BrowsingHistory:

                break;
        }
    }
}