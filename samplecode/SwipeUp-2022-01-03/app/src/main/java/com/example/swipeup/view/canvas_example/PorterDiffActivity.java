package com.example.swipeup.view.canvas_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

import com.example.swipeup.R;

// Reference Homepage : https://black-jin0427.tistory.com/145
// Reference Homepage : https://i.ytimg.com/vi/WCXM4DnwT1g/maxresdefault.jpg

public class PorterDiffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView myView = new MyView(this);
        setContentView(myView);
    }

    class MyView extends View {

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blackpanther);

            //Bitmap outputBitmap_01 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            int resizeWidth = 800;
            int resizeHeight = 400;

            // --------------------------------------------------------------------------------------
            // Test 01
            Bitmap outputBitmap_01 = Bitmap.createBitmap(resizeWidth, resizeHeight, Bitmap.Config.ARGB_8888);

            Canvas imageCanvas_01 = new Canvas(outputBitmap_01);

            int color = 0xff424242;
            float roundPx = 300;

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(color);

            Rect rect = new Rect(0, 0, resizeWidth, resizeHeight);
            RectF rectF = new RectF(rect);

            imageCanvas_01.drawBitmap(sourceBitmap, rect, rect, paint);

            imageCanvas_01.drawRoundRect(rectF, roundPx, roundPx, paint);

            // Screen Canvas ( : Draw it into Screen Canvas )
            canvas.drawBitmap(outputBitmap_01, 0, 0, paint);


            // --------------------------------------------------------------------------------------
            // Test 02
            Bitmap outputBitmap_02 = Bitmap.createBitmap(resizeWidth, resizeHeight, Bitmap.Config.ARGB_8888);

            Canvas imageCanvas_02 = new Canvas(outputBitmap_02);

            Paint paint02 = new Paint();
            paint02.setAntiAlias(true);
            paint02.setColor(color);

            rect = new Rect(0, 0, resizeWidth, resizeHeight);
            rectF = new RectF(rect);

            imageCanvas_02.drawRoundRect(rectF, roundPx, roundPx, paint02);

            imageCanvas_02.drawBitmap(sourceBitmap, rect, rect, paint02);

            // Screen Canvas ( : Draw it into Screen Canvas ) - (x,y) -> (0, 400)
            canvas.drawBitmap(outputBitmap_02, 0, 400, paint02);


            // --------------------------------------------------------------------------------------
            // Test 03
            Bitmap outputBitmap_03 = Bitmap.createBitmap(resizeWidth, resizeHeight, Bitmap.Config.ARGB_8888);

            Canvas imageCanvas_03 = new Canvas(outputBitmap_03);

            Paint paint03 = new Paint();
            paint03.setAntiAlias(true);
            paint03.setColor(color);

            rect = new Rect(0, 0, resizeWidth, resizeHeight);
            rectF = new RectF(rect);;

            imageCanvas_03.drawRoundRect(rectF, roundPx, roundPx, paint03);

            paint03.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); // Add Round Effect
            imageCanvas_03.drawBitmap(sourceBitmap, rect, rect, paint03);

            // Screen Canvas ( : Draw it into Screen Canvas ) - (x,y) -> (0, 800)
            canvas.drawBitmap(outputBitmap_03, 0, 800, paint03);



            // --------------------------------------------------------------------------------------
            // Test 04
            Bitmap outputBitmap_04 = Bitmap.createBitmap(resizeWidth, resizeHeight, Bitmap.Config.ARGB_8888);

            Paint paint04 = new Paint();
            paint04.setAntiAlias(true);
            paint04.setColor(color);

            rect = new Rect(0, 0, resizeWidth, resizeHeight);
            rectF = new RectF(rect);

            Canvas imageCanvas_04 = new Canvas(outputBitmap_04);



            imageCanvas_04.drawRoundRect(rectF, roundPx, roundPx, paint04);

            paint04.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)); // Not Working
            imageCanvas_04.drawBitmap(sourceBitmap, rect, rect, paint04);
            // Screen Canvas ( : Draw it into Screen Canvas ) - (x,y) -> (0, 800)
            canvas.drawBitmap(outputBitmap_04, 0, 1200, paint04);


            // --------------------------------------------------------------------------------------
            // Test 05
            Bitmap outputBitmap_05 = Bitmap.createBitmap(resizeWidth, resizeHeight, Bitmap.Config.ARGB_8888);
            Canvas imageCanvas_05 = new Canvas(outputBitmap_05);

            Paint paint5 = new Paint();
            paint5.setAntiAlias(true);
            paint5.setColor(color);

            imageCanvas_05.drawRoundRect(rectF, roundPx, roundPx, paint);
            imageCanvas_05.drawBitmap(sourceBitmap, rect, rect, paint);

            canvas.drawBitmap(outputBitmap_05, 0, 1600, paint);  // (x,y) -> (0, 1600)


        }
    }
}