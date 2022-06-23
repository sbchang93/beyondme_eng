package com.example.swipeup.view.touch_example.touch_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.swipeup.R;

//Reference Home URL : https://bitsoul.tistory.com/169

public class MultiTouchActivity extends AppCompatActivity {

    int id[] = new int[3];
    int x[] = new int[3];
    int y[] = new int[3];
    String result;

    TextView tv1;
    TextView tv2;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);
        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);
        tv3 = findViewById(R.id.text3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointer_count = event.getPointerCount(); //현재 터치 발생한 포인트 수를 얻는다.
        if(pointer_count > 3) pointer_count = 3; //4개 이상의 포인트를 터치했더라도 3개까지만 처리를 한다.

        switch(event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: //한 개 포인트에 대한 DOWN을 얻을 때.
                result = "Single Touch : \n";
                id[0] = event.getPointerId(0); //터치한 순간부터 부여되는 포인트 고유번호.
                x[0] = (int) (event.getX());
                y[0] = (int) (event.getY());
                result = "ACTION_DOWN : \n";
                result += "("+x[0]+","+y[0]+")";
                tv1.setText(result);
                break;

            case MotionEvent.ACTION_POINTER_DOWN: //두 개 이상의 포인트에 대한 DOWN을 얻을 때.
                result = "Multi-Touch :\nACTION_POINTER_DOWN: \n";
                for(int i = 0; i < pointer_count; i++) {
                    id[i] = event.getPointerId(i); //터치한 순간부터 부여되는 포인트 고유번호.
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "id[" + id[i] + "] ("+x[i]+","+y[i]+")\n";
                }
                tv2.setText(result);
                break;

            case MotionEvent.ACTION_MOVE:
                result = "Multi and Single Touch\nMOVE:\n";
                for(int i = 0; i < pointer_count; i++) {
                    id[i] = event.getPointerId(i);
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "id[" + id[i] + "] ("+x[i]+","+y[i]+")\n";
                }
                break;
            case MotionEvent.ACTION_UP:
                result = "";
                break;
        }

        tv3.setText(result);

        return super.onTouchEvent(event);
    }

}