package com.example.swipeup.view.android_hero_samples.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

//Reference Home URL : https://github.com/GavinAndre/AndroidHeroSamples
//Reference Home URL : https://programmer.help/blogs/android-scroll-analysis-3-the-ultimate-way-to-slide-viewdraghelper.html

public class DragViewGroup extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;
    private int mWidth;

    public DragViewGroup(Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragViewGroup(Context context,
                         AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    private ViewDragHelper.Callback callback =
            new ViewDragHelper.Callback() {

                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    return mMainView == child;
                }

                @Override
                public void onViewCaptured(View capturedChild,
                                           int activePointerId) {
                    super.onViewCaptured(capturedChild, activePointerId);
                }

                @Override
                public void onViewDragStateChanged(int state) {
                    super.onViewDragStateChanged(state);
                }

                @Override
                public void onViewPositionChanged(View changedView,
                                                  int left, int top, int dx, int dy) {
                    super.onViewPositionChanged(changedView, left, top, dx, dy);
                }

                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    return 0;
                }

                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    return left;
                }

                @Override
                public void onViewReleased(View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    if (mMainView.getLeft() < 300) {
                        mViewDragHelper.smoothSlideViewTo(mMainView, -200, 0);
                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                    } else {
                        mViewDragHelper.smoothSlideViewTo(mMainView, 600, 0);
                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                    }
                }

//                @Override
//                public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                    super.onViewReleased(releasedChild, xvel, yvel);
//                    if (mMainView.getLeft() < 500) {
//                        mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
//                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
//                    } else {
//                        mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
//                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
//                    }
//                }
            };

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}