package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.toronto.mystudyapp.R;

public class TextViewActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
    }
}
