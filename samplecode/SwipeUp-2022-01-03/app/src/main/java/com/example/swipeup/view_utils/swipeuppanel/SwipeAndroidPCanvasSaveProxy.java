package com.example.swipeup.view_utils.swipeuppanel;

import android.graphics.Canvas;
import android.util.Log;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel

public class SwipeAndroidPCanvasSaveProxy implements SwipeCanvasSaveProxy {
    private static final String TAG = SwipeCanvasSaveProxyFactory.class.getSimpleName();
    private final Canvas mCanvas;

    SwipeAndroidPCanvasSaveProxy(final Canvas canvas) {
        Log.d(TAG, "New AndroidPCanvasSaveProxy");

        mCanvas = canvas;
    }

    @Override
    public int save() {
        return mCanvas.save();
    }

    @Override
    public boolean isFor(final Canvas canvas) {
        return canvas == mCanvas;
    }
}
