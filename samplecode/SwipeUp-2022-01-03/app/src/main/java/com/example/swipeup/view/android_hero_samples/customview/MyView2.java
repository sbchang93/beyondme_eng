package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class MyView2 extends View {

    private static final String TAG = MyView.class.getSimpleName();

    public MyView2(Context context) {
        this(context, null);
    }

    public MyView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
        //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
        //return true;
    }
}