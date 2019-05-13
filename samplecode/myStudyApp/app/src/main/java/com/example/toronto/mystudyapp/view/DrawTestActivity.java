package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;

public class DrawTestActivity extends Activity {
    private MyView07 view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MyView07(this);
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected class MyView07 extends View {

        public MyView07(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint01 = new Paint();
            paint01.setAntiAlias(true);

            paint01.setColor(Color.YELLOW);
            canvas.drawRect(0,0,50,50,paint01);

            canvas.save();
            paint01.setColor(Color.GREEN);
            canvas.translate(100, 100);
            canvas.scale(2, 2);
            canvas.drawRect(0,0,50,50,paint01);

            canvas.restore();
            paint01.setColor(Color.BLUE);
            canvas.scale(2, 2);
            canvas.translate(100, 100);
            canvas.drawRect(0,0,50,50,paint01);

            Paint paint02 = new Paint();
            paint02.setAntiAlias(true);

            paint02.setColor(Color.YELLOW);
            canvas.drawRect(100,100,200,200,paint02);

            paint02.setColor(Color.GREEN);
            canvas.save();
            canvas.rotate(-30);
            canvas.drawRect(100,100,200,200,paint02);

            paint02.setColor(Color.BLUE);
            canvas.restore();
            canvas.rotate(30, 150, 150);
            canvas.drawRect(100,100,200,200,paint02);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    invalidate();
                    return true;
            }
            return false;
        }

    }
}
