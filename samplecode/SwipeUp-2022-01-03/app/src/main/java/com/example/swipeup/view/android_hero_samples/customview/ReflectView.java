package com.example.swipeup.view.android_hero_samples.customview;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.swipeup.R;
import com.example.swipeup.view_utils.UIHelper;

public class ReflectView extends View {
    private int dp;
    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public ReflectView(Context context) {
        super(context);
        initRes(context);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
    }

    public ReflectView(Context context, AttributeSet attrs,
                       int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRes(context);
    }

    private void initRes(Context context) {
        dp = UIHelper.dipToPx(getContext(), 1);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.shape_test);
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

//        mPaint = new Paint();
//        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0,mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
//                0XDD000000, 0X10000000, Shader.TileMode.CLAMP));
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0,mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
                0XDD000000, 0X10000000, Shader.TileMode.CLAMP));
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        canvas.drawRect(0, mSrcBitmap.getHeight(), mRefBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);

        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0,mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
                0XDD000000, 0X10000000, Shader.TileMode.CLAMP));
        canvas.drawBitmap(mSrcBitmap, 0, 0 + 350*dp, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight() + 350*dp, null);
        mPaint.setXfermode(mXfermode);
        canvas.drawRect(0, mSrcBitmap.getHeight(), mRefBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);

//        int save = canvas.save();
//        canvas.restore();

    }
}