package com.example.toronto.mystudyapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.toronto.mystudyapp.view.HandlerActivity;
import com.example.toronto.mystudyapp.view.HashMapActivity;
import com.example.toronto.mystudyapp.view.ListView2Activity;
import com.example.toronto.mystudyapp.view.ListViewActivity;
import com.example.toronto.mystudyapp.view.LocationManagerActivity;
import com.example.toronto.mystudyapp.view.RecyclerViewActivity;
import com.example.toronto.mystudyapp.view.ViewActivity;

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
                    startActivity(new Intent(this, RecyclerViewActivity.class));
                    //startActivity(new Intent(this, ListViewActivity.class));
                    //startActivity(new Intent(this, ListView2Activity.class));
                    //startActivity(new Intent(this, HashMapActivity.class));
                    //startActivity(new Intent(this, ViewActivity.class));
                    //startActivity(new Intent(this, HandlerActivity.class));
                    //startActivity(new Intent(this, LocationManagerActivity.class));


                    finish();
                });

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                Toast.makeText(MainActivity.this, "handler in MainActivity" , Toast.LENGTH_SHORT).show();
            }
        };

        handle.post(callback);


//        Observable.just("")
//                .delay(1, TimeUnit.SECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
//                    if (true) {
//                        startActivity(new Intent(this, RecyclerViewActivity.class));
//                    } else {
//                        startActivity(new Intent(this, EmptyActivity.class));
//                    }
//                    finish();
//                });

    }
}
