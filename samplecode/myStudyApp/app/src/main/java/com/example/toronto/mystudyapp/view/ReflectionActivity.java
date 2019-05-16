package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.Random;

class Ball {
    int x, y;
    int rad;
    int dx, dy;
    int color;
    int count;

    static Ball Create(int x, int y, int Rad) {

        Random Rnd = new Random();
        Ball NewBall = new Ball();

        NewBall.x = x;
        NewBall.y = y;
        NewBall.rad = Rad;
        do {
            NewBall.dx = Rnd.nextInt(11) - 5;
            NewBall.dy = Rnd.nextInt(11) - 5;
        } while (NewBall.dx == 0 || NewBall.dy == 0);

        NewBall.count = 0;
        NewBall.color = Color.rgb(Rnd.nextInt(256),Rnd.nextInt(256),Rnd.nextInt(256));
        return NewBall;
    }

    void Move(int width, int height) {
        x += dx;
        y += dy;

        if(x < rad || x > width - rad) {
            dx *= -1;
            count++;
        }

        if(x < rad || x > height - rad) {
            dy *= -1;
            count++;
        }
    }

    void Draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        int r;
        int alpha;

        for(r = rad, alpha =1; r > 4; r--, alpha +=5) {
            paint.setColor(Color.argb(alpha, Color.red(color),Color.green(color),Color.blue(color)));
            canvas.drawCircle(x, y, r, paint);
        }
    }

}

public class ReflectionActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    private MyView08 view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MyView08(this);
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected class MyView08 extends View {
        Bitmap mBack;
        ArrayList<Ball> arBall = new ArrayList<Ball>();
        final static int DELAY = 50;
        final static int RAD = 24;

        public MyView08(Context context) {
            super(context);
            mBack = BitmapFactory.decodeResource(context.getResources(), R.drawable.ms06s);
            mHandler.sendEmptyMessage(0);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(mBack, 0, 0, null);
            for (int idx = 0; idx < arBall.size(); idx++) {
                arBall.get(idx).Draw(canvas);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                //case MotionEvent.ACTION_MOVE:
                    Ball NewBall = Ball.Create((int)event.getX(), (int)event.getY(), RAD);
                    arBall.add(NewBall);
                    invalidate();
                    return true;
            }
            return false;
        }

        Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                Ball B;
                for (int idx = 0; idx < arBall.size(); idx++) {
                    B = arBall.get(idx);
                    B.Move(getWidth(), getHeight());
                    if(B.count > 4) {
                        arBall.remove(idx);
                        idx--;
                    }
                }
                invalidate();
                mHandler.sendEmptyMessageDelayed(0, DELAY);
            }
        };

    }
}
