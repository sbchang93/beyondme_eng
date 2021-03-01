package com.example.fragmentexample.view.gesture_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentexample.R;

// Reference Homepage : https://fluorite94.tistory.com/117

public class GestureActivity extends AppCompatActivity {
    TextView mTextView = null;
    GestureDetector mGestureDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        mTextView = (TextView) findViewById(R.id.gesture_text_view);
        View gestureView = findViewById(R.id.gesture_view);

        gestureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                mTextView.append("onDown Call" + "\n");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                mTextView.append("onShowPress Call" + "\n");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                mTextView.append("onSingleTapUp Call" + "\n");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                mTextView.append("onScroll Call (" + v + " * " + v1 + ")" + "\n");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                mTextView.append("onLongPress Call" + "\n");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                mTextView.append("onFling Call (" + v + " * " + v1 + ")" + "\n");
                return true;
            }
        });
    }

}



