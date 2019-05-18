package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.common.Shape;
import com.example.toronto.mystudyapp.util.Logger;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class ObserverMainActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_main);

        findViewById(R.id.btn01_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn02_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn03_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn04_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn05_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn06_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn07_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn08_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn09_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn10_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn11_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn12_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn13_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn14_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn15_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn16_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn17_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn18_Obsrv).setOnClickListener(mClickListener);
        findViewById(R.id.btn19_Obsrv).setOnClickListener(mClickListener);
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn01_Obsrv:
                    btn01_function();
                    break;
                case R.id.btn02_Obsrv:
                    btn02_function();
                    break;
                case R.id.btn03_Obsrv:
                    btn03_function();
                    break;
                case R.id.btn04_Obsrv:
                    btn04_function();
                    break;
                case R.id.btn05_Obsrv:
                    btn05_function();
                    break;
                case R.id.btn06_Obsrv:
                    btn06_function();
                    break;
                case R.id.btn07_Obsrv:
                    btn07_function();
                    break;
                case R.id.btn08_Obsrv:
                    btn08_function();
                    break;
                case R.id.btn09_Obsrv:
                    btn09_function();
                    break;
                case R.id.btn10_Obsrv:
                    btn10_function();
                    break;
                case R.id.btn11_Obsrv:
                    btn11_function();
                    break;
                case R.id.btn12_Obsrv:
                    btn12_function();
                    break;
                // Remember ===> findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
            }
        }
    };

//Observable.just("bnt01")
//        .delay(1, TimeUnit.MILLISECONDS)
//        .subscribe(s -> {
//            startActivity(new Intent(this, UserActivity.class));
//            finish();
//});

//    Logger.d(TAG,s);

    private void btn01_function() {

//     public abstract class Observable<T> implements ObservableSource<T> {
//        ...
//        public final Disposable subscribe(Consumer<? super T> onNext) {
//            return subscribe(onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION, Functions.emptyConsumer());
//        }

        Observable.just("Hello", "RxJava 2!")
                .subscribe(s -> {
                    Logger.d(TAG,s);});  // s -> String

        Observable<Integer> a = Observable.just(1);
        Observable<Integer> b = Observable.just(100);
        Observable.combineLatest(a, b, (x,y) -> x + y)
                .subscribe(s -> {
                    Logger.d(TAG,""+s);}); // s -> Integer

        String msg = "0x1234567890abcdefghij1234567890abcdefghij1234567890abcdefghij1234567890abcdefghij1234567890abcdefghij1234567890abcdefghij1234567890abcdefghij";
        String result;
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("0x[0-9a-zA-Z]{60}", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(msg);
        result = matcher.replaceAll("0x************************************************************");
        pattern = Pattern.compile("0x[0-9a-zA-Z]{36}", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(result);
        result = matcher.replaceAll("0x************************************");
        Logger.d(TAG,result);
    }
    private void btn02_function() {
    }
    private void btn03_function() {
        //String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T", "7-O", "8-O"};
        String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(Shape::getShape);
        source.subscribe(obj1 -> {
            obj1.subscribe(
                    val -> {Logger.d(TAG,"Group:" + obj1.getKey() + "\t Value:" + ""+val);}
            );
        });

        String[] objs_2 = {"6", "4", "2-T", "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source_2 =
                Observable.fromArray(objs_2).groupBy(Shape::getShape);
        source_2.subscribe(obj_2 -> {
            obj_2.filter(val -> obj_2.getKey().equals(Shape.BALL))
                    .subscribe(
                            val -> {Logger.d(TAG,"Group:" + obj_2.getKey() + "\t Value:" + ""+val);}
            );
        });
    }
    private void btn04_function() {
    }
    private void btn05_function() {
    }
    private void btn06_function() {
    }
    private void btn07_function() {
    }
    private void btn08_function() {
    }
    private void btn09_function() {
    }
    private void btn10_function() {
    }
    private void btn11_function() {
    }
    private void btn12_function() {
    }

}
