package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fragmentexample.R;

// Reference Homepage Link : http://blog.naver.com/PostView.nhn?blogId=liange&logNo=200000070224&parentCategoryNo=&categoryNo=1&viewDate=&isShowPopularPosts=false&from=postView

public class ViewPager12Activity extends AppCompatActivity {

    private ViewPager viewPager ;
    private PagerAdapterImage12 pagerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager12);

        viewPager = (ViewPager) findViewById(R.id.view_pager) ;
        pagerAdapter = new PagerAdapterImage12(this) ;
        viewPager.setAdapter(pagerAdapter) ;

        viewPager.setOnPageChangeListener(new ViewPagerCircularHandler12(viewPager));

//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // TODO Auto-generated method stub
//            }
//            @Override
//            public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
//                // TODO Auto-generated method stub
//                if(position == 0)
//                    viewPager.setCurrentItem(20); // Not working correctly
//
//                if(position == 39)
//                    viewPager.setCurrentItem(20); // Not working correctly
//            }
//            @Override
//            public void onPageSelected(int position) {
//            // TODO Auto-generated method stub
//            }
//        });
    }
}
