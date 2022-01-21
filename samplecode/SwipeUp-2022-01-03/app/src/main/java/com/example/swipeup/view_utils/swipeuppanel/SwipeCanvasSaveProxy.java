package com.example.swipeup.view_utils.swipeuppanel;

import android.graphics.Canvas;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel
public interface SwipeCanvasSaveProxy {
    int save();

    boolean isFor(final Canvas canvas);
}
