package com.example.swipeup.view.animation_example;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swipeup.R;

// Reference YouTube URL : http://youtube.com/watch?v=CPxkoe2MraA
// Reference Homepage URL : https://nextus.tistory.com/1

public class AnimationActivity extends AppCompatActivity {
    LinearLayout linearLayout1;
    TextView textView1;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        linearLayout1 = findViewById(R.id.anim_demo_root_layout);
        textView1 = findViewById(R.id.anim_demo_text);
        imageView1 = findViewById(R.id.anim_demo_image);

        int duration = 300;
        imageView1.setTranslationY(-20.0f);
        //imageView1.setTranslationY(-imageView1.getHeight());
        //imageView1.setTranslationX(-imageView1.getWidth());
        imageView1.animate().setDuration(duration)
                .translationY(0) //.translationX(0)
                .scaleXBy(1).scaleX(0.5f)
                .scaleYBy(1).scaleY(0.5f)
                .alphaBy(1).alpha(0.3f)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setTranslationY(-textView1.getHeight());
                    }
                });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setTranslationY(-textView1.getHeight());
                textView1.animate().setDuration(duration/2)
                        .translationY(0)
                        .alpha(1)
                        //.setInterpolator(new DecelerateInterpolator());
                        //.setInterpolator(new AccelerateDecelerateInterpolator());
                        .setInterpolator(new AccelerateInterpolator());
            }
        });

        //imageView1.setInterpolator(new LinearInterpolator());
        //imageView1.setInterpolator(new AccelerateInterpolator())
        //imageView1.setInterpolator(new DecelerateInterpolator());
        //imageView1.setInterpolator(new AccelerateDecelerateInterpolator());
        //imageView1.setInterpolator(new SineInOut60());
        //imageView1.setInterpolator(new SineInOut33());


        // Not Working - need to analyze this problem.
        int ALPHA_NONE = 0;
        int BACKGROUND_ALPHA_ENABLE = 128;
        int BACKGROUND_ALPHA_DISABLE = 153;
        int mBackGroundAlpha = ALPHA_NONE;
        Paint Paint_Black = new Paint();
        Paint_Black.setColor(Color.BLACK);
        Paint_Black.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint_Black.setAlpha(mBackGroundAlpha);

        //ObjectAnimator bgAnimation = ObjectAnimator.ofInt(Paint_Black, "Alpha", Paint_Black.getAlpha(), BACKGROUND_ALPHA_DISABLE);
        ObjectAnimator bgAnimation = ObjectAnimator.ofInt(Paint_Black, "Alpha", 0, 255);
        bgAnimation.setDuration(duration);
        bgAnimation.start();
    }
}