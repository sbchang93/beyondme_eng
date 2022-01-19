package com.example.swipeup.view.touch_example;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.TouchDelegate;
import android.widget.CheckBox;
import android.widget.FrameLayout;

// Reference GitHub : https://github.com/devunwired/custom-touch-examples

public class TouchDelegateLayout extends FrameLayout {

    public TouchDelegateLayout(Context context) {
        super(context);
        init();
    }

    public TouchDelegateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchDelegateLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private CheckBox mButton;
    private void init() {
        mButton = new CheckBox(getContext());
        mButton.setText("Click Anywhere On Screen");

        addView(mButton, new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));
    }

    /*
     * TouchDelegate is applied to this view (parent) to delegate all touches
     * within the specified rectangle to the CheckBox (child).  Here, the rectangle
     * is the entire size of this parent view.
     *
     * This must be done after the view has measured itself so we know how big to make the rect,
     * thus we've chosen to add the delegate in onMeasure()
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Apply the whole area of this view as the delegate area
        Rect bounds = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()); // 전체화면 터치하면, mButton 체크박스가 동작함.
        //Rect bounds = new Rect(0, 0, 200, 200);  // 200x200 영역 터치한 경우에만 터치가 동작함.
        TouchDelegate delegate = new TouchDelegate(bounds, mButton);
        setTouchDelegate(delegate);
    }
}