package com.example.swipeup.view_utils.swipeuppanel;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel

import android.graphics.Canvas;
import android.os.Build;

import com.example.swipeup.slidinguppanel.canvassaveproxy.CanvasSaveProxy;


public class SwipeCanvasSaveProxyFactory {
    public SwipeCanvasSaveProxy create(final Canvas canvas) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return new SwipeAndroidPCanvasSaveProxy(canvas);
        } else {
            //return new LegacyCanvasSaveProxy(canvas);
            return new SwipeAndroidPCanvasSaveProxy(canvas);
        }
    }
}
