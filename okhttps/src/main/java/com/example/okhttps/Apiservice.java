package com.example.okhttps;

import java.util.Map;

import io.reactivex.Observer;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Apiservice {
    String STRING ="https://www.wanandroid.com/";
    @FormUrlEncoded
    @POST("user/login")
    Observer<Bean> getData(@FieldMap Map<String,Object> map);
}