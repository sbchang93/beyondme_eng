package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fragmentexample.R;

// Reference Homepage Link : https://recipes4dev.tistory.com/m/148

public class ViewPager11Activity extends AppCompatActivity {

    private ViewPager viewPager ;
    private PagerAdapterTextView11 pagerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager11);

        viewPager = (ViewPager) findViewById(R.id.view_pager) ;
        pagerAdapter = new PagerAdapterTextView11(this) ;
        viewPager.setAdapter(pagerAdapter) ;
    }
}
