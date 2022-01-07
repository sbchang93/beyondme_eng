package com.example.swipeup.view.animation_example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.swipeup.R;

// Reference Homepage URL : https://developer.android.com/guide/topics/graphics/drawable-animation?hl=ko#java
// Reference Homepage URL :  http://www.gisdeveloper.co.kr/?p=10261

public class AnimationDrawableActivity extends AppCompatActivity {

    AnimationDrawable weatherAnimation;
    AnimationDrawable mainBgAnimation;
    ImageView weatherImage;
    Button myButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);

        weatherImage = findViewById(R.id.anim_drawable_image);
        weatherImage.setBackgroundResource(R.drawable.weather_change);
        weatherAnimation = (AnimationDrawable) weatherImage.getBackground();

        weatherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherAnimation.stop();
                weatherAnimation.start();
            }
        });

        LinearLayout mainLinearLayout =  findViewById(R.id.anim_drawable_root_layout);
        mainLinearLayout.setBackgroundResource(R.drawable.background_change);
        mainBgAnimation = (AnimationDrawable) mainLinearLayout.getBackground();

        mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainBgAnimation.start();
            }
        });

        myButtonView = findViewById(R.id.my_button_view);
        myButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myButtonView.animate()
                        .scaleX(5.0f)
                        .scaleY(5.0f)
                        .alpha(0.0f)
                        .translationX(400.0f)
                        .translationY(400.0f)
                        .rotation(360.0f)
                        .setDuration(1000)
                        .withStartAction(new Runnable() {
                            public void run() {
                                Toast.makeText(AnimationDrawableActivity.this, "애니메이션 시작", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .withEndAction(new Runnable() {
                            public void run() {
                                myButtonView.animate()
                                        .scaleX(1.0f).scaleY(1.0f)
                                        .alpha(1.0f)
                                        .x(0.0f).y(0.0f)
                                        .rotation(0.0f);
                                Toast.makeText(AnimationDrawableActivity.this, "완료", Toast.LENGTH_SHORT).show();
                            }
                        });
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