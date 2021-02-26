package com.example.fragmentexample.view.image_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ImageExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ImageView(this));

        //setContentView(R.layout.main);
    }
}