package com.example.swipeup.view.android_hero_samples.customview;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class Clock extends View {

    private int mHeight, mWidth;
    Paint paintCircle, paintDegree, paintPointer, paintHour, paintMinute;

    public Clock(Context context) {
        this(context, null);
    }

    public Clock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Clock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paintCircle = new Paint();
        paintDegree = new Paint();
        paintPointer = new Paint();
        paintHour = new Paint();
        paintMinute = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        Log.i(TAG, "onDraw: mWidth " + mWidth);
        Log.i(TAG, "onDraw: mHeight " + mHeight);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 5f, paintCircle);
        //paintCircle.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                paintDegree.setStrokeWidth(5); // Bold
                paintDegree.setTextSize(30); // Big Size
                //paintCircle.setAntiAlias(true);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2 + 5f,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90, paintDegree);        //  90
            } else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                //paintCircle.setAntiAlias(true);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2 + 5f,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60, paintDegree);         // 60
            }

            canvas.rotate(15, mWidth / 2, mHeight / 2);

            // ***  12시 방향에서 세로로 그리고, 15도씩 회전하면서 시간을 그려나감  *** //
        }

        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);  // Center Point

        paintHour.setStrokeWidth(20);
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);     // Hours Line
        canvas.drawLine(0, 0, 100, 200, paintMinute);   // Minutes Line
        canvas.restore();
    }
}