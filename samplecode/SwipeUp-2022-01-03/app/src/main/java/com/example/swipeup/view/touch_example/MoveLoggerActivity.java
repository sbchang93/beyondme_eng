package com.example.swipeup.view.touch_example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.swipeup.R;

// Reference GitHub : https://github.com/devunwired/custom-touch-examples

public class MoveLoggerActivity extends AppCompatActivity  implements View.OnTouchListener {
    public static final String TAG = "MoveLoggerActivity";

    /* Slop constant for this device */
    private int mTouchSlop;
    /* Initial touch point */
    private Point mInitialTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_logger);

        findViewById(R.id.view_logall).setOnTouchListener(this);
        findViewById(R.id.view_logslop).setOnTouchListener(this);

        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mInitialTouch = new Point();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mInitialTouch.set((int)event.getX(), (int)event.getY());
            //Must declare interest to get more events
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            switch (v.getId()) {
                case R.id.view_logall:
                    Log.i(TAG, String.format("Top Move: %.1f,%.1f", event.getX(), event.getY()));
                    break;
                case R.id.view_logslop:
                    Log.i(TAG, "mTouchSlop : " + mTouchSlop);
                    Log.i(TAG, "X distance : " + Math.abs(event.getX() - mInitialTouch.x));
                    Log.i(TAG, "Y distance : " + Math.abs(event.getY() - mInitialTouch.y));
                    if ( Math.abs(event.getX() - mInitialTouch.x) > mTouchSlop
                            || Math.abs(event.getY() - mInitialTouch.y) > mTouchSlop ) {
                        Log.i(TAG, String.format("Bottom Move: %.1f,%.1f", event.getX(), event.getY()));
                    } else {
                        Log.i(TAG, "Bottom Move: It moved below mTouchSlop");
                    }
                    break;
                default:
                    break;
            }
        }
        //Don't interefere when not necessary
        return false;
    }
}






