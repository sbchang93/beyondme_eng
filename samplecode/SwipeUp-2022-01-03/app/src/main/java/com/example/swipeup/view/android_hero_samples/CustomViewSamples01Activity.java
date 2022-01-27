package com.example.swipeup.view.android_hero_samples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.swipeup.R;
import com.example.swipeup.view.android_hero_samples.customview.TitleBar;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class CustomViewSamples01Activity extends AppCompatActivity {
    private static final String TAG = "AnimationActivity";

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_samples01);

        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setOnTitleBarLeftClickListener(new TitleBar.titleBarLeftClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(CustomViewSamples01Activity.this, "left", Toast.LENGTH_SHORT).show();
            }
        });
        mTitleBar.setOnTitleBarRightClickListener(new TitleBar.titleBarRightClickListener() {
            @Override
            public void rightClick() {
                Toast.makeText(CustomViewSamples01Activity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}