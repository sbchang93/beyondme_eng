package com.example.fragmentexample.view.settings_example;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;

public class CustomCardPreference extends Preference {
    private static final String TAG = "CustomCardPreference";
    private Context mContext;

    public CustomCardPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    public CustomCardPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public CustomCardPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // This is called.
    }

    public CustomCardPreference(Context context) {
        super(context);
        mContext = context;
    }

}

