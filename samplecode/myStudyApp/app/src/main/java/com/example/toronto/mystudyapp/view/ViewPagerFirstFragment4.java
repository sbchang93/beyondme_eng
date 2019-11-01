package com.example.toronto.mystudyapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

public class ViewPagerFirstFragment4 extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    TextView tv = null;
    EditText et = null;
    int i = 0;

    public ViewPagerFirstFragment4() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_view_pager_first4, container, false);

        et = (EditText)layout.findViewById(R.id.et);

        Button btn = (Button)layout.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // check et != null && et.getText() != ""
                ((ViewPager4Activity)getActivity()).tempCount = Integer.parseInt(et.getText().toString());
                ((ViewPager4Activity)getActivity()).vp.setCurrentItem(1);
            }
        });
        return layout;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser) {
            if (et != null) {
                et.setText("" + ((ViewPager4Activity) getActivity()).tempCount);
            }
        } else {
            try {
                Logger.d(TAG, "LifeCycle invisible(first)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}