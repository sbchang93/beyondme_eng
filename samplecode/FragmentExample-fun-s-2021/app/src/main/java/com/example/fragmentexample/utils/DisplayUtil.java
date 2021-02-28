package com.example.fragmentexample.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtil {

    // https://stackoverflow.com/questions/16103344/android-text-size-on-canvas-differ-from-text-size-in-textview
    public static float convertPixelsToDp(Context context, float px){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;

    }

    public static float convertDpToPixel(Context context, float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi/160f);
        return px;

    }
}
