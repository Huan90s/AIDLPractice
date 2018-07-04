package com.huan.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends IAidlInterface.Stub {

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    }
}
