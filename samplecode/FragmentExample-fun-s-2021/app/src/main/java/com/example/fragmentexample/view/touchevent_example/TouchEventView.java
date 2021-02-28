package com.example.fragmentexample.view.touchevent_example;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.DisplayUtil;

public class TouchEventView extends View {

    private int mX;
    private int mY;
    private String mActionName ="None";

    float _0dp = DisplayUtil.convertDpToPixel(getContext(), 0);
    float _20dp = DisplayUtil.convertDpToPixel(getContext(), 20);
    float _40dp = DisplayUtil.convertDpToPixel(getContext(), 40);

    public TouchEventView(Context context) {
        super(context);
        mX = 100;
        mY = 500;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mX = (int) event.getX();
        mY = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mActionName = "ACTION_DOWN";
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mActionName = "ACTION_MOVE";
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            mActionName = "ACTION_UP";
            mX = 100;
            mY = 500;
        }

        invalidate();
        return true;   // for ACTION_MOVE, ACTION_UP Event
        //return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon), mX, mY, null);

        Paint p = new Paint();
        p.setTextSize(_20dp);
        p.setColor(Color.BLUE);
        canvas.drawText("Event Position X:" + mX + ", Y:" + mY, _0dp, _20dp, p);
        canvas.drawText("Action Name : " + mActionName, _0dp, _40dp, p);
    }

}
