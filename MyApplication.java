package com.example.medicalretrieval;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import java.util.HashMap;

public class MyApplication extends Application {
    private static MyApplication mApp;

    public HashMap<String,Object> application = new HashMap<>();


//    private ThingDatabase thingDatabase;


    public static MyApplication getInstance(){
        if(mApp == null){
            mApp = new MyApplication();
        }
        return mApp;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mApp=this;
        Log.d("ning", "onCreate: ");


//        thingDatabase = Room.databaseBuilder(this,ThingDatabase.class,"thing")
//                .addMigrations()
//                .allowMainThreadQueries()
//                .build();

    }

//    App终止调用
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("ning", "onTerminate: ");
    }

//    配置改变时
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("ning", "onConfigurationChanged: ");
    }


//    public ThingDatabase getThingDatabase() {return thingDatabase;}
}
