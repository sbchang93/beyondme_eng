package com.example.toronto.mystudyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.toronto.mystudyapp.view.EmptyActivity;
import com.example.toronto.mystudyapp.view.RecyclerViewActivity;

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
                    if (true) {
                        startActivity(new Intent(this, RecyclerViewActivity.class));
                    } else {
                        startActivity(new Intent(this, EmptyActivity.class));
                    }
                    finish();
                });

    }
}
