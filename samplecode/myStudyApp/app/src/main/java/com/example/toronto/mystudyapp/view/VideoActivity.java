package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.toronto.mystudyapp.R;

public class VideoActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        video = (VideoView) findViewById(R.id.videoview);

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/raw/test_video");
        video.setVideoURI(uri);

        video.setMediaController(new MediaController(this));

        video.start();
    }
}
