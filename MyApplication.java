package com.example.medicalretrieval;

import android.app.Application;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.medicalretrieval.dataBase.PdfItemDataBase;
import com.example.medicalretrieval.dataBase.UserDataBase;
import com.example.medicalretrieval.pojo.User;

import java.util.HashMap;
import java.util.List;

public class MyApplication extends Application {
    private static MyApplication mApp;

    public HashMap<String,Object> application = new HashMap<>();

    private UserDataBase userDataBase;
    private PdfItemDataBase pdfItemDataBase;



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
        userDataBase = Room.databaseBuilder(this,UserDataBase.class,"user")
                .addMigrations()
                .allowMainThreadQueries()
                .build();

        pdfItemDataBase = Room.databaseBuilder(this,PdfItemDataBase.class,"pdfItem")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
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
    public UserDataBase getUserDataBase() {
        return userDataBase;
    }

    public PdfItemDataBase getPdfItemDataBase() {
        return pdfItemDataBase;
    }

}
