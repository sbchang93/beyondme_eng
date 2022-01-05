package com.example.swipeup.view.view_example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.util.ArrayList;

// Reference Home URL : https://www.masterqna.com/android/55641/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%84%A0-%EB%91%90%EA%BB%98-%EC%A1%B0%EC%A0%88-%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95

public class PaintView extends View {
    private static final String TAG = "PaintView";

    static final int STATE_RED = 0;
    static final int STATE_BLUE = 1;
    static final int STATE_YELLOW = 2;
    static final int STATE_WHITE = 3;
    static final int STATE_BLACK = 4;
    static final int STATE_GREEN = 5;

    ArrayList<Point> mPointList = new ArrayList<Point>();

    Paint[] mPaintList = new Paint[6];
    int mStrokeWidth = 15;
    int mColorState = STATE_BLACK;

//    public PaintView(Context context) {
//        super(context);
//    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        // Setup Paint List
        Paint paint = new Paint();
        initPaintInfo(paint, Color.RED);
        mPaintList[0] = paint;

        paint = new Paint();
        initPaintInfo(paint, Color.BLUE);
        mPaintList[1] = paint;

        paint = new Paint();
        initPaintInfo(paint, Color.YELLOW);
        mPaintList[2] = paint;

        paint = new Paint();
        initPaintInfo(paint, Color.WHITE);
        mPaintList[3] = paint;

        paint = new Paint();
        initPaintInfo(paint, Color.BLACK);
        mPaintList[4] = paint;

        paint = new Paint();
        initPaintInfo(paint, Color.GREEN);
        mPaintList[5] = paint;


        int x = 300;
        int y = 300;
        // Setup Point List
        Point point = new Point(x + 100, y + 50);
        mPointList.add(point);

        point = new Point(x + 200, y + 100);
        mPointList.add(point);

        point = new Point(x + 100, y + 200);
        mPointList.add(point);

        point = new Point(x + 50, y + 200);
        mPointList.add(point);

        point = new Point(x + 0, y + 100);
        mPointList.add(point);

        point = new Point(x + 50, y + 50);
        mPointList.add(point);

        point = new Point(x + 100, y + 50);
        mPointList.add(point);

    }

    private void initPaintInfo(Paint paint, @ColorInt int color) {
        paint.setColor(color);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setAntiAlias(true);
    }

//    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

//    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.WHITE);
        Paint paint = mPaintList[0];
        for (int i = 0; i < mPointList.size() - 1; i++) {
            if (i < 6) paint = mPaintList[i];
            canvas.drawLine(mPointList.get(i).x, mPointList.get(i).y, mPointList.get(i + 1).x, mPointList.get(i + 1).y, paint);
        }
        super.onDraw(canvas);
    }
}
