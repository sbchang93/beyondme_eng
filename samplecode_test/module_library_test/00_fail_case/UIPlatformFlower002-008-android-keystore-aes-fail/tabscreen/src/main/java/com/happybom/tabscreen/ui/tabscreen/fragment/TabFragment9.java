package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.happybom.tabscreen.R;

public class TabFragment9 extends Fragment {
    private static final String TAG = "TabFragment9";

    public TabFragment9 newInstance(int index) {
        TabFragment9 f = new TabFragment9();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment9, container, false);
        return view;
    }

}
