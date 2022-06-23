package com.example.swipeup.view.winset_example;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.example.swipeup.R;

// Reference Home URL : https://m.blog.naver.com/manhdh/120151543622

public class Dialog01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog01);

        Dialog myDial = new Dialog(this,R.style.customDialog);
        myDial.setTitle("Test Title");
        myDial.setContentView(new MyLayer(this));
        myDial.create();
        myDial.show();

        new AlertDialog.Builder(this)
                .setTitle("알림 Title")
                .setMessage("Message Body 내용")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    public class MyLayer extends View {

        private Paint mPaint;

        public MyLayer(Context context) {
            super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //canvas.drawColor(Color.WHITE);
            canvas.drawColor(Color.BLACK);
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(50, 50, 50, mPaint);
            canvas.saveLayerAlpha(0, 0, 200, 200, 255);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(100, 100, 50, mPaint);
            canvas.restore();
        }
    }
}