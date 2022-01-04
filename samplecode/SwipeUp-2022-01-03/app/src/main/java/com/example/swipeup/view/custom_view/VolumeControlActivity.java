package com.example.swipeup.view.custom_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

import com.example.swipeup.R;

// Reference Home URL : https://wpioneer.tistory.com/27

public class VolumeControlActivity extends AppCompatActivity {
    private static final String TAG = "VolumeControlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_control);

        final RatingBar ratingBar = findViewById(R.id.rating_bar);
        VolumeControlView volumeControlView = findViewById(R.id.volume_control_view);
        volumeControlView.setKnobListener(new VolumeControlView.KnobListener() {
            @Override
            public void onChanged(double angle) {
                float rating = ratingBar.getRating();
                if(angle > 0 && rating <7.0) {
                    ratingBar.setRating(rating+0.5f);
                    Log.i(TAG, "Current Rating Value" + String.valueOf(ratingBar.getRating()));
                } else if(rating > 0.0) {
                    ratingBar.setRating(rating-0.5f);
                    Log.i(TAG, "Current Rating Value" + String.valueOf(ratingBar.getRating()));
                }

            }
        });
    }
}