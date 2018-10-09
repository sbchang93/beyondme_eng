package com.jaeryong.android.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class ServerService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IRemoteServiceImpl();
    }

    private class IRemoteServiceImpl extends IRemoteService.Stub {

        @Override
        public int sum(int a, int b) throws RemoteException {
            return a + b;
        }

    }
}
