package com.example.fragmentexample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fragmentexample.R;

//Reference Homepage Link : https://recipes4dev.tistory.com/m/148

public class PagerAdapterTextView11 extends PagerAdapter {

    private Context mContext = null ; // LayoutInflater 서비스 사용을 위한 Context 참조 저장.

    public PagerAdapterTextView11() {
    }

    public PagerAdapterTextView11(Context context) { // Context를 전달받아 mContext에 저장하는 생성자 추가.
        mContext = context ;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null ;
        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.page_item11, container, false);

            TextView textView = (TextView) view.findViewById(R.id.tv_title) ;
            textView.setText("TEXT " + position) ;
        }
        container.addView(view); // 뷰페이저에 추가.
        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object); // 뷰페이저에서 삭제.
    }

    @Override
    public int getCount() {
        return 15; // 전체 페이지 수는 15개로 고정.
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}







