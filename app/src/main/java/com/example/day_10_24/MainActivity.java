package com.example.day_10_24;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMainBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMainBtn = (Button) findViewById(R.id.btn_main);
        mMainBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main:
                // TODO 20/10/26
                onPost();
                break;
            default:
                break;
        }
    }

    private void onPost() {
        OkHttpClient build = new OkHttpClient.Builder().build();
        RequestBody body = new FormBody.Builder().add("k", "json").build();
        Request request = new Request.Builder().url("https://www.wanandroid.com/article/query/0/json").post(body).build();
        Call call = build.newCall(request);
        call.enqueue(new MyCallback<Bean>() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSup(Bean bean) {
                List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
                for (Bean.DataBean.DatasBean datasBean :datas){
                    Log.e("TAG", "onSup: "+datasBean.getTitle());
                }
            }
        });
    }
}