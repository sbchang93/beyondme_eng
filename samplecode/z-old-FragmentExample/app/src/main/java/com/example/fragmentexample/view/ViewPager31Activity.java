package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.fragmentexample.R;
import com.example.fragmentexample.util.Logger;
import com.rd.PageIndicatorView;

import java.util.ArrayList;


// Reference Link Page : https://itpangpang.xyz/m/284

public class ViewPager31Activity extends AppCompatActivity
{
    private final String TAG = this.getClass().getSimpleName();

    ViewPagerCustom9 vpc9;
    DemoCustom9Adapter adapter;
    PageIndicatorView indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Logger.d(TAG, "onCreate ... ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager31);

        vpc9 = (ViewPagerCustom9)findViewById(R.id.vp);
        indicatorView = findViewById(R.id.indicator);

        adapter = new DemoCustom9Adapter(this, createDummyItems(), true);
        vpc9.setAdapter(adapter);

        //vpc9.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vpc9.setCurrentItem(0);

        //Custom bind indicator
        indicatorView.setCount(vpc9.getIndicatorCount());
        vpc9.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
//                indicatorView.setSelection(newIndicatorPosition);
            }
        });

//        // Manage the click of Buttons
//        Button btn_first = (Button)findViewById(R.id.btn_first);
//        Button btn_second = (Button)findViewById(R.id.btn_second);
//        Button btn_third = (Button)findViewById(R.id.btn_third);
//
//        btn_first.setOnClickListener(movePageListener);
//        btn_first.setTag(0);
//        btn_second.setOnClickListener(movePageListener);
//        btn_second.setTag(1);
//        btn_third.setOnClickListener(movePageListener);
//        btn_third.setTag(2);
    }


    private ArrayList<Fragment> createDummyItems () {
        ArrayList<Fragment> items = new ArrayList<>();
        items.add(0, new ViewPagerFirstFragment());
        items.add(1, new ViewPagerSecondFragment());
        items.add(2, new ViewPagerThirdFragment());
        return items;
    }


//    View.OnClickListener movePageListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            int tag = (int) v.getTag();
//            vpc9.setCurrentItem(tag);
//        }
//    };
//
//    private class pagerAdapter extends FragmentStatePagerAdapter
//    {
//        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
//            super(fm);
//        }
//        @Override
//        public android.support.v4.app.Fragment getItem(int position) {
//            switch(position) {
//                case 0:
//                    return new ViewPagerFirstFragment();
//                case 1:
//                    return new ViewPagerSecondFragment();
//                case 2:
//                    return new ViewPagerThirdFragment();
//                default:
//                    return null;
//            }
//        }
//        @Override
//        public int getCount()
//        {
//            return 3;
//        }
//
//
//    }


//    private ArrayList<Integer> createDummyItems () {
//        ArrayList<Integer> items = new ArrayList<>();
//        items.add(0, 1);
//        items.add(1, 2);
//        items.add(2, 3);
//        items.add(3, 4);
//        items.add(4, 5);
//        items.add(5, 6);
//        items.add(6, 0);
//        return items;
//    }
}