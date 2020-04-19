package com.happybom.uiplatformflower002;

import android.content.Context;

import com.happybom.blueapp.BlueApp;
import com.happybom.redapp.RedApp;

public class Configuration {
    private static RedApp redApp;
    private static BlueApp blueApp;

    public static void init(Context context) {
        redApp = Injector.provideRedApp(context);
        blueApp = Injector.provideBlueApp(context);
    }
}
