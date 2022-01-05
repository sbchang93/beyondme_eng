package com.example.swipeup.view.custom_view;

// Reference Home URL : https://www.charlezz.com/?p=1035

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
    private static final String TAG = "LinedEditText";

    private Rect mRect;
    private Paint mPaint;

//    public LinedEditText(@NonNull Context context) {
//        super(context);
//    }

    public LinedEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }

//    public LinedEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Rect rect = mRect;
        Paint paint = mPaint;

        for (int i=0; i<count; i++) {
            int baseline = getLineBounds(i, rect); // Returns: the Y-coordinate of the baseline
            canvas.drawLine(rect.left, baseline+1,rect.right, baseline+1, paint);
        }

        super.onDraw(canvas);
    }
}
