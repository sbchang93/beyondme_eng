package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DragView2 extends View {

    private int lastX;
    private int lastY;

    public DragView2(Context context) {
        super(context);
        ininView();
    }

    public DragView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView();
    }

    public DragView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView();
    }

    private void ininView() {
        setBackgroundColor(Color.GREEN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) (event.getRawX());
        int rawY = (int) (event.getRawY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                lastX = rawX;
                lastY = rawY;
                break;
        }
        return true;
    }
}
