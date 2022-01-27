package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class MyViewGroupB extends LinearLayout {

    private static final String TAG = MyViewGroupB.class.getSimpleName();

    public MyViewGroupB(Context context) {
        this(context, null);
    }

    public MyViewGroupB(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
        //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
        //return true;
    }
}
