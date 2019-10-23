package com.example.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fragmentexample.view.FragmentLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable.just("")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, FragmentLayout.class));
                    finish();
                });

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                //Toast.makeText(MainActivity.this, "handler in MainActivity" , Toast.LENGTH_SHORT).show();
            }
        };

        handle.post(callback);
    }
}
