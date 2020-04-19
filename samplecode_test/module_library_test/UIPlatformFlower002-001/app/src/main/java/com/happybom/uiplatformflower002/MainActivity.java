package com.happybom.uiplatformflower002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.happybom.blueapp.BlueApp;
import com.happybom.commonlibrary.BaseMainActivity;
import com.happybom.redapp.RedApp;
import com.happybom.uiplatformflower002.R;
import com.happybom.uiplatformflower002.ui.runScreen02Activity;
import com.happybom.uiplatformflower002.view.common.UiFlowerBaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends UiFlowerBaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //RedApp redApp = Injector.provideRedApp(this);

        Observable.just("RedApp")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> {
					// Run RedMainActivity via the RedApp interface
                    RedApp redApp = Injector.provideRedApp(this);
                    redApp.startApp(this);

                    //startActivity(new Intent(this, runScreen02Activity.class));
                    //finish();
                }, t ->{});

        Observable.just("BlueApp")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(s -> {
                    BlueApp blueApp = Injector.provideBlueApp(this);
                    blueApp.startApp(this);
                }, t ->{});


        //        MyTest str = new MyTest();
        //        str.showMyToast(getApplicationContext(), getResources());

    }
}
