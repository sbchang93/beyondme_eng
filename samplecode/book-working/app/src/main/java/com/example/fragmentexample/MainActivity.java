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
import com.example.fragmentexample.view.RecyclerOthers01Activity;
import com.example.fragmentexample.view.RecyclerServie01Activity;
import com.example.fragmentexample.view.RecyclerView71Activity;
import com.example.fragmentexample.view.RecyclerView72Activity;
import com.example.fragmentexample.view.RecyclerView81Activity;
import com.example.fragmentexample.view.ViewPager31Activity;
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
        findViewById(R.id.book_samples).setOnClickListener(mClickListener);
        findViewById(R.id.normal_samples).setOnClickListener(mClickListener);
        findViewById(R.id.db_samples).setOnClickListener(mClickListener);
        findViewById(R.id.observalbe_samples).setOnClickListener(mClickListener);
        findViewById(R.id.list_menu_samples).setOnClickListener(mClickListener);
        findViewById(R.id.draw_samples).setOnClickListener(mClickListener);
        findViewById(R.id.book_fragment_samples).setOnClickListener(mClickListener);
        findViewById(R.id.view_page_samples).setOnClickListener(mClickListener);
        findViewById(R.id.layout_view_samples).setOnClickListener(mClickListener);
        findViewById(R.id.service_samples).setOnClickListener(mClickListener);
        findViewById(R.id.others_samples).setOnClickListener(mClickListener);



        // ------------------------------------------------------------------------------------------------
        // For Debugging Message
        sampleNameMap.put(R.id.book_samples, "R.id.normal_samples");
        sampleNameMap.put(R.id.normal_samples, "R.id.normal_samples");
        sampleNameMap.put(R.id.db_samples, "R.id.db_samples");
        sampleNameMap.put(R.id.observalbe_samples, "R.id.observalbe_samples");
        sampleNameMap.put(R.id.list_menu_samples, "R.id.list_menu_samples");
        sampleNameMap.put(R.id.draw_samples, "R.id.draw_samples");
        sampleNameMap.put(R.id.book_fragment_samples, "R.id.book_fragment_samples");
        sampleNameMap.put(R.id.view_page_samples, "R.id.view_page_samples");
        sampleNameMap.put(R.id.layout_view_samples, "R.id.layout_view_samples");
        sampleNameMap.put(R.id.service_samples, "R.id.service_samples");
        sampleNameMap.put(R.id.others_samples, "R.id.others_samples");


        //sampleNameMap.put(, "");
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Logger.d(TAG, "mClickListener : " + sampleNameMap.get(v.getId()));

            switch(v.getId()) {
                case R.id.book_samples:
                    launchBookActivities();
                    break;
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
                case R.id.layout_view_samples:
                    layoutAndViewSamples();
                    break;
                case R.id.service_samples:
                    serviceSamples();
                    break;
                case R.id.others_samples:
                    othersSamples();
                    break;

                // Remember ===> findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
            }
        }
    };


    private void launchBookActivities() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, FragmentLayout.class));
                    startActivity(new Intent(this, RecyclerView71Activity.class));
                });
    }

    private void fragmentSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, FragmentLayout.class));
                    startActivity(new Intent(this, RecyclerView71Activity.class));
                });
    }

    private void viewPageSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, FragmentLayout.class));
                    startActivity(new Intent(this, RecyclerView72Activity.class));
                });
    }

    private void layoutAndViewSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, ViewPagerActivity.class));
                    //startActivity(new Intent(this, ViewPager31Activity.class));
                    startActivity(new Intent(this, RecyclerView81Activity.class));

                });
    }

    private void serviceSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, RecyclerServie01Activity.class));
                });
    }

    private void othersSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, RecyclerOthers01Activity.class));
                });
    }


    private void launchSampleActivities() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                });
    }


    private void launchRoomDBSamples() {
        Observable.just("Room DB")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                });
    }

    private void launchObservableSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                });
    }

    private void listMenusSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {

                });
    }

    private void drawSamplesSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                });
    }



    
}


/*
* ImageView 관련 설명
* https://recipes4dev.tistory.com/136
*
* Android Code 샘플들 보여줌
* https://www.codota.com/code/java/methods/android.view.View/setPadding
*
*
*
 */