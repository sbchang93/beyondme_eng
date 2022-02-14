package com.example.swipeup.view.android_hero_samples;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.swipeup.R;
import com.example.swipeup.view_utils.ImageHelper;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class PixelsEffectActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);
        Bitmap _bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_test2);
        imageView1 = findViewById(R.id.imageview1);
        imageView2 = findViewById(R.id.imageview2);
        imageView3 = findViewById(R.id.imageview3);
        imageView4 = findViewById(R.id.imageview4);

        imageView1.setImageBitmap(_bitmap);
        imageView2.setImageBitmap(ImageHelper.handleImageNegative(_bitmap));
        imageView3.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(_bitmap));
        imageView4.setImageBitmap(ImageHelper.handleImagePixelsRelief(_bitmap));
    }
}
