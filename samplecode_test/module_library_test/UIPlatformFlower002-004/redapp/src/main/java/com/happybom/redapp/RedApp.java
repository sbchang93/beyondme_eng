package com.happybom.redapp;

import android.content.Context;

public interface RedApp {
    String TAG = RedApp.class.getSimpleName();

    static RedApp getApplication(Context context) {
        return RedAppManagerImpl.getInstance(context);
    }

    void startApp(Context context);
}
