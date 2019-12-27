package com.example.fragmentexample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fragmentexample.R;

public class PagerAdapterImage12  extends PagerAdapter {

    private Context mContext = null ;

    public PagerAdapterImage12() {
        // TODO Auto-generated constructor stub
    }

    public PagerAdapterImage12(Context context) { // Context를 전달받아 mContext에 저장하는 생성자 추가.
        mContext = context ;
    }
    private int[] mImages = new int[] {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h
    };

    @Override
    public int getCount() {
        return 10; //mImages.length*20;  // Show many images in ViewPager
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ConstraintLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        Context context = mContext; //getActivity();
//        ImageView imageView = new ImageView(context);
//        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
//        imageView.setPadding(padding, padding, padding, padding);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        imageView.setImageResource(mImages[position%mImages.length]);
//        ((ViewPager) container).addView(imageView, 0);
//        return imageView;

        View view = null ;
        if (mContext != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.page_item12, container, false);

            TextView textView = (TextView) view.findViewById(R.id.tv_name) ;
            textView.setText("TEXT " + position) ;
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_name) ;
            imageView.setImageResource(mImages[position%mImages.length]);
        }
        container.addView(view); // 뷰페이저에 추가.
        return view ;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ConstraintLayout) object);
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }
}
