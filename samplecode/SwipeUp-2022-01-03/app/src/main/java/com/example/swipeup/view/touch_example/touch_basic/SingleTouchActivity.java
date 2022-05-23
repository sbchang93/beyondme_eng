package com.example.swipeup.view.touch_example.touch_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.swipeup.R;

//Reference Home URL : https://bitsoul.tistory.com/169

public class SingleTouchActivity extends AppCompatActivity {

    int x;
    int y;
    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_touch);
        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                x = (int) (event.getX());
                y = (int) (event.getY());
                tv1.setText("DOWN("+ x +"," + y +")");
                tv2.setText("DOWN("+ x +"," + y +")");
                break;

            case MotionEvent.ACTION_MOVE:
                x = (int) (event.getX());
                y = (int) (event.getY());
                tv2.setText("MOVE("+ x +","+ y +")");
                break;
        }
        return super.onTouchEvent(event);
    }

}