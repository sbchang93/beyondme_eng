package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.OsVersionHelper;

import java.lang.reflect.Field;

public class ShowTextActivity extends AppCompatActivity {
    private static final String TAG = "ShowTextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
    }

    @Override
    protected void onResume() {
        super.onResume();

        StringBuilder builder = new StringBuilder();
        builder.append("android : ").append(Build.VERSION.RELEASE);

        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(" : ").append(fieldName).append(" : ");
                builder.append("sdk=").append(fieldValue);
            }
        }

        Log.d(TAG, "OS: " + builder.toString());
        // Output : D/ShowTextActivity: OS: android : 11 : R : sdk=30
        // https://namu.wiki/w/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C(%EC%9A%B4%EC%98%81%EC%B2%B4%EC%A0%9C)/11
        // android 11  (API Level 30)
        // R : R os
        // sdk : 30


        String osVersionName = OsVersionHelper.getOsVersionName();
        Log.d(TAG, "OS: " + builder.toString());
    }

}