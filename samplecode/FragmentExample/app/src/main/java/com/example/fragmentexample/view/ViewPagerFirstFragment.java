package com.example.fragmentexample.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.fragmentexample.R;
import com.example.fragmentexample.util.Logger;

public class ViewPagerFirstFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public ViewPagerFirstFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Logger.d(TAG, "onCreate ... ");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_view_pager_first, container, false);
        return layout;
    }
}