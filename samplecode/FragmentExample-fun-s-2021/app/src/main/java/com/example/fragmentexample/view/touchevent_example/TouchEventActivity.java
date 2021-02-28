package com.example.fragmentexample.view.touchevent_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentexample.view.keydown_example.KeyDownView;

public class TouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TouchEventView(this));
    }
}