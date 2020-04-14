package com.happybom.commonlibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Reference Homepage for common library creation
// https://kokohapps.tistory.com/entry/Android-Module-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EB%B9%84%EC%8A%B7%ED%95%9C-%EC%95%B1-%EC%97%AC%EB%9F%AC%EA%B0%9C-%EC%B0%8D%EC%96%B4%EB%82%B4%EA%B8%B0

public class BaseMainActivity extends AppCompatActivity {
    private static final String TAG = "BaseMainActivity";
    protected Button button;
    protected TextView textView;

    @Override
    //protected void onCreate(Bundle savedInstanceState)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);

        this.textView = (TextView) findViewById(R.id.textView);

        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseMainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
