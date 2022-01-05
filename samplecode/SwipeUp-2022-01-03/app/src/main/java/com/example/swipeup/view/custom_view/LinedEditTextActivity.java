package com.example.swipeup.view.custom_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.swipeup.R;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

// Reference Home URL : https://www.charlezz.com/?p=1035

public class LinedEditTextActivity extends AppCompatActivity {
    private static final String TAG = "LinedEditTextActivity";

    PieChartView mPieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lined_edit_text);

        mPieChartView = findViewById(R.id.pie_chart_view);

        AtomicBoolean toggle = new AtomicBoolean();
        toggle.set(true);
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .repeat(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    mPieChartView.setShowText(toggle.get());
                    toggle.set(!toggle.get());
                }, t -> t.getMessage());
    }
}