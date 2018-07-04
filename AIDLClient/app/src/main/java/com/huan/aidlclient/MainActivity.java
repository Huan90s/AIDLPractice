package com.huan.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huan.aidlserver.IAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IAidlInterface mService;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huan.aidlserver", "com.huan.aidlserver.AIDLService"));
        bindService(intent, new MyServiceConnection(), Context.BIND_AUTO_CREATE);

        final TextView tvPlus = findViewById(R.id.tvPlus);
        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                try {
                    tvPlus.setText(mService.plus(1, count) + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mService = IAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
