package com.example.swipeup.slidinguppanel.canvassaveproxy;
import android.graphics.Canvas;
import android.os.Build;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel
//. Build Error Patch
//https://github.com/umano/AndroidSlidingUpPanel/pull/922/commits/7af57e82bfdac401ea1a0b6a105442d427e9c5e7
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/SlidingUpPanelLayout.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/AndroidPCanvasSaveProxy.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/CanvasSaveProxy.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/CanvasSaveProxyFactory.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/LegacyCanvasSaveProxy.java

public class CanvasSaveProxyFactory {

    public CanvasSaveProxy create(final Canvas canvas) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return new AndroidPCanvasSaveProxy(canvas);
        } else {
            return new LegacyCanvasSaveProxy(canvas);
        }
    }
}