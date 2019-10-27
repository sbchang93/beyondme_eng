package com.example.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentexample.util.Logger;
import com.example.fragmentexample.view.FragmentLayout;
import com.example.fragmentexample.view.ViewPagerActivity;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private HashMap<Integer, String> sampleNameMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.d(TAG, "onCreate ... ");

        Observable.just("")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> {
                    // runOnUiThread : https://itmining.tistory.com/6
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "An Observable run once in MainActivity", Toast.LENGTH_LONG).show();
                        }
                    });

//                    startActivity(new Intent(this, FragmentLayout.class));
//                    finish();

                });

        //Handler
        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                Toast.makeText(MainActivity.this, "An handler run once in MainActivity" , Toast.LENGTH_LONG).show();
            }
        };

        handle.post(callback);

        //Button Event
        findViewById(R.id.normal_samples).setOnClickListener(mClickListener);
        findViewById(R.id.db_samples).setOnClickListener(mClickListener);
        findViewById(R.id.observalbe_samples).setOnClickListener(mClickListener);
        findViewById(R.id.list_menu_samples).setOnClickListener(mClickListener);
        findViewById(R.id.draw_samples).setOnClickListener(mClickListener);
        findViewById(R.id.book_fragment_samples).setOnClickListener(mClickListener);
        findViewById(R.id.view_page_samples).setOnClickListener(mClickListener);


        // ------------------------------------------------------------------------------------------------
        // For Debugging Message
        sampleNameMap.put(R.id.normal_samples, "R.id.normal_samples");
        sampleNameMap.put(R.id.db_samples, "R.id.db_samples");
        sampleNameMap.put(R.id.observalbe_samples, "R.id.observalbe_samples");
        sampleNameMap.put(R.id.list_menu_samples, "R.id.list_menu_samples");
        sampleNameMap.put(R.id.draw_samples, "R.id.draw_samples");
        sampleNameMap.put(R.id.book_fragment_samples, "R.id.book_fragment_samples");
        sampleNameMap.put(R.id.view_page_samples, "R.id.view_page_samples");
        //sampleNameMap.put(, "");
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Logger.d(TAG, "mClickListener : " + sampleNameMap.get(v.getId()));

            switch(v.getId()) {
                case R.id.normal_samples:
                    launchSampleActivities();
                    break;
                case R.id.db_samples:
                    launchRoomDBSamples();
                    break;
                case R.id.observalbe_samples:
                    launchObservableSamples();
                    break;
                case R.id.list_menu_samples:
                    listMenusSamples();
                    break;
                case R.id.draw_samples:
                    drawSamplesSamples();
                    break;
                case R.id.book_fragment_samples:
                    fragmentSamples();
                    break;
                case R.id.view_page_samples:
                    viewPageSamples();
                    break;

                // Remember ===> findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
            }
        }
    };


    private void launchSampleActivities() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, LiveDataActivity.class));
                    //startActivity(new Intent(this, ObserverActivity.class));

//                    startActivity(new Intent(this, CallOtherActivity.class));
//                    startActivity(new Intent(this, HashMapActivity.class));
//                    startActivity(new Intent(this, ViewActivity.class));
//                    startActivity(new Intent(this, HandlerActivity.class));
//                    startActivity(new Intent(this, LocationManagerActivity.class));
//                    startActivity(new Intent(this, TextViewActivity.class));
//                    startActivity(new Intent(this, AudioActivity.class));
//                    startActivity(new Intent(this, DialogActivity.class));
//                    startActivity(new Intent(this, Dialog2Activity.class));
//                    startActivity(new Intent(this, Dialog3Activity.class));
//                    startActivity(new Intent(this, Dialog4Activity.class));
//                    startActivity(new Intent(this, Dialog5Activity.class));
//                    startActivity(new Intent(this, Test01Activity.class));
//                    startActivity(new Intent(this, Test02_MyViewActivity.class));
//                    startActivity(new Intent(this, SaveState02Activity.class));
//                    startActivity(new Intent(this, SaveState03Activity.class));
//                    startActivity(new Intent(this, DialogTest01Activity.class));
//                    startActivity(new Intent(this, DialogTest02Activity.class));
//                    startActivity(new Intent(this, DialogTest03Activity.class));
//                    startActivity(new Intent(this, DialogTest04Activity.class));
//                    startActivity(new Intent(this, DataBindingTest01Activity.class));
//                    startActivity(new Intent(this, NameActivityForViewModel.class));
                    //finish();
                });
    }


    private void launchRoomDBSamples() {
        Observable.just("Room DB")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
//                    startActivity(new Intent(this, UserActivity.class));
                    //finish();
                });
    }

    private void launchObservableSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    if (true) {
//                        startActivity(new Intent(this, RecyclerViewActivity.class));
                    } else {
//                        startActivity(new Intent(this, ReflectionActivity.class));
                    }
//                    startActivity(new Intent(this, ObserverMainActivity.class));

//                    startActivity(new Intent(this, LiveDataActivity.class));
//                    startActivity(new Intent(this, ObserverActivity.class));
                    //finish();
                });
    }

    private void listMenusSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
//                    startActivity(new Intent(this, ListView3Activity.class));
//                    startActivity(new Intent(this, RecyclerViewActivity.class));
//                    startActivity(new Intent(this, ListViewActivity.class));
//                    startActivity(new Intent(this, ListView2Activity.class));
//                    startActivity(new Intent(this, ListView02CustomActivity.class));
//                    startActivity(new Intent(this, ListView01Activity.class));

                    //finish();
                });
    }

    private void drawSamplesSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
//                    startActivity(new Intent(this, DrawActivity.class));
//                    startActivity(new Intent(this, ImageActivity.class));
//                    startActivity(new Intent(this, VideoActivity.class));
//                    startActivity(new Intent(this, DrawTestActivity.class));
//                    startActivity(new Intent(this, ReflectionActivity.class));

                    //finish();
                });
    }

    private void fragmentSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, FragmentLayout.class));
                    //finish();
                });
    }

    private void viewPageSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, ViewPagerActivity.class));
                    //finish();
                });
    }
    
}
