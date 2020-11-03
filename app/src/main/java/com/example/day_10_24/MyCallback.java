package com.example.day_10_24;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class MyCallback<T> implements Callback {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()){
            String string = response.body().string();
            Class call1 = (Class <T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            final T request = (T) new Gson().fromJson(string, call1);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onSup(request);
                }
            });
        }
    }
    public abstract void onSup(T t);
}
