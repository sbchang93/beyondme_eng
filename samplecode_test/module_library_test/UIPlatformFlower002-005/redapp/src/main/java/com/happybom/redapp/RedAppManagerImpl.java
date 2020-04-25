package com.happybom.redapp;

import android.content.Context;
import android.content.Intent;

import static androidx.core.content.ContextCompat.startActivity;

public class RedAppManagerImpl implements RedApp{
    final static String TAG = RedAppManagerImpl.class.getSimpleName();

    static RedAppManagerImpl instance;

    public RedAppManagerImpl(Context context) {

    }

    public synchronized static RedAppManagerImpl getInstance(Context context) {
        if (instance == null) {
                instance = new RedAppManagerImpl(context);
        }
        return instance;
    }

    @Override
    public void startApp(Context context) {
        Intent intent = new Intent(context, RedMainActivity.class);
        context.startActivity(intent);
    }

}
