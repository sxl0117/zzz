package com.example.okhttps;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public abstract class MyCallback<T> implements Callback {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()){
            String string = response.body().string();
            getRedponseHeader(response.headers());
            Class mclass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            final T result = (T) new Gson().fromJson(string, mclass);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onSucess(result);
                }
            });
        }
    }
    public abstract void onSucess(T t);
    public void getRedponseHeader(Headers headers){

    }
}
