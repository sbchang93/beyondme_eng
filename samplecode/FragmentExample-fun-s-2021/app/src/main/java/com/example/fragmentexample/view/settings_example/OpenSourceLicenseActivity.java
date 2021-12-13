package com.example.fragmentexample.view.settings_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.commonActivity.BaseUIActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OpenSourceLicenseActivity extends BaseUIActivity {
    private static final String TAG = "OpenSourceLicenseActivity";

    TextView tvOpenSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source_license);

        setTitle("Open Source Licenses");

        // ActionBar's Back Press key
        // Inherited from BaseUIActivity class    <===  Supporting ActionBar's Back Press key.

        tvOpenSource = findViewById(R.id.open_source_text);

        AssetManager assetManager = getResources().getAssets();
        String text = "";

        try (InputStream is = assetManager.open("open_source_license_notice.txt")) {
            text = convertInputStreamToString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(text.length() != 0) {
            tvOpenSource.setText(text);
        }
    }

    protected String convertInputStreamToString(InputStream inputStream) {
        String line = "";
        String result = "";
        StringBuffer resultbuffer = new StringBuffer(result);
        try {
            BufferedReader temp_BufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = temp_BufferedReader.readLine()) != null) {
                resultbuffer.append(line);
                resultbuffer.append('\n');
            }
            result = resultbuffer.toString();
        } catch (Exception e) {
            Log.e(TAG, "convertInputStreamToString::Exception:" + e.toString());
        }

        return result;
    }
}