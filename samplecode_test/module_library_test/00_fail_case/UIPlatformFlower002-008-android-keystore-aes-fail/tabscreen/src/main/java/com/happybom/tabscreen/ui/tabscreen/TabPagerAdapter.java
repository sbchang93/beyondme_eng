package com.happybom.tabscreen.ui.tabscreen;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment7;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment8;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment9;
import com.happybom.tabscreen.ui.tabscreen.listview.ListViewFragment;
import com.happybom.tabscreen.ui.tabscreen.webview.WebViewFragment;
import com.happybom.tabscreen.ui.tabscreen.dictionary.DictionaryFragment;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment4;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment5;
import com.happybom.tabscreen.ui.tabscreen.fragment.TabFragment6;

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
                return new ListViewFragment();
            case 1:
                return new WebViewFragment();
            case 2:
                return new DictionaryFragment();
            case 3:
                return new TabFragment4();
            case 4:
                return new TabFragment5();
            case 5:
                return new TabFragment6();
            case 6:
                return new TabFragment7();
            case 7:
                return new TabFragment8();
            case 8:
                return new TabFragment9();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}