package com.example.day04_retrofit_login;


import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCallBack<T> implements Callback {


    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            Class genericSuperclass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }
}