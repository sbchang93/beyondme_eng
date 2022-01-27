package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class DragView3 extends View {

    private int lastX;
    private int lastY;

    public DragView3(Context context) {
        super(context);
        ininView();
    }

    public DragView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView();
    }

    public DragView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView();
    }

    private void ininView() {
        setBackgroundColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
