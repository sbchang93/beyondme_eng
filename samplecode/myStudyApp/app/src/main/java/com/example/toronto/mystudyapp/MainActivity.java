package com.example.toronto.mystudyapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.view.AudioActivity;
import com.example.toronto.mystudyapp.view.CallOtherActivity;
import com.example.toronto.mystudyapp.view.DataBindingTest01Activity;
import com.example.toronto.mystudyapp.view.Dialog2Activity;
import com.example.toronto.mystudyapp.view.Dialog3Activity;
import com.example.toronto.mystudyapp.view.Dialog4Activity;
import com.example.toronto.mystudyapp.view.Dialog5Activity;
import com.example.toronto.mystudyapp.view.DialogActivity;
import com.example.toronto.mystudyapp.view.DialogTest01Activity;
import com.example.toronto.mystudyapp.view.DialogTest02Activity;
import com.example.toronto.mystudyapp.view.DialogTest03Activity;
import com.example.toronto.mystudyapp.view.DialogTest04Activity;
import com.example.toronto.mystudyapp.view.DrawActivity;
import com.example.toronto.mystudyapp.view.DrawTestActivity;
import com.example.toronto.mystudyapp.view.HandlerActivity;
import com.example.toronto.mystudyapp.view.HashMapActivity;
import com.example.toronto.mystudyapp.view.ImageActivity;
import com.example.toronto.mystudyapp.view.ListView2Activity;
import com.example.toronto.mystudyapp.view.ListView3Activity;
import com.example.toronto.mystudyapp.view.ListViewActivity;
import com.example.toronto.mystudyapp.view.LiveDataActivity;
import com.example.toronto.mystudyapp.view.LocationManagerActivity;
import com.example.toronto.mystudyapp.view.NameActivityForViewModel;
import com.example.toronto.mystudyapp.view.ObserverActivity;
import com.example.toronto.mystudyapp.view.ObserverMainActivity;
import com.example.toronto.mystudyapp.view.RecyclerViewActivity;
import com.example.toronto.mystudyapp.view.ReflectionActivity;
import com.example.toronto.mystudyapp.view.SaveState02Activity;
import com.example.toronto.mystudyapp.view.SaveState03Activity;
import com.example.toronto.mystudyapp.view.Test01Activity;
import com.example.toronto.mystudyapp.view.Test02_MyViewActivity;
import com.example.toronto.mystudyapp.view.TextViewActivity;
import com.example.toronto.mystudyapp.view.UserActivity;
import com.example.toronto.mystudyapp.view.VideoActivity;
import com.example.toronto.mystudyapp.view.ViewActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                //Toast.makeText(MainActivity.this, "handler in MainActivity" , Toast.LENGTH_SHORT).show();
            }
        };
        handle.post(callback);

        findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
        findViewById(R.id.dbSamples).setOnClickListener(mClickListener);
        findViewById(R.id.observalbeSamples).setOnClickListener(mClickListener);
        findViewById(R.id.listMenuSamples).setOnClickListener(mClickListener);
        findViewById(R.id.drawSamples).setOnClickListener(mClickListener);
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.normalSamples:
                    launchSampleActivities();
                    break;
                case R.id.dbSamples:
                    launchRoomDBSamples();
                    break;
                case R.id.observalbeSamples:
                    launchObservableSamples();
                    break;
                case R.id.listMenuSamples:
                    listMenusSamples();
                    break;
                case R.id.drawSamples:
                    drawSamplesSamples();
                    break;

                 // Remember ===> findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
            }
        }
    };

    private void launchRoomDBSamples() {
        Observable.just("Room DB")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, UserActivity.class));
                    finish();
                });
    }

    private void launchObservableSamples() {
                Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    if (true) {
                        startActivity(new Intent(this, RecyclerViewActivity.class));
                    } else {
                        startActivity(new Intent(this, ReflectionActivity.class));
                    }
                    startActivity(new Intent(this, ObserverMainActivity.class));

//                    startActivity(new Intent(this, LiveDataActivity.class));
//                    startActivity(new Intent(this, ObserverActivity.class));
                    finish();
                });
    }

    private void listMenusSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, ListView3Activity.class));
                    startActivity(new Intent(this, RecyclerViewActivity.class));
                    startActivity(new Intent(this, ListViewActivity.class));
                    startActivity(new Intent(this, ListView2Activity.class));

                    finish();
                });
    }

    private void drawSamplesSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, DrawActivity.class));
                    startActivity(new Intent(this, ImageActivity.class));
                    startActivity(new Intent(this, VideoActivity.class));
                    startActivity(new Intent(this, DrawTestActivity.class));
                    startActivity(new Intent(this, ReflectionActivity.class));

                    finish();
                });
    }

    private void launchSampleActivities() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, LiveDataActivity.class));
                    //startActivity(new Intent(this, ObserverActivity.class));

                    startActivity(new Intent(this, CallOtherActivity.class));
                    startActivity(new Intent(this, HashMapActivity.class));
                    startActivity(new Intent(this, ViewActivity.class));
                    startActivity(new Intent(this, HandlerActivity.class));
                    startActivity(new Intent(this, LocationManagerActivity.class));
                    startActivity(new Intent(this, TextViewActivity.class));
                    startActivity(new Intent(this, AudioActivity.class));
                    startActivity(new Intent(this, DialogActivity.class));
                    startActivity(new Intent(this, Dialog2Activity.class));
                    startActivity(new Intent(this, Dialog3Activity.class));
                    startActivity(new Intent(this, Dialog4Activity.class));
                    startActivity(new Intent(this, Dialog5Activity.class));
                    startActivity(new Intent(this, Test01Activity.class));
                    startActivity(new Intent(this, Test02_MyViewActivity.class));
                    startActivity(new Intent(this, SaveState02Activity.class));
                    startActivity(new Intent(this, SaveState03Activity.class));
                    startActivity(new Intent(this, DialogTest01Activity.class));
                    startActivity(new Intent(this, DialogTest02Activity.class));
                    startActivity(new Intent(this, DialogTest03Activity.class));
                    startActivity(new Intent(this, DialogTest04Activity.class));
                    startActivity(new Intent(this, DataBindingTest01Activity.class));
                    startActivity(new Intent(this, NameActivityForViewModel.class));
                    finish();
                });
    }



}


