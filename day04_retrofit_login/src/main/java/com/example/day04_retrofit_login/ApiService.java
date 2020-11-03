package com.example.day04_retrofit_login;

import com.DataInfoBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    public static final String BASE_URL = "https://www.wanandroid.com/";

    @POST("user/login")
    @FormUrlEncoded
    Call<LoginBean> getDataInfo(@FieldMap Map<String,Object> map);

    @GET("lg/collect/15181/json")
    @FormUrlEncoded
    Call<DataInfoBean> getLoginInfo();
}
