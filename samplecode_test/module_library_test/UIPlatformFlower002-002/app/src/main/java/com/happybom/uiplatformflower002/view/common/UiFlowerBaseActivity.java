package com.happybom.uiplatformflower002.view.common;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.happybom.uiplatformflower002.Configuration;

import io.reactivex.Observable;

public abstract class UiFlowerBaseActivity  extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Observable.just("onCreate")
                .doOnNext(v -> Configuration.init(this))
                . subscribe(v -> {
                }, t-> {
                    Log.d(TAG, "init failed" + t.toString());
                });
        super.onCreate(savedInstanceState);
    }


}
