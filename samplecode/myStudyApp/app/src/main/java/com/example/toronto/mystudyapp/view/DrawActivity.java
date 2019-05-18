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

public class DrawActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    private MyView view;
    private ArrayList<Point> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new MyView(this);
        setContentView(view);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    protected class MyView extends View {
        private Bitmap bmp1, bmp2, scaled;
        private Paint paintStroke = new Paint();
        private Paint paintFill = new Paint();


        public MyView(Context context) {
            super(context);

            paintStroke.setStyle(Paint.Style.STROKE);
            paintStroke.setColor(Color.RED);
            paintStroke.setStrokeWidth(10);

            paintFill.setStyle(Paint.Style.FILL);
            paintFill.setColor(Color.argb(255, 255, 255,0));

            BitmapDrawable d = (BitmapDrawable)getResources().getDrawable(R.drawable.ms06s);
            bmp1 = d.getBitmap();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.ms06s, options);

            int w =  (int) (bmp1.getWidth() / 3.f);
            int h = (int) (bmp1.getHeight() / 3.f);
            scaled = Bitmap.createScaledBitmap(bmp1, w, h, true);

            pList = new ArrayList<Point>();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawCircle(200, 200, 100, paintStroke);
            canvas.drawCircle(200, 200, 100, paintFill);

            canvas.drawBitmap(bmp1, 0, 400, null);
            canvas.drawBitmap(bmp2, 0, 600, null);
            canvas.drawBitmap(scaled, 0, 800, null);

            for (int i=0; i<pList.size(); i++)
                canvas.drawCircle(pList.get(i).x, pList.get(i).y, 50, paintFill);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    pList.add(new Point((int)event.getX(), (int)event.getY()));
                    invalidate();
                    return true;
            }
            // return super.onTouchEvent(event);
            return false;
        }

    }

}
