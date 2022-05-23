package com.example.swipeup.view.touch_example.touch_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

//Reference Home URL : https://bitsoul.tistory.com/61

public class DrawCanvasOnViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView m = new MyView(this);
        setContentView(m);
    }

    class MyView extends View {
        int x = 100, y = 100;

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setTextSize(60);
            paint.setColor(Color.RED);
            canvas.drawRect(x - 100, y - 100, x + 100, y + 100, paint); // 사각형그림
            canvas.drawText("글씨", 100, 100, paint); // 글자 출력

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // 화면에 터치가 발생했을 때 호출되는 콜백 메서드
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    x = (int) event.getX();
                    y = (int) event.getY();
                    invalidate(); // 화면을 다시 그려줘라 => onDraw() 호출해준다
            }
            return true;
        }
    }

}

