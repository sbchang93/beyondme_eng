package com.example.toronto.mystudyapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

public class ViewPagerSecondFragment  extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public ViewPagerSecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_view_pager_second, container, false);
        return layout;
    }
}
