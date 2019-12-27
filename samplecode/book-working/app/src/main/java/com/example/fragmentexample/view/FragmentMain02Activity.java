package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragmentexample.R;
import com.example.fragmentexample.util.Logger;

public class FragmentMain02Activity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Logger.d(TAG, "onCreate ... ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main02);

        vp = (ViewPager)findViewById(R.id.viewpage_fragment_main02);
        Button btn_first = (Button)findViewById(R.id.btn_first_fragment_main02);
        Button btn_second = (Button)findViewById(R.id.btn_second_fragment_main02);
        Button btn_third = (Button)findViewById(R.id.btn_third_fragment_main02);

        vp.setAdapter(new FragmentMain02Activity.pagerMain2Adapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        //btn_first.setOnClickListener(movePageListener);
        btn_first.setOnClickListener(v -> {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);});
        btn_first.setTag(0);

        btn_second.setOnClickListener(movePageListener);
        btn_second.setTag(1);
        btn_third.setOnClickListener(movePageListener);
        btn_third.setTag(2);
    }

    View.OnClickListener movePageListener
            =  v -> { int tag = (int) v.getTag();
                      vp.setCurrentItem(tag);};

//    View.OnClickListener movePageListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            int tag = (int) v.getTag();
//            vp.setCurrentItem(tag);
//        }
//    };


    private final static int TOTAL_PAGE_NUM = 4;

    private class pagerMain2Adapter extends FragmentStatePagerAdapter
    {
        public pagerMain2Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new ViewPagerFirstFragment();
                case 1:
                    return new ViewPagerSecondFragment();
                case 2:
                    return new ViewPagerThirdFragment();
                case 3:
                    return new FirstExampleFragment();

                // Change TOTAL_PAGE_NUM, when you add new Fragment in this Activity.
                // TOTAL_PAGE_NUM = 4
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return TOTAL_PAGE_NUM;
        }


    }


}