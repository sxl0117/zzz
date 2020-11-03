package com.example.day04_retrofit_login;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DataInfoBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class MainLoginAdapter extends RecyclerView.Adapter {
    private List<DataInfoBean> datas;
    private Context context;

    public MainLoginAdapter(List<DataInfoBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;

        viewHolder.btn_coll.setText((String) datas.get(position).getData());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cookie = App.getSp().getString("cookie", "");
                if(cookie!=null){
                    Log.e("tag","收藏成功");
                }else {
                    Log.e("tag","收藏失败");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView btn_coll;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.btn_coll = (TextView) rootView.findViewById(R.id.tv_title);
        }

    }
}
