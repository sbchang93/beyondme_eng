package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.happybom.tabscreen.R;

public class TabFragment6 extends Fragment {
    private static final String TAG = "TabFragment6";

    public TabFragment6 newInstance(int index) {
        TabFragment6 f = new TabFragment6();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment6, container, false);
        return view;
    }

}
