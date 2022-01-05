package com.example.swipeup.view.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.swipeup.R;

// Reference Home URL : https://www.charlezz.com/?p=1035

//public class PieChartView extends View {
//public class PieChartView extends androidx.appcompat.widget.AppCompatTextView {
public class PieChartView extends androidx.appcompat.widget.AppCompatEditText {
    private static final String TAG = "PieChartView";

//    public PieChartView(Context context) {
//        super(context);
//    }

    private Rect mRect;
    private Paint mPaint;
    private Paint mWhitePaint;

    boolean mShowText;
    int mTextPos;

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieChart, 0, 0);

        try {
            mShowText = typedArray.getBoolean(R.styleable.PieChart_showText, false);
            mTextPos = typedArray.getInteger(R.styleable.PieChart_labelPosition, 0);
        } finally {
            typedArray.recycle();
        }

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);
        mPaint.setColor(0x800000FF);

        mWhitePaint = new Paint();
        mWhitePaint.setStyle(Paint.Style.STROKE);
        mWhitePaint.setStrokeWidth(7);
        mWhitePaint.setColor(0x80FF0000);
    }

//    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

//    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint;

        if(mShowText)
            paint = mPaint;
        else
            paint = mWhitePaint;

        int count = getLineCount();
        Rect rect = mRect;


        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, rect); // Returns: the Y-coordinate of the baseline
            canvas.drawLine(rect.left, baseline + 1, rect.right, baseline + 1, paint);
            canvas.drawLine(rect.left, baseline - 15, rect.right, baseline - 15, paint);
            canvas.drawLine(rect.left, baseline - 30, rect.right, baseline - 30, paint);
        }

        super.onDraw(canvas);
    }
}
