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
//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tkddlf4209&logNo=221638722158

public class ViewPropertyAnimatorActivity extends AppCompatActivity {
    private static final String TAG = "ViewPropertyAnimatorActivity";

    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);

        textView1 = findViewById(R.id.text1);
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

        textView2 = findViewById(R.id.text2);
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


        // https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tkddlf4209&logNo=221638722158
        // Increase the height(Y) until it reaches 300f. ( Repeat infinitely. )
        ImageView imageBear1 = findViewById(R.id.bear1);
        float translation_value = 300f;
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageBear1, "translationY", translation_value);
        anim1.setDuration(5000); // duration 5 seconds
        anim1.setRepeatCount(ValueAnimator.INFINITE);
        //anim1.setRepeatMode(ValueAnimator.REVERSE);
        //anim.setInterpolator(new CycleInterpolator(1));
        anim1.start();

        // 0 ~ 300 : <=== animation.getAnimatedValue()
        ImageView imageCat = findViewById(R.id.cat);
        int height_value = 300;
        ValueAnimator anim2 = ValueAnimator.ofInt(0, height_value);
        anim2.setDuration(5000); // duration 5 seconds
        anim2.setRepeatCount(ValueAnimator.INFINITE);
        anim2.setRepeatMode(ValueAnimator.REVERSE);
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();  // 0 ~ 300
                imageCat.getLayoutParams().height = value.intValue();
                imageCat.requestLayout();
            }
        });
        anim2.start();


        //https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tkddlf4209&logNo=221638722158
        View redScanLine = findViewById(R.id.scan_line);
        float translation_value3 = 300f;
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(redScanLine, "translationY", translation_value3);

        anim3.setDuration(5000); // duration 5 seconds
        anim3.setRepeatCount(ValueAnimator.INFINITE);
        anim3.setRepeatMode(ValueAnimator.REVERSE);
        //anim4.setInterpolator(new CycleInterpolator(1));
        anim3.start();

        View greenScan = findViewById(R.id.scan_fill);
        int height_value4 = 300;
        ValueAnimator anim4 = ValueAnimator.ofInt(0, height_value4);
        anim4.setDuration(5000); // duration 5 seconds
        anim4.setRepeatCount(ValueAnimator.INFINITE);
        anim4.setRepeatMode(ValueAnimator.REVERSE);
        anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();  // 0 ~ 300
                greenScan.getLayoutParams().height = value.intValue();
                greenScan.requestLayout();
            }
        });
        anim4.start();




    }
}