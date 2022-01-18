package com.example.swipeup.view.view_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;

import com.example.swipeup.R;

// Reference Homepage : https://pythonq.com/so/android/120804

public class CanvasActivity extends AppCompatActivity {

    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new MyView(this);
        setContentView(myView);
    }
}

class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawRect(0, 0, 500, 500, paint);
        // canvas.clipRect(0,0, 200,200);           // 그리는 영역을 (0,0) ~ (200,200)으로 줄임
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,300,300, paint); // 300 x 300으로 크게 그려도, 200 x 200 영역에만 그려짐.


        paint.setColor(Color.BLACK);
        // // //canvas.clipRect(0, 600, 500, 600 + 500);   //(동작 안됨) <= 앞에서 canvas.clipRect 영역을 잡아서 사용하면, 밑에서 canvas.clipRect 다시 잡으면 동작 안 됨.
        canvas.drawRect(0, 600, 500, 600 + 500, paint);

        paint.setColor(Color.YELLOW);
        canvas.clipRect(0,600, 200,600+200);
        canvas.drawRect(0,600,75,600+75, paint);

    }
}