package com.happybom.blueapp;

import android.content.Context;
import android.content.Intent;

public class BlueAppManagerImpl implements BlueApp{
    final static String TAG = BlueAppManagerImpl.class.getSimpleName();

    static BlueAppManagerImpl instance;

    public BlueAppManagerImpl(Context context) {

    }

    public synchronized static BlueAppManagerImpl getInstance(Context context) {
        if (instance == null) {
            instance = new BlueAppManagerImpl(context);
        }
        return instance;
    }

    @Override
    public void startApp(Context context) {
        Intent intent = new Intent(context, BlueMainActivity.class);
        context.startActivity(intent);
    }

}
