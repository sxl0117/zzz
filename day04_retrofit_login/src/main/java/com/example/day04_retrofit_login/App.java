package com.example.day04_retrofit_login;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
    public static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences("config", MODE_PRIVATE);
    }

    public static SharedPreferences getSp() {
        return sp;
    }
}
