package com.example.swipeup.view.animation_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
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

// Reference Homepage URL : https://brightwon.tistory.com/2

public class AnimationActivity extends AppCompatActivity {
    private static final String TAG = "AnimationActivity";

    LinearLayout linearLayout1;
    TextView textView1;
    ImageView imageView1;
    ImageView vertorImageView;

    AnimatedVectorDrawable vectorAnimation;
    ObjectAnimator objectAnimation1;
    boolean toggle = true;

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

                //imageView1.setInterpolator(new LinearInterpolator());
                //imageView1.setInterpolator(new AccelerateInterpolator())
                //imageView1.setInterpolator(new DecelerateInterpolator());
                //imageView1.setInterpolator(new AccelerateDecelerateInterpolator());
                //imageView1.setInterpolator(new SineInOut60());
                //imageView1.setInterpolator(new SineInOut33());
            }
        });


        /// Reference Homepage URL : https://brightwon.tistory.com/2
        vertorImageView = findViewById(R.id.anim_demo_vector_image);
        vectorAnimation = (AnimatedVectorDrawable) ContextCompat.getDrawable(this, R.drawable.animatorvectordrawable);
        vertorImageView.setImageDrawable(vectorAnimation);
        vectorAnimation.start();

        vertorImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vectorAnimation.start();
            }
        });


        // Reference Homepage URL : https://brightwon.tistory.com/2
        ImageView playView = findViewById(R.id.anim_demo_play_vector_image);
        AnimatedVectorDrawable animatedVector = (AnimatedVectorDrawable) ContextCompat.getDrawable(this, R.drawable.play_animation);
        playView.setImageDrawable(animatedVector);
        animatedVector.start();

        // Reference Homepage URL : https://ddolcat.tistory.com/485
        TextView textView2 = findViewById(R.id.anim_demo_text2);
        objectAnimation1 = ObjectAnimator.ofFloat(textView2, "translationX", 100f);
        objectAnimation1.setDuration(1000);
        objectAnimation1.start();
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle)
                    objectAnimation1 = ObjectAnimator.ofFloat(textView2, "translationX", 0.0f);
                else
                    objectAnimation1 = ObjectAnimator.ofFloat(textView2, "translationX", 100f);
                toggle = !toggle;

                objectAnimation1.start();
            }
        });


        // background.
        ObjectAnimator bgAnimation = ObjectAnimator.ofInt(linearLayout1.getBackground(), "Alpha", 255, 30);
        bgAnimation.setDuration(1500);
        bgAnimation.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        vectorAnimation.start();
    }
}