package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Rational;

public class ReturnDesktopActivity extends AppCompatActivity {

    private DesktopRecevier desktopRecevier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_desktop);

        desktopRecevier = new DesktopRecevier();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(desktopRecevier,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(desktopRecevier);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if(isInPictureInPictureMode){
            Log.d("ning", "进入了画中画模式 ");
        }else {
            Log.d("ning", "退出了画中画模式");
        }
    }

    private class DesktopRecevier extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent !=null && intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
                String reason = intent.getStringExtra("reason");
                if (!TextUtils.isEmpty(reason) && (reason.equals("homekey")||reason.equals("recentapps"))){
                    //android 8.0后才提供画中画模式
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O
                    && !isInPictureInPictureMode()){
//                        创建画中画模式的参数构建器
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
//                        下面的10/5=2，表示画中画窗口的宽度是高度的两倍
                        Rational ration = new Rational(10,5);
                        builder.setAspectRatio(ration);
                        enterPictureInPictureMode(builder.build());
                    }
                }
            }
        }
    }
}