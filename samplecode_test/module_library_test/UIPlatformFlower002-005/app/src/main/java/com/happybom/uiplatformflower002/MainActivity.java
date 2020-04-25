package com.happybom.uiplatformflower002;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.happybom.blueapp.BlueApp;
import com.happybom.uiplatformflower002.view.common.UiFlowerBaseActivity;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends UiFlowerBaseActivity {
    private static final String TAG = "UIPFlower_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // Remove Status Bar ( https://commin.tistory.com/63 )
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Observable.just("RedApp")
                .delay(0, TimeUnit.SECONDS)
                .subscribe(s -> {

                    try {
                        //Reflection Homepage URL : https://www.charlezz.com/?p=756
                        Class redMainActivity = Class.forName("com.happybom.redapp.RedMainActivity");
                        Method m[] = redMainActivity.getDeclaredMethods();
                        for (int i = 0; i < m.length; i++)
                            Log.d(TAG, "Method :" + m[i].toString());

                        startActivity(new Intent(this, redMainActivity));
                    } catch (Throwable e) {
                        Log.d(TAG, "" + e);
                    }


                    // Run RedMainActivity via the RedApp interface
                    //RedApp redApp = Injector.provideRedApp(this);
                    //redApp.startApp(this);

                    //startActivity(new Intent(this, runScreen02Activity.class));
                    //finish();
                }, t ->{});

        Observable.just("BlueApp")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> {
                    BlueApp blueApp = Injector.provideBlueApp(this);
                    blueApp.startApp(this);
                }, t ->{});

        Observable.just("tabscreen")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(s -> {
                    try {
                        Class tabscreenActivity = Class.forName("com.happybom.tabscreen.ui.tabscreen.TabScreenActivity");
                        startActivity(new Intent(this, tabscreenActivity));
                    } catch (Throwable e) {
                        Log.d(TAG, "" + e);
                    }
                }, t ->{});


        //        MyTest str = new MyTest();
        //        str.showMyToast(getApplicationContext(), getResources());

    }
}
