package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.happybom.tabscreen.R;

public class TabFragment2 extends Fragment {
    private static final String TAG = "TabFragment2";

    public TabFragment2 newInstance(int index) {
        TabFragment2 f = new TabFragment2();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment2, container, false);
        return view;
    }

}
