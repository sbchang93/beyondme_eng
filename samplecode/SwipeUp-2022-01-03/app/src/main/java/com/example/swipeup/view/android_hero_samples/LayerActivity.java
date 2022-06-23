package com.example.swipeup.view.android_hero_samples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class LayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyLayer(this));
    }

    public class MyLayer extends View {

        private Paint mPaint;

        public MyLayer(Context context) {
            super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            //canvas.drawColor(Color.BLACK);
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(150, 150, 100, mPaint);
            canvas.saveLayerAlpha(0, 0, 400, 400, 255);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(200, 200, 100, mPaint);
            canvas.restore();
        }
    }
}