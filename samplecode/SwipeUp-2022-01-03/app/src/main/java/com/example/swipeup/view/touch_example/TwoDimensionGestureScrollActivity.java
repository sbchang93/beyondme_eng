package com.example.swipeup.view.touch_example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.swipeup.R;

import java.io.IOException;
import java.io.InputStream;

// Reference GitHub : https://github.com/devunwired/custom-touch-examples

public class TwoDimensionGestureScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_scroll);
        ImageView iv = (ImageView) findViewById(R.id.imageView);

        Bitmap image;
        try {
            InputStream in = getAssets().open("android.jpg");
            image = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            image = null;
        }
        iv.setImageBitmap(image);
    }
}