package com.example.designer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建者模式
        OkHttpClient build = new OkHttpClient.Builder().build();
        //Call call = okHttpClient.newCall(new Request.Builder().build());
        Phone build1 = new Phone.Builder()
                .setCpu("cpu")
                .setLanya("蓝牙")
                .setPingmu("屏幕")
                .setShexiangtou("摄像头")
                .setWifi("无线网").build();
        Log.e("TAG", "onCreate: "+build1.toString());
    }
}
