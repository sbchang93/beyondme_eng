package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fragmentexample.R;
import com.rd.PageIndicatorView;

// Reference Homepage Link (Circular ViewPager): https://codeday.me/ko/qa/20190517/568589.html

public class ViewPager13Activity extends AppCompatActivity {

    private ViewPager viewPager ;
    private PagerAdapterTextView13 pagerAdapter ;
    PageIndicatorView indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager13);

        viewPager = (ViewPager) findViewById(R.id.view_pager) ;
        indicatorView = findViewById(R.id.indicator);

        pagerAdapter = new PagerAdapterTextView13(this) ;
        viewPager.setOnPageChangeListener(new ViewPagerCircularHandler13(viewPager, indicatorView));
        //android:overScrollMode="never"
        viewPager.setAdapter(pagerAdapter) ;

        int count = viewPager.getAdapter().getCount();
        indicatorView.setCount(count);
//        indicatorView.setSelectedColor(500);
//        indicatorView.setUnselectedColor(20000);

    }
}
