package com.happybom.blueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;

import com.happybom.commonlibrary.AppManager;
import com.happybom.commonlibrary.BaseMainActivity;

// Reference Homepage for common library creation
// https://kokohapps.tistory.com/entry/Android-Module-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EB%B9%84%EC%8A%B7%ED%95%9C-%EC%95%B1-%EC%97%AC%EB%9F%AC%EA%B0%9C-%EC%B0%8D%EC%96%B4%EB%82%B4%EA%B8%B0
public class BlueMainActivity extends BaseMainActivity {
    private static final String TAG = "BlueMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        AppManager.appType = AppManager.AppType.BlueApp;
        AppManager.themeColor = ContextCompat.getColor(this, R.color.colorPrimary);

        this.button.setTextColor(AppManager.themeColor);
        this.button.setText("Blue Button");

        this.textView.setText("This is Blue Apk");
    }
}
