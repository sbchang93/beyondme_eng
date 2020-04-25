package com.happybom.blueapp;

import android.content.Context;

public interface BlueApp {
    String TAG = BlueApp.class.getSimpleName();

    static BlueApp getApplication(Context context) {
        return BlueAppManagerImpl.getInstance(context);
    }

    void startApp(Context context);
}
