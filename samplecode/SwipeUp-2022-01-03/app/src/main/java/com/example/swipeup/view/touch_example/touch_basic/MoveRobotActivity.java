package com.example.swipeup.view.touch_example.touch_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.swipeup.R;

//Reference Home URL : https://bitsoul.tistory.com/58

public class MoveRobotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_robot);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout1);
        ll.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                        // 이미지 뷰의 위치를 옮기기
                        imageView.setX(event.getX());
                        imageView.setY(event.getY());
                }
                return true;
            }
        });
    }
}