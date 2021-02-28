package com.example.fragmentexample.view.keydown_example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

import com.example.fragmentexample.utils.DisplayUtil;

public class KeyDownView extends View {
    public String mDirection = "None";

    float _0dp = DisplayUtil.convertDpToPixel(getContext(), 0);
    float _20dp = DisplayUtil.convertDpToPixel(getContext(), 20);

    public KeyDownView(Context context) {
        super(context);
        setFocusable(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
            mDirection = "Left";
        else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
            mDirection = "Right";
        else if (keyCode == KeyEvent.KEYCODE_DPAD_UP)
            mDirection = "Up";
        else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
            mDirection = "Down";

        invalidate();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(_20dp);
        p.setColor(Color.BLUE);
        canvas.drawText("Input Direction : " + mDirection, _0dp, _20dp, p);
    }

}
