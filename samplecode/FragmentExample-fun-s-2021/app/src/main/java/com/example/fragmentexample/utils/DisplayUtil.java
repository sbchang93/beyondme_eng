package com.example.fragmentexample.utils;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class DisplayUtil {

    // https://stackoverflow.com/questions/16103344/android-text-size-on-canvas-differ-from-text-size-in-textview
    public static float convertPixelsToDp(Context context, float px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;

    }

    public static float convertDpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;

    }

    // https://inma.tistory.com/72
    public static void testSample(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        int dpi = displayMetrics.densityDpi;
        float density = displayMetrics.density;
    }

    // https://inma.tistory.com/72
    public static int dpToPx(Context context, float dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

//    public float dpToPx(Context context, float dp) {
//        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
//    }

    // https://inma.tistory.com/72
    public static float pxToDp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;

        if (density == 1.0)  // mdpi (160dpi) -- xxxhdpi (density = 4) 기준으로 density 값을 재설정 한다.
            density *= 4.0;
        else if (density == 1.5)  // hdpi (240dpi)
            density *= (8 / 3);
        else if (density == 2.0)  // xhdpi (320dpi)
            density *= 2.0;

        return px / density;
    }
}

