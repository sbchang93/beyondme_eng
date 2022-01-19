package com.example.swipeup.view.touch_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

// Reference GitHub : https://github.com/devunwired/custom-touch-examples

public class TouchForwardActivity extends AppCompatActivity {
    public static final String TAG = "TouchForwardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TouchForwardLayout layout = new TouchForwardLayout(this);

        Button button = new Button(this);
        button.setText("You Can't Miss Me!");

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        layout.addView(button, lp);
        setContentView(layout);
    }
}