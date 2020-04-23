package com.happybom.tabscreen.ui.tabscreen.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment1;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment2;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment3;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}