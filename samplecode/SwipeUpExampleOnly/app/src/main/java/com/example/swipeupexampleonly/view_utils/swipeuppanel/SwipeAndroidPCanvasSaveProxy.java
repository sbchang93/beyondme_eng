package com.example.swipeupexampleonly.view_utils.swipeuppanel;

import android.graphics.Canvas;
import android.util.Log;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel
//. Build Error Patch
//https://github.com/umano/AndroidSlidingUpPanel/pull/922/commits/7af57e82bfdac401ea1a0b6a105442d427e9c5e7
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/SlidingUpPanelLayout.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/AndroidPCanvasSaveProxy.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/CanvasSaveProxy.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/CanvasSaveProxyFactory.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/LegacyCanvasSaveProxy.java


class SwipeAndroidPCanvasSaveProxy implements SwipeCanvasSaveProxy {
    private static final String TAG = SwipeCanvasSaveProxy.class.getSimpleName();
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