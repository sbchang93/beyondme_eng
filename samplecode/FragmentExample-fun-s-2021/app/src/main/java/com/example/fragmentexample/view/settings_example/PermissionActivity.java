package com.example.fragmentexample.view.settings_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.commonActivity.BaseUIActivity;

public class PermissionActivity  extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        setTitle("Permission");

        // ActionBar's Back Press key
        // Inherited from BaseUIActivity class    <===  Supporting ActionBar's Back Press key.

    }
}