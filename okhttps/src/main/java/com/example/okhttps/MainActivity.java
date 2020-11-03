package com.example.okhttps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestBody body = new FormBody.Builder().add("username","5559")
                .add("password", "666666").build();
        final Request request = new Request.Builder().url(Content.logname_url).post(body).addHeader("Cookie", "").build();
        HttpLoggingInterceptor tag = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("TAG", "log: " + message);
            }
        });
        tag.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(Content.Time_url, TimeUnit.SECONDS)
                .addInterceptor(tag)
                .addInterceptor(new MyInterCeptor()).build();
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = request.body().toString();
                Bean bean = new Gson().fromJson(s, Bean.class);
                Bean.DataBean data = bean.getData();
            }
        });
//        call.enqueue(new MyCallback<Bean>() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onSucess(Bean bean) {
//                Log.e("TAG", "onSucess: "+bean.getData().toString() );
//            }
//
//            @Override
//            public void getRedponseHeader(Headers headers) {
//                List<String> values = headers.values("Set-Cookie");
//                String cookieStr = values.get(0);
//                int i = cookieStr.indexOf(";");
//                String substring =cookieStr.substring(0, i);
//                Log.e("TAG", "getRedponseHeader: "+substring );
//            }
//        });
    }
    class MyInterCeptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            RequestBody requestBody = new FormBody.Builder().build();
            Request build = chain.request().newBuilder().addHeader("key1", "value")
                    .post(requestBody).build();
            //将封装好的请求头信息通过chain分发下去,得到响应信息
            Response proceed = chain.proceed(build);
            Headers headers = proceed.headers();
            List<String> values = headers.values("Set-Cookie");
            String cookieStr = values.get(0);
            int i = cookieStr.indexOf(";");
            String substring = cookieStr.substring(0, i);
            Log.e("TAG", "intercept: "+substring);
            return proceed;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
