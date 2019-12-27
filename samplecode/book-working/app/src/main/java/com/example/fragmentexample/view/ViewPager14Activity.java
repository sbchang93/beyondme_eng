package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fragmentexample.R;


// Reference Homepage Link (Circular ViewPager): https://codeday.me/ko/qa/20190517/568589.html
// Reference Homepage Link (ViewPager Indicator) : https://black-jin0427.tistory.com/95

public class ViewPager14Activity extends AppCompatActivity {

    private ViewPager viewPager ;
    private PagerAdapterTextView14 pagerAdapter ;
    ViewPagerCircleIndicator14 indicatorView;
    private int itemListSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager14);

        viewPager = (ViewPager) findViewById(R.id.view_pager) ;
        indicatorView = findViewById(R.id.indicator);
        indicatorView.createDotPanel(itemListSize, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, 0);

        pagerAdapter = new PagerAdapterTextView14(this, itemListSize) ;
        viewPager.setOnPageChangeListener(new ViewPagerCircularHandler14(viewPager, indicatorView));
        viewPager.setAdapter(pagerAdapter) ;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrollStateChanged(final int state) {
            }
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(final int position) {
                indicatorView.selectDot(position);
            }
        });

    }
}