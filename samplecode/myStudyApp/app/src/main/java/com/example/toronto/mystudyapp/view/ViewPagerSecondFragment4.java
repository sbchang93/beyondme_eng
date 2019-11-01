package com.example.toronto.mystudyapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

public class ViewPagerSecondFragment4  extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    TextView tv;
    TextView tv2;
    int i = 0;
    private final static int price = 1000;

    public ViewPagerSecondFragment4() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_view_pager_second4, container, false);
        tv = (TextView)layout.findViewById(R.id.tv);
        tv2 = (TextView)layout.findViewById(R.id.tv2);

        Button btn1 = (Button)layout.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ((ViewPager4Activity)getActivity()).tempCount++;
                //((ViewPager4Activity)getActivity()).vp.setCurrentItem(0);

                int count = ((ViewPager4Activity)getActivity()).tempCount;
                tv.setText("사과 "+ count +"개"); //사과 갯수 확인 TextView
                tv2.setText(count*price + " 원"); //결제예상금액 TextView
            }
        });

        Button btn2 = (Button)layout.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ((ViewPager4Activity)getActivity()).tempCount--;
                //((ViewPager4Activity)getActivity()).vp.setCurrentItem(0);

                int count = ((ViewPager4Activity)getActivity()).tempCount;
                tv.setText("사과 "+count+"개"); //사과 갯수 확인 TextView
                tv2.setText(count*price+"원"); //결제예상금액 TextView
            }
        });

        return layout;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser) {
            int count = ((ViewPager4Activity)getActivity()).tempCount;
            tv.setText("사과 "+count+"개"); //사과 갯수 확인 TextView
            tv2.setText(count*price+"원"); //결제예상금액 TextView
        } else {
            try {
                Logger.d(TAG, "LifeCycle invisible(second)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}