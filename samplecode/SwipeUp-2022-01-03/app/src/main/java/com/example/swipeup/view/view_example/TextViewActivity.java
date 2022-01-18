package com.example.swipeup.view.view_example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

// Reference Homepage : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=lowmans&logNo=100113911560

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("this is test");
        MyDrawable back = new MyDrawable();
        tv.setBackgroundDrawable(back);
        setContentView(tv);
    }
}

class MyDrawable extends Drawable {

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width,height;
//        width = canvas.getClipBounds().right;
//        height = canvas.getClipBounds().bottom;

        width = 300;
        height = 100;

        Paint paint = new Paint();
        paint.setColor(0x3341CDCD);
        //p.setColor(0x330000FF);

        RectF rect = new RectF(3.0f, 3.0f, width - 3.0f, height- 3.0f);
        canvas.drawRoundRect(rect, 15.0f, 15.0f, paint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}