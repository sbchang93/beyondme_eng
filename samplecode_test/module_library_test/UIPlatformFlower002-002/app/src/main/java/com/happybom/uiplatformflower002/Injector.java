package com.happybom.uiplatformflower002;

import android.content.Context;

import com.happybom.blueapp.BlueApp;
import com.happybom.redapp.RedApp;

public class Injector {
    private static RedApp redApp;
    private static BlueApp blueApp;

    public static RedApp provideRedApp(Context context) {
        if (redApp == null) {
            redApp = RedApp.getApplication(context);
        }
        return redApp;
    }

    public static BlueApp provideBlueApp(Context context) {
        if (blueApp == null) {
            blueApp = BlueApp.getApplication(context);
        }
        return blueApp;
    }
}
