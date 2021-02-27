package com.example.fragmentexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

// Reference Homepage Link (ViewPager CircleIndicator) : https://black-jin0427.tistory.com/95
public class ViewPagerCircleIndicator14 extends LinearLayout {

    private Context mContext = null;

    private int mDefaultCircle = 0;
    private int mSelectCircle = 0;

    private ArrayList<ImageView> imageDot = new ArrayList<>();

    DisplayMetrics metrics = new DisplayMetrics();

    // 4.5dp 를 픽셀 단위로 바꿉니다.
    //private float temp = 0; //TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.5f, resources.displayMetrics);

    public ViewPagerCircleIndicator14(Context context) {
        super(context);
        mContext = context;
    }

    public ViewPagerCircleIndicator14(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 기본 점 생성
     * @param count 점의 갯수
     * @param defaultCircle 기본 점의 이미지
     * @param selectCircle 선택된 점의 이미지
     * @param position 선택된 점의 포지션
     */
    public void createDotPanel(int count, int defaultCircle, int selectCircle, int position) {

        this.removeAllViews();

        mDefaultCircle = defaultCircle;
        mSelectCircle = selectCircle;

        for (int i=0; i<count; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setPadding(4, 0, 4,0);
            imageDot.add(imageView);
            this.addView(imageDot.get(i));
        }

        //인덱스 선택
        selectDot(position);
    }

    /**
     * 선택된 점 표시
     * @param position
     */
    void selectDot(int position) {
        for (int i=0; i< imageDot.size(); i++) {
            if (i == position) {
                imageDot.get(i).setImageResource(mSelectCircle);
            } else {
                imageDot.get(i).setImageResource(mDefaultCircle);
            }
        }
    }
}
