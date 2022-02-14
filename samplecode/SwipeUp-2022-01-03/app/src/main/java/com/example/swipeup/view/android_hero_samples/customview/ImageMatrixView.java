package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.example.swipeup.R;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class ImageMatrixView  extends View {

    private Matrix mMatrix;
    private Bitmap mBitmap;

    public ImageMatrixView(Context context) {
        super(context);
        initMyView();
    }

    public ImageMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyView();
    }

    public ImageMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMyView();
    }

    public void setImageAndMatrix(Bitmap bm, Matrix matrix) {
        mMatrix = matrix;
        mBitmap = bm;
    }

    public void initMyView() {
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher); // null
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_test1);
        setImageAndMatrix(bitmap, new Matrix());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }
}