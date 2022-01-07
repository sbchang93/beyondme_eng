package com.example.swipeup.view.animation_example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.swipeup.R;

// Reference Homepage URL : https://developer.android.com/guide/topics/graphics/drawable-animation?hl=ko#java

public class AnimationDrawableActivity extends AppCompatActivity {

    AnimationDrawable weatherAnimation;
    AnimationDrawable mainBgAnimation;
    ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);

        weatherImage = findViewById(R.id.imageView1);
        weatherImage.setBackgroundResource(R.drawable.weather_change);
        weatherAnimation = (AnimationDrawable) weatherImage.getBackground();

        weatherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherAnimation.stop();
                weatherAnimation.start();
            }
        });

        LinearLayout mainLinearLayout =  findViewById(R.id.linear_layout_animation);
        mainLinearLayout.setBackgroundResource(R.drawable.background_change);
        mainBgAnimation = (AnimationDrawable) mainLinearLayout.getBackground();

        mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainBgAnimation.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        weatherAnimation.start();

        mainBgAnimation.start();
    }
}