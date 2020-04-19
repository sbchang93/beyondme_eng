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
        Button button = (Button) findViewById(R.id.button);

        textView.setTextColor(AppManager.themeColor);
        button.setTextColor(AppManager.themeColor);

        if(AppManager.appType == AppManager.AppType.RedApp) {
            textView.setText("This is second Activity \nthat is run by Red Apk");
        } else if(AppManager.appType == AppManager.AppType.BlueApp) {
            textView.setText("This is second Activity \nthat is run by Blue Apk");
        }

        button.setText("Close");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
