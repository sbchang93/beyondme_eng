package com.example.fragmentexample.view;

import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

// Reference Homepage Link : https://codeday.me/ko/qa/20190517/568589.html

public class ViewPagerCircularHandler14 implements ViewPager.OnPageChangeListener {
    private ViewPager   mViewPager;
    private int         mCurrentPosition;
    private int         mScrollState;
    ViewPagerCircleIndicator14 mIndicatorView;

//    public ViewPagerCircularHandler14(final ViewPager viewPager) {
//        mViewPager = viewPager;
//        mIndicatorView = null;
//    }

    public ViewPagerCircularHandler14(final ViewPager viewPager, ViewPagerCircleIndicator14 indicatorView) {
        mViewPager = viewPager;
        mIndicatorView = indicatorView;
    }

    @Override
    public void onPageSelected(final int position) {
        mCurrentPosition = position;

        // Set ViewPager Indicator
        if(mIndicatorView != null)
            mIndicatorView.selectDot(position);
            //mIndicatorView.setProgress(mCurrentPosition, 1 /*progress*/);
    }

    @Override
    public void onPageScrollStateChanged(final int state) {
        handleScrollState(state);
        mScrollState = state;
    }

    private void handleScrollState(final int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            setNextItemIfNeeded();
        }
    }

    private void setNextItemIfNeeded() {
        if (!isScrollStateSettling()) {
            handleSetNextItem();
        }
    }

    private boolean isScrollStateSettling() {
        return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
    }

    private void handleSetNextItem() {
        final int lastPosition = mViewPager.getAdapter().getCount() - 1;
        if(mCurrentPosition == 0) {
            mViewPager.setCurrentItem(lastPosition, false);
        } else if(mCurrentPosition == lastPosition) {
            mViewPager.setCurrentItem(0, false);
        }
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
    }
}
