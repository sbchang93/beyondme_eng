package com.example.swipeup.view.android_hero_samples;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.swipeup.R;
import com.example.swipeup.view.android_hero_samples.customview.ImageMatrixView;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class ImageMatrixTestActivity extends AppCompatActivity {

    private GridLayout mGridGroup;
    private ImageMatrixView mMyView;
    private Bitmap mBitmap;
    private int mEtWidth = 0;
    private int mEtHeight = 0;
    private float[] mImageMatrix = new float[9];
    private EditText[] mETs = new EditText[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matrix_test);
        mGridGroup = (GridLayout) findViewById(R.id.grid_group);
        mMyView = (ImageMatrixView) findViewById(R.id.view);
        //mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher); // null
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_test1);

        mGridGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGridGroup.getWidth() / 3;
                mEtHeight = mGridGroup.getHeight() / 3;
                addEts();
                initImageMatrix();
            }
        });

        mGridGroup.setVisibility(View.INVISIBLE);
    }

    private void addEts() {
        for (int i = 0; i < 9; i++) {
            EditText et = new EditText(this);
            et.setGravity(Gravity.CENTER);
            mETs[i] = et;
            mGridGroup.addView(et, mEtWidth, mEtHeight);
        }
    }

    private void getImageMatrix() {
        for (int i = 0; i < 9; i++) {
            EditText et = mETs[i];
            mImageMatrix[i] = Float.valueOf(et.getText().toString());
        }
    }

    private void initImageMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mETs[i].setText(String.valueOf(1));
            } else {
                mETs[i].setText(String.valueOf(0));
            }
        }
    }

    public void change(View view) {
        getImageMatrix();
        Matrix matrix = new Matrix();
        //matrix.setValues(mImageMatrix);

        //matrix.setRotate(45);
        //matrix.postTranslate(200, 200);

        //matrix.setTranslate(200, 200);
        //matrix.preRotate(45);

        //matrix.setScale(2, 2);
        //matrix.postRotate(45);
        //matrix.postTranslate(200, 200);

        matrix.setSkew(0.9f, 0);
        matrix.postTranslate(200, 200);


        mMyView.setImageAndMatrix(mBitmap, matrix);
        mMyView.invalidate();
    }

    public void reset(View view) {
        initImageMatrix();
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);
        mMyView.setImageAndMatrix(mBitmap, matrix);
        mMyView.invalidate();
    }
}
