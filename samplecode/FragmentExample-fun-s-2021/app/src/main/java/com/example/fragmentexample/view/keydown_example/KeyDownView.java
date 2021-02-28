package com.example.fragmentexample.view.keydown_example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

import com.example.fragmentexample.utils.DisplayUtil;

public class KeyDownView extends View {
    public String mDirection = "없음";

    float x_0dp = DisplayUtil.convertDpToPixel(getContext(), 0);
    float y_20dp = DisplayUtil.convertDpToPixel(getContext(), 20);
    float TextSize_20dp = DisplayUtil.convertDpToPixel(getContext(), 20);

    public KeyDownView(Context context) {
        super(context);
        setFocusable(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
            mDirection = "왼쪽";
        else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
            mDirection = "오른쪽";
        else if (keyCode == KeyEvent.KEYCODE_DPAD_UP)
            mDirection = "위쪽";
        else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
            mDirection = "아랫쪽";

        invalidate();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(TextSize_20dp);
        p.setColor(Color.BLUE);
        canvas.drawText("입력된 방향 키 : " + mDirection, x_0dp, y_20dp, p);
    }

}
