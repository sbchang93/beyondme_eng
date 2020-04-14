package com.happybom.uiplatformflower002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.happybom.commonlibrary.BaseMainActivity;
import com.happybom.uiplatformflower002.R;
import com.happybom.uiplatformflower002.ui.runScreen02Activity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import com.happybom.testmodule003.MyTest;

public class MainActivity extends BaseMainActivity {
    private static final String TAG = "MainActivity";

    @Override
    //protected void onCreate(Bundle savedInstanceState)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.button.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        this.button.setText("레드앱 버튼");

    }

//    private static boolean runOneTime = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        Observable.just("runScreen02")
////                .delay(1, TimeUnit.SECONDS)
////                .subscribe(s -> {
////                    startActivity(new Intent(this, runScreen02Activity.class));
////                    //finish();
////                });
//
//        MyTest str = new MyTest();
//        str.showMyToast(getApplicationContext(), getResources());
//    }
}
