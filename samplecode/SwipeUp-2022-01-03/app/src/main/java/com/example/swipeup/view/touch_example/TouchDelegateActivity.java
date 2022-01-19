package com.example.swipeup.view.touch_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// Reference GitHub : https://github.com/devunwired/custom-touch-examples

public class TouchDelegateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TouchDelegateLayout layout = new TouchDelegateLayout(this);
        setContentView(layout);
    }
}