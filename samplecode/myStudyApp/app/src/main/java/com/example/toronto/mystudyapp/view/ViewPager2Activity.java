package com.example.toronto.mystudyapp.view;


import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

// Reference Link Page : https://itpangpang.xyz/286?category=587498

public class ViewPager2Activity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    ViewPager vp;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        vp = (ViewPager)findViewById(R.id.vp);
        ll = (LinearLayout)findViewById(R.id.ll);

        TextView tab_first = (TextView)findViewById(R.id.tab_first);
        TextView tab_second = (TextView)findViewById(R.id.tab_second);
        TextView tab_third = (TextView)findViewById(R.id.tab_third);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        tab_first.setOnClickListener(movePageListener);
        tab_first.setTag(0);
        tab_second.setOnClickListener(movePageListener);
        tab_second.setTag(1);
        tab_third.setOnClickListener(movePageListener);
        tab_third.setTag(2);

        tab_first.setSelected(true);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while(i<3) {
                    if(position==i) {
                        ll.findViewWithTag(i).setSelected(true);
                    } else {
                        ll.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();

            int i = 0;
            while(i<3) {
                if(tag==i) {
                    ll.findViewWithTag(i).setSelected(true);
                } else {
                    ll.findViewWithTag(i).setSelected(false);
                }
                i++;
            }

            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new ViewPagerFirstFragment();
                case 1:
                    return new ViewPagerSecondFragment();
                case 2:
                    return new ViewPagerThirdFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }
}


