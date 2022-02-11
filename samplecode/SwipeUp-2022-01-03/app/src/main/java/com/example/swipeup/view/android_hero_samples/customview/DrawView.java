package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.swipeup.R;
import com.example.swipeup.view_utils.UIHelper;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class DrawView extends View {

    private int dp;
    private int sp;
    private Paint mPaint;
    private RectF oval1;
    private RectF oval2;
    private RectF oval3;
    private Path path;
    private Path path1;
    private Bitmap bitmap;
    private Shader mShader;

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dp = UIHelper.dipToPx(getContext(), 1);
        sp = UIHelper.sp2px(getContext(), 1);
        mPaint = new Paint();
        oval1 = new RectF();
        oval2 = new RectF();
        oval3 = new RectF();
        path = new Path();
        path1 = new Path();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        mShader = new LinearGradient(0, 0, 100 * dp, 100 * dp,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                        Color.LTGRAY}, null, Shader.TileMode.REPEAT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Draw rectangle：", 10 * dp, 25 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(80 * dp, 10 * dp, 120 * dp, 50 * dp, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(140 * dp, 10 * dp, 180 * dp, 50 * dp, mPaint);
        canvas.drawRect(200 * dp, 10 * dp, 280 * dp, 50 * dp, mPaint);


        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Drawpoint：", 10 * dp, 80 * dp, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(5);
        canvas.drawPoint(120 * dp, 70 * dp, mPaint);
        canvas.drawPoints(new float[]{120 * dp, 80 * dp, 140 * dp, 80 * dp, 160 * dp, 80 * dp}, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Draw line：", 10 * dp, 110 * dp, mPaint);

        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(80 * dp, 100 * dp, 120 * dp, 100 * dp, mPaint);
        canvas.drawLine(140 * dp, 100 * dp, 180 * dp, 140 * dp, mPaint);
        canvas.drawLines(new float[]{200 * dp, 100 * dp, 240 * dp, 100 * dp,
                240 * dp, 100 * dp, 200 * dp, 140 * dp,
                200 * dp, 140 * dp, 240 * dp, 140 * dp}, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Painted round corner rectangle：", 10 * dp, 180 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        oval1.set(120 * dp, 160 * dp, 180 * dp, 200 * dp);
        canvas.drawRoundRect(oval1, 5 * dp, 5 * dp, mPaint);
        oval1.set(220 * dp, 160 * dp, 280 * dp, 200 * dp);
        canvas.drawRoundRect(oval1, 20 * dp, 20 * dp, mPaint);


        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Paint：", 10 * dp, 230 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(80 * dp, 230 * dp, 15 * dp, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Fan：", 10 * dp, 280 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        oval2.set(100 * dp, 260 * dp, 160 * dp, 320 * dp);
        canvas.drawArc(oval2, 200, 140, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mShader);
        oval3.set(180 * dp, 260 * dp, 240 * dp, 320 * dp);
        //canvas.drawArc(oval3, 200, 140, true, mPaint);
        canvas.drawArc(oval3, 220, 100, true, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Arc：", 10 * dp, 330 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        oval2.set(100 * dp, 310 * dp, 160 * dp, 370 * dp);
        canvas.drawArc(oval2, 200, 140, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mShader);
        oval3.set(180 * dp, 310 * dp, 240 * dp, 370 * dp);
        canvas.drawArc(oval3, 200, 140, false, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Ellipse：", 10 * dp, 370 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(100 * dp, 350 * dp, 160 * dp, 390 * dp, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Draw text：", 10 * dp, 420 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("Android", 120 * dp, 420 * dp, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Path：", 10 * dp, 460 * dp, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        path.moveTo(100 * dp, 440 * dp);
        path.lineTo(140 * dp, 440 * dp);
        path.lineTo(150 * dp, 450 * dp);
        path.lineTo(140 * dp, 460 * dp);
        path.lineTo(100 * dp, 460 * dp);
        path.lineTo(90 * dp, 450 * dp);
        path.close();
        canvas.drawPath(path, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Painted beser curve:", 180 * dp, 370 * dp, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        path1.moveTo(180 * dp, 390 * dp);
        path1.quadTo(280 * dp, 390 * dp, 250 * dp, 500 * dp);
        canvas.drawPath(path1, mPaint);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15 * sp);
        mPaint.setAntiAlias(true);
        canvas.drawText("Draw Picture:", 10 * dp, 500 * dp, mPaint);
        canvas.drawBitmap(bitmap, 100 * dp, 470 * dp, mPaint);
    }
}
