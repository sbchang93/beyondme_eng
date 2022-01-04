package com.example.swipeup.view.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.swipeup.R;

// Reference Home URL : https://wpioneer.tistory.com/27

public class VolumeControlView extends androidx.appcompat.widget.AppCompatImageView implements View.OnTouchListener {
    private static final String TAG = "VolumeControlView";

    private double mAngle = 0.0;
    private KnobListener mKnobListener;
    float x, y;
    float mx, my;

    public VolumeControlView(Context context) {
        super(context);
        this.setImageResource(R.drawable.leftarrow);
        this.setOnTouchListener(this);
    }

    public VolumeControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setImageResource(R.drawable.leftarrow);
        this.setOnTouchListener(this);
    }

    public interface KnobListener {
        public void onChanged(double angle);
    }

    public void setKnobListener(KnobListener listener) {
        mKnobListener = listener;
    }

    private double getAngle(float x, float y) {
        mx = x - (getWidth() / 2.0f);
        my = (getHeight() / 2.0f) - y;
        double degree = Math.atan2(mx, my) * 180.0 / 3.141592;
        return degree;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        mAngle = getAngle(x, y);
        Log.i(TAG, "mAngle" + String.valueOf(mAngle));
        invalidate();
        mKnobListener.onChanged(mAngle);
        return true;
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //c.save();
        canvas.rotate((float)mAngle, getWidth()/2, getHeight()/2);
        super.onDraw(canvas);
        //canvas.restore();
    }
}
