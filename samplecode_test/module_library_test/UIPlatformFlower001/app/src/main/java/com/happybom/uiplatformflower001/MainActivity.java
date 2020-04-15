package com.happybom.uiplatformflower001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.happybom.testmodule003.MyTest;
import com.happybom.uiplatformflower001.ui.runScreen02Activity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.just("runScreen02")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, runScreen02Activity.class));
                    //finish();
                });

        MyTest str = new MyTest();
        str.showMyToast(getApplicationContext(), getResources());
    }


}
