package com.happybom.commonlibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    //protected void onCreate(Bundle savedInstanceState)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("세컨드 액티버티입니다.");

        Button button = (Button) findViewById(R.id.button);
        button.setText("닫기");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
