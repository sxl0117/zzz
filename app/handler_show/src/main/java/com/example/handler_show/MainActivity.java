package com.example.handler_show;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mHandlerText;
    private HandlerThread handlerThread = new HandlerThread("子线程");
    static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            WeakReference<MainActivity> weakReference = new WeakReference<MainActivity>((MainActivity) message.obj);
            weakReference.get().mHandlerText.setText("当前在: " + Thread.currentThread().getName());
            return false;
        }
    });
    private Myrunable myrunable;
    private Button mServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mHandlerText = findViewById(R.id.text_handler);
        mServiceBtn = (Button) findViewById(R.id.btn_service);
        mServiceBtn.setOnClickListener(this);
        handlerThread.start();
        Message message = Message.obtain();
        message.obj = this;
        handler.sendMessage(message);
        myrunable = new Myrunable();
        new Thread(myrunable).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_service:
                // TODO 20/10/27
                startService(new Intent(this,MyService.class));
                break;
            default:
                break;
        }
    }

    class Myrunable implements Runnable {
        @Override
        public void run() {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(myrunable);
    }
}
