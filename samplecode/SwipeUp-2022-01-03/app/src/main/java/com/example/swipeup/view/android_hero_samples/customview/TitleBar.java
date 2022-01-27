package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.swipeup.R;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class TitleBar extends RelativeLayout {

    private ImageButton mLeftButton, mRightButton;
    private TextView mTitleView;
    private LayoutParams mLeftParams, mTitleParams, mRightParams;
    private int mLeftTextColor;
    private Drawable mLeftImageDrawable;
    private Drawable mRightImageDrawable;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    private titleBarLeftClickListener mLeftClickListener;
    private titleBarRightClickListener mRightClickListener;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mLeftTextColor = ta.getColor(R.styleable.TitleBar_leftTextColor, 0);
        mLeftImageDrawable = ta.getDrawable(R.styleable.TitleBar_leftImageDrawable);
        mLeftText = ta.getString(R.styleable.TitleBar_leftText);
        mRightTextColor = ta.getColor(R.styleable.TitleBar_rightTextColor, 0);
        mRightImageDrawable = ta.getDrawable(R.styleable.TitleBar_rightImageDrawable);
        mRightText = ta.getString(R.styleable.TitleBar_rightText);
        mTitleTextSize = ta.getDimension(R.styleable.TitleBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TitleBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TitleBar_title);
        ta.recycle();
    }

    private void initView(Context context) {
        mLeftButton = new ImageButton(context);
        mRightButton = new ImageButton(context);
        mTitleView = new TextView(context);

        //        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setImageDrawable(mLeftImageDrawable);
        mLeftButton.setBackgroundResource(android.R.color.transparent);
        //        mLeftButton.setText(mLeftText);

        //        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setImageDrawable(mRightImageDrawable);
        mRightButton.setBackgroundResource(android.R.color.transparent);
        //        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);


        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_START, TRUE);  // 왼쪽에 위치시킴
        } else {
            mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        }
        addView(mLeftButton, mLeftParams);


        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mRightParams.addRule(RelativeLayout.ALIGN_PARENT_END, TRUE);  // 오른쪽에 위치시킴
        } else {
            mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        }
        addView(mRightButton, mRightParams);


        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);


        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftClickListener != null) {
                    mLeftClickListener.leftClick();
                }
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightClickListener != null) {
                    mRightClickListener.rightClick();
                }
            }
        });
    }

    public void setTitle(String mTitle) {
        if (mTitleView != null) {
            this.mTitle = mTitle;
            mTitleView.setText(mTitle);
        }
    }

    public void setLeftButtonVisible(boolean flag) {
        if (mLeftButton != null) {
            if (flag) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mLeftButton.setVisibility(View.GONE);
            }
        }
    }

    public void setRightButtonVisible(boolean flag) {
        if (mRightButton != null) {
            if (flag) {
                mRightButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    public void setOnTitleBarLeftClickListener(titleBarLeftClickListener mLeftClickListener) {
        this.mLeftClickListener = mLeftClickListener;
    }

    public void setOnTitleBarRightClickListener(titleBarRightClickListener mRightClickListener) {
        this.mRightClickListener = mRightClickListener;
    }

    public interface titleBarLeftClickListener {
        void leftClick();
    }

    public interface titleBarRightClickListener {
        void rightClick();
    }
}