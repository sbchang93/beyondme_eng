package com.example.swipeup.view.animation_example;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipeup.R;

// Reference Home URL : https://ichi.pro/ko/eotteon-android-animatorga-sayonghagie-jeoghabhangayo-227415534195479

public class ViewPropertyAnimatorActivity extends AppCompatActivity {
    private static final String TAG = "ViewPropertyAnimatorActivity";

    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);

        textView1 = findViewById(R.id.anim_property_text1);
        //iewPropertyAnimator animationTextView1 = textView1.animate();
        textView1.animate().rotationX(3600f)
                .setDuration(5000)
                .withStartAction(new Runnable() {
                    public void run() {
                        Toast.makeText(ViewPropertyAnimatorActivity.this, "애니메이션 시작", Toast.LENGTH_SHORT).show();
                    }
                })
                .withEndAction(new Runnable() {
                    public void run() {
                        Toast.makeText(ViewPropertyAnimatorActivity.this, "애니메이션 완료", Toast.LENGTH_SHORT).show();
                    }
                });

        textView2 = findViewById(R.id.anim_property_text2);
        textView2.animate()
                .rotationX(3600f)
                .setDuration(3000)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .withStartAction(new Runnable() {
                    public void run() {
                    }
                })
                .withEndAction(new Runnable() {
                    public void run() {
                        // Not working.
                        ObjectAnimator animationTextView2 = ObjectAnimator.ofFloat(textView2, "Alpha", 255, 40);
                        animationTextView2.setInterpolator(new AccelerateDecelerateInterpolator());
                        animationTextView2.setRepeatCount(ValueAnimator.INFINITE);
                        animationTextView2.setRepeatMode(ValueAnimator.REVERSE);
                        animationTextView2.start();
                    }
                });


    }
}