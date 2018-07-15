package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.toronto.mystudyapp.R;

public class AudioActivity extends Activity {

    MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);
    }


    public void onSound (View v) {
        if (mPlayer == null) {
            //mPlayer = MediaPlayer.create(this, R.raw.test);
            mPlayer = MediaPlayer.create(this, R.raw.test_01);
            mPlayer.start();
        }
    }

    public void onSoundOff (View v) {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer = null;
        }
    }
}
