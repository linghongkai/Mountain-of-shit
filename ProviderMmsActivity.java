package com.example.chapter07_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.chapter07_client.entity.ImageInfo;
import com.example.chapter07_client.util.FileUtil;
import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProviderMmsActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQUEST_CODE = 1;

    private EditText et_phone;
    private EditText et_title;
    private EditText et_message;

    private GridLayout gl_appendix;
    private List<ImageInfo> mImageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_mms);

        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);

        gl_appendix = findViewById(R.id.gl_appendix);

        //手动让MediaStore扫描入库
        MediaScannerConnection.scanFile(this,new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()},
                null,null);


        if (PermissionUtil.checkPermission(this,PERMISSIONS,PERMISSION_REQUEST_CODE)){
            loadImageList();
            showImageGrid();
        }
    }

    private void showImageGrid() {
        gl_appendix.removeAllViews();
        for (ImageInfo image:mImageList){
            //image ->ImageView
            ImageView iv_appendix = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeFile(image.path);
            iv_appendix.setImageBitmap(bitmap);
            iv_appendix.setScaleType(ImageView.ScaleType.FIT_CENTER);

            int px = Utils.dip2px(this,110);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px,px);
            iv_appendix.setLayoutParams(params);

            int padding = Utils.dip2px(this,5);
            iv_appendix.setPadding(padding,padding,padding,padding);

            iv_appendix.setOnClickListener(v->{
                sendMms(et_phone.getText().toString(),
                        et_title.getText().toString(),
                        et_message.getText().toString(),
                        image.path);
            });
            gl_appendix.addView(iv_appendix);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE &&
            PermissionUtil.checkGrant(grantResults)){
            loadImageList();
            showImageGrid();
        }
    }

    @SuppressLint("Range")
    private void loadImageList() {
//
//        MediaStore
        String[] columns = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATA,
        };
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 307200",
                null,
                "_size DESC"
        );
        int count = 0;
        if(cursor!=null){
            while (cursor.moveToNext() && count<6){
                ImageInfo image = new ImageInfo();
                image.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                image.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                image.name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
                image.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                if(FileUtil.checkFileUri(this,image.path)){
                    count++;
                    mImageList.add(image);
                }
                Log.d("ning", image.toString());
                
                
                
            }
        }
    }
    private void sendMms(String phone, String title, String message,String path) {

        //根据指定路径创建一个Uri对象
        Uri uri = Uri.parse(path);

        //兼容 Android 7.0 把访问文件的Uri方式改为FileProvider
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            uri = FileProvider.getUriForFile(this,getString(R.string.file_provider),new File(path));
            Log.d("ning", String.format("new uri:%s",uri.toString()));
        }
        
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra("address",phone);
        intent.putExtra("subject",title);
        intent.putExtra("sms_body",message);

        intent.putExtra(Intent.EXTRA_STREAM, uri);

        intent.setType("image/*");
        startActivity(intent);

    }

}