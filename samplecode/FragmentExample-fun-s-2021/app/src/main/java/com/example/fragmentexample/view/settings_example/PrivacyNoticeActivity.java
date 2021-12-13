package com.example.fragmentexample.view.settings_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.commonActivity.BaseUIActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrivacyNoticeActivity  extends BaseUIActivity {
    private final static String TAG = PrivacyNoticeActivity.class.getSimpleName();

    LinearLayout privacyNoticeContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_notice);

        setTitle("Privacy Noitce");

        // ActionBar's Back Press key
        // Inherited from BaseUIActivity class    <===  Supporting ActionBar's Back Press key.

        privacyNoticeContentLayout = findViewById(R.id.privacy_notice_content_layout);

        String fileName = getFileName("Test_page_01.html");
        Log.i(TAG, "File Name : " + fileName);

        View webViewLayout = getLayoutInflater().inflate(R.layout.privacy_notice_webview, null);
        WebView webView = webViewLayout.findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/" + fileName);

        privacyNoticeContentLayout.addView(webViewLayout);
    }


    private String getFileName(String fileName) {

        List<String> assetFileList = new ArrayList();
        try {
            AssetManager assetManager = getApplicationContext().getAssets();
            assetFileList = Arrays.asList(assetManager.list(""));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(assetFileList.contains(fileName)) {
            return fileName;
        }

        return "Test_page_02.html";
    }
}