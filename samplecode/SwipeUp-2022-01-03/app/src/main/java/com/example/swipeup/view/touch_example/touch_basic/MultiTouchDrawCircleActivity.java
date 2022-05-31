package com.example.swipeup.view.touch_example.touch_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//Reference Home URL : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kimej0707&logNo=220636515734
//        ACTION_DOWN : 화면을 터치하는 첫번째 포인터에 대해 발생. 제스처 인식의 시작점
//                       MotionEvent 인덱스 0번에 저장(배열로 저장함)
//        ACTION_POINTER_DOWN : 첫번째 이외의 포인터에 대해 발생됨.
//
//        . 인덱스(index)와 아이디(id)
//           - 인덱스(index) : 터치의 순서
//           - 아이디(id)    : 포인터의 개수가 달라질 수 있음. 포인터 추적용 정보
//                             getPointerId()


public class MultiTouchDrawCircleActivity extends AppCompatActivity {
    public static final String TAG = "TouchTouchDrawCircleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyMultiTouchView myMultiTouchView = new MyMultiTouchView(this);
        setContentView(myMultiTouchView);
    }

    class MyMultiTouchView extends View {
        private Paint paint;

        final int MAX_POINTS = 10;

        int mPointerId[] = new int[MAX_POINTS];
        float[] x = new float[MAX_POINTS];
        float[] y = new float[MAX_POINTS];
        boolean[] touching = new boolean[MAX_POINTS];

        public MyMultiTouchView(Context context) {
            super(context);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.MAGENTA);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (int i = 0; i < MAX_POINTS; i++) {
                if (touching[i]) {
                    canvas.drawCircle(x[i], y[i], 30, paint);
                }
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int pointer_count = event.getPointerCount();
            int activeIndex = event.getActionIndex();
            int activePointerId = event.getPointerId(activeIndex);
            Log.i(TAG, "activePointerId : " + activePointerId);
            int action = event.getActionMasked();   // for Multi Pointers

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    for (int i = 0; i < pointer_count; i++) {
                        Log.i(TAG, "id : " + i);
                        x[i] = event.getX(i);
                        y[i] = event.getY(i);
                        mPointerId[i] = event.getPointerId(i);
                        touching[activePointerId] = true;
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    for (int i = 0; i < pointer_count; i++) {
                        x[i] = event.getX(i);
                        y[i] = event.getY(i);
                        mPointerId[i] = event.getPointerId(i);
                        touching[i] = true;
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    for (int i = 0; i < pointer_count; i++) {
                        if (i == activePointerId) {
                            touching[i] = false;
                            break;
                        }
                    }
                    break;
            }

            invalidate();

            return true;
        }
    }

//    Original Codes ( 원본 코드 ) - 멀티 터치에 대해 Circle을 못 그려줌.
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int index = event.getActionIndex();
//        int id = event.getPointerId(index);
//        Log.i(TAG, "id : " + id);
//        int action = event.getActionMasked();   // for Multi Pointers
//
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_POINTER_DOWN:
//                x[id] = event.getX();
//                y[id] = event.getY();
//                touching[id] = true;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                x[id] = event.getX();
//                y[id] = event.getY();
//                break;
//
//            case MotionEvent.ACTION_POINTER_UP:
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                touching[id] = false;
//                break;
//        }
//
//        invalidate();
//
//        return true;
//    }

}