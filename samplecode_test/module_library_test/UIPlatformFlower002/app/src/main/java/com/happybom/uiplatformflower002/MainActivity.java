package com.happybom.uiplatformflower002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.happybom.uiplatformflower002.R;
import com.happybom.uiplatformflower002.ui.runScreen02Activity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import com.happybom.testmodule003.MyTest;

public class MainActivity extends AppCompatActivity {

    private static boolean runOneTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Observable.just("runScreen02")
//                .delay(1, TimeUnit.SECONDS)
//                .subscribe(s -> {
//                    startActivity(new Intent(this, runScreen02Activity.class));
//                    //finish();
//                });

        MyTest str = new MyTest();
        str.showMyToast(this, getResources());
    }
}
