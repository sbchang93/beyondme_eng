package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Test02_MyViewActivity extends Activity {
    private MyView03 vw;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = 200;
        y = 200;
        vw = new MyView03(this);
        setContentView(vw);

    }

    protected class MyView03 extends View {

        public MyView03(Context context)  {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            canvas.drawCircle(x, y, 16, p);
        }

//        public boolean onTouchEvent(MotionEvent event){
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                x += 40;
//                invalidate();
//                return true;
//            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                if( x > 50 ) {
//                    x -= 10;
//                    invalidate();
//                }
//                return true;
//            }
//            return false;
//        }

    }

}


//        public boolean onKeyDown(int KeyCode, KeyEvent event) {
//            super.onKeyDown(KeyCode, event);
//            if(event.getAction() == KeyEvent.ACTION_DOWN) {
//                switch(KeyCode) {
//                    case KeyEvent.KEYCODE_DPAD_LEFT:
//                        x -= 5;
//                        invalidate();
//                        return true;
//                    case KeyEvent.KEYCODE_DPAD_RIGHT:
//                        x += 5;
//                        invalidate();
//                        return true;
//                    case KeyEvent.KEYCODE_DPAD_UP:
//                        y -= 5;
//                        invalidate();
//                        return true;
//                    case KeyEvent.KEYCODE_DPAD_DOWN:
//                        y += 5;
//                        invalidate();
//                        return true;
//                }
//
//            }
//
//            return false;
//        }