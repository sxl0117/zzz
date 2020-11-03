package com.example.day04_retrofit_login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DataInfoBean;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recycler_main;
    private NavigationView nav_main;
    private MainLoginAdapter mainLoginAdapter;
    private Button btn_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initData();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("tag", "拦截器接收的数据" + message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new MyInterceptor()).build();


        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit build = builder.baseUrl(ApiService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Map<String, Object> map = new HashMap<>();
        map.put("username", "5559");
        map.put("password", "666666");
        ApiService apiService = build.create(ApiService.class);
        Call<LoginBean> dataInfo = apiService.getDataInfo(map);
        dataInfo.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean.DataBean data = response.body().getData();
                Log.e("tag", data.toString());
                Headers headers = response.headers();
                List<String> values = headers.values("Set-Cookie");

                String headerIndex = values.get(0);
                int index = headerIndex.indexOf(";");
                String substring = headerIndex.substring(0, index);
                App.getSp().edit().putString("cookie", substring).commit();

                Log.e("tag", "接收的Cookie" + substring);
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
//        recycler_main=findViewById(R.id.recycler_main);
//        recycler_main.setLayoutManager(new LinearLayoutManager(this));


        nav_main = (NavigationView) findViewById(R.id.nav_main);
        View headerView = nav_main.getHeaderView(0);
        TextView tv_login = headerView.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRetrofit();
            }
        });

    }


    class MyInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            RequestBody requestBody = new FormBody.Builder()
                    .build();

            Request device = chain.request().newBuilder()
                    .addHeader("device", "")
                    .post(requestBody).build();

            okhttp3.Response response = chain.proceed(chain.request());
            Headers headers = response.headers();
            List<String> values = headers.values("Set-Cookie");
            String str = values.get(0);
            int i = str.indexOf(";");
            String substring = str.substring(0, i);
            Log.e("tag", "interceptor自定义：" + substring);
            return response;
        }
    }


    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        build.create(ApiService.class).getLoginInfo().enqueue(new Callback<DataInfoBean>() {
            @Override
            public void onResponse(Call<DataInfoBean> call, Response<DataInfoBean> response) {

                recycler_main.setAdapter(mainLoginAdapter);
            }

            @Override
            public void onFailure(Call<DataInfoBean> call, Throwable t) {

            }
        });
    }

}
