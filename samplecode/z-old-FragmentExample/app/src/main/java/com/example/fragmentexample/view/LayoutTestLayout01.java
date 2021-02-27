package com.example.fragmentexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.fragmentexample.R;

// Reference Homepage Link (Custom LinearLayout) : https://codeday.me/ko/qa/20190518/576535.html
public class LayoutTestLayout01 extends LinearLayout {
    public LayoutTestLayout01(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_testlayout01, this, true);
    }
}