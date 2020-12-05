package com.example.toronto.mystudyapp.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class WeakReferenceHandler<T extends Object> extends Handler {
    private final WeakReference<T> object;

    public WeakReferenceHandler(T object) {
        super();
        this.object = new WeakReference<T>(object);
    }

    public WeakReferenceHandler(T object, Looper looper) {
        super(looper);
        this.object = new WeakReference<T>(object);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        handleMessage(object.get(), msg);
    }

    public abstract void handleMessage(T object, Message msg);
}
