package com.example.toronto.mystudyapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

public class ViewPagerThirdFragment3 extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    TextView tv;
    int i = 0;

    public ViewPagerThirdFragment3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_view_pager_third3, container, false);
        tv = (TextView)layout.findViewById(R.id.tv);
        Button btn = (Button)layout.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i++;
                tv.setText(""+i);
            }
        });
        return layout;
    }
}