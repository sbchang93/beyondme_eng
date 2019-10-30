package com.example.toronto.mystudyapp.view;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

public class ViewPagerCustomActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    ViewPagerCustom vpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_custom);

        vpc = (ViewPagerCustom)findViewById(R.id.vp);
        vpc.setAdapter(new ViewPagerCustomActivity.pagerAdapter(getSupportFragmentManager()));
        vpc.setCurrentItem(0);
        Logger.d(TAG, "onCreate: ViewPager height :" + vpc.getHeight()); // Height is not correct here. After onWindowFocusChanged(), it will be correct.
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
//        vp = (ViewPagerCustom)findViewById(R.id.vp);
//        vp.setAdapter(new ViewPagerCustomActivity.pagerAdapter(getSupportFragmentManager()));
//        vp.setCurrentItem(0);
        Logger.d(TAG, "onWindowFocusChanged: ViewPager height :" + vpc.getHeight());
    }


    @Override
    protected void onPause() {
        super.onPause();
        Logger.d(TAG, "nPause() - ViewPager height :" + vpc.getHeight());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume() - ViewPager height :" + vpc.getHeight()); // Height is not correct here. After onWindowFocusChanged(), it will be correct.
    }

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new ViewPagerFirstFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return 1;
        }
    }
}
