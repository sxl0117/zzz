package com.example.handler_show;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentService intentService = new IntentService(getPackageName()) {
            @Override
            protected void onHandleIntent(@Nullable Intent intent) {
                Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onHandleIntent: "+Thread.currentThread().getName());
            }
        };
        return super.onStartCommand(intent, flags, startId);
    }
}
