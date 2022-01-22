package com.example.swipeup.view_utils.swipeuppanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import androidx.core.view.ViewCompat;

import com.example.swipeup.R;
import com.example.swipeup.slidinguppanel.ScrollableViewHelper;
import com.example.swipeup.slidinguppanel.SlidingUpPanelLayout;
import com.example.swipeup.slidinguppanel.ViewDragHelper;
import com.example.swipeup.slidinguppanel.canvassaveproxy.CanvasSaveProxy;
import com.example.swipeup.slidinguppanel.canvassaveproxy.CanvasSaveProxyFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel

public class SwipeUpPanelLayout extends ViewGroup {

    private static final String TAG = SwipeUpPanelLayout.class.getSimpleName();

    private static final int DEFAULT_PANEL_HEIGHT = 68; // dp;
    private static final float DEFAULT_ANCHOR_POINT = 1.0f; // In relative %
    private static SwipePanelState DEFAULT_SWIPE_STATE = SwipePanelState.COLLAPSED;
    private static final int DEFAULT_SHADOW_HEIGHT = 4; // dp;
    private static final int DEFAULT_FADE_COLOR = 0x99000000;
    private static final int DEFAULT_MIN_FLING_VELOCITY = 400; // dips per second
    private static final boolean DEFAULT_OVERLAY_FLAG = false;
    private static final boolean DEFAULT_CLIP_PANEL_FLAG = true;

    private static final int[] DEFAULT_ATTRS = new int[]{
            android.R.attr.gravity
    };

    public static final String SWIPING_STATE = "Swiping_state";
    private int mMinFlingVelocity = DEFAULT_MIN_FLING_VELOCITY;
    private int mCoveredFadeColor = DEFAULT_FADE_COLOR;
    private static final int DEFAULT_PARALLAX_OFFSET = 0;
    private final Paint mCoveredFadePaint = new Paint();
    private final Drawable mShadowDrawable;
    private int mPanelHeight = -1;
    private int mShadowHeight = -1;
    private int mParallaxOffset = -1;
    private boolean mIsSwipingUp;
    private boolean mOverlayContent = DEFAULT_OVERLAY_FLAG;
    private boolean mClipPanel = DEFAULT_CLIP_PANEL_FLAG;
    private View mDragView;
    private int mDragViewResId = -1;
    private View mScrollableView;
    private int mScrollableViewResId;
    private SwipeScrollableViewHelper mScrollableViewHelper = new SwipeScrollableViewHelper();
    private View mSwipeableView;
    private View mMainView;

    public enum SwipePanelState {
        EXPANDED,
        COLLAPSED,
        ANCHORED,
        HIDDEN,
        DRAGGING
    }

    private SwipePanelState mSwipeState = DEFAULT_SWIPE_STATE;
    private SwipePanelState mLastNotDraggingSwipeState = DEFAULT_SWIPE_STATE;
    private float mSwipeOffset;
    private int mSwipeRange;
    private float mAnchorPoint = 1.f;
    private boolean mIsUnableToDrag;
    private boolean mIsTouchEnabled;

    private float mPrevMotionX;
    private float mPrevMotionY;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsScrollableViewHandlingTouch = false;

    private final List<PanelSwipeListener> mPanelSwipeListeners = new CopyOnWriteArrayList<>();
    private View.OnClickListener mFadeOnClickListener;

    private final SwipeViewDragHelper mDragHelper;

    private int log_SwipeMoveEventCounts;

    private boolean mFirstLayout = true;

    private final Rect mTmpRect = new Rect();

    public interface PanelSwipeListener {
        public void onPanelSwipe(View panel, float swipeOffset);
        public void onSwipePanelStateChanged(View panel, SwipePanelState previousState, SwipePanelState newState);
    }

    public static class SimplePanelSwipeListener implements PanelSwipeListener {
        @Override
        public void onPanelSwipe(View panel, float swipeOffset) {
        }

        @Override
        public void onSwipePanelStateChanged(View panel, SwipePanelState previousState, SwipePanelState newState) {
        }
    }

    public SwipeUpPanelLayout(Context context) {
        this(context, null);
    }

    public SwipeUpPanelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeUpPanelLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) {
            mShadowDrawable = null;
            mDragHelper = null;
            return;
        }

        Interpolator scrollerInterpolator = null;
        if (attrs != null) {
            TypedArray defAttrs = context.obtainStyledAttributes(attrs, DEFAULT_ATTRS);

            if (defAttrs != null) {
                int gravity = defAttrs.getInt(0, Gravity.NO_GRAVITY);
                setGravity(gravity);
                defAttrs.recycle();
            }

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwipeUpPanelLayout);

            if (ta != null) {
                mPanelHeight = ta.getDimensionPixelSize(R.styleable.SwipeUpPanelLayout_swipePanelHeight, -1);
                mShadowHeight = ta.getDimensionPixelSize(R.styleable.SwipeUpPanelLayout_swipeShadowHeight, -1);
                mParallaxOffset = ta.getDimensionPixelSize(R.styleable.SwipeUpPanelLayout_swipeParallaxOffset, -1);

                mMinFlingVelocity = ta.getInt(R.styleable.SwipeUpPanelLayout_swipeFlingVelocity, DEFAULT_MIN_FLING_VELOCITY);
                mCoveredFadeColor = ta.getColor(R.styleable.SwipeUpPanelLayout_swipeFadeColor, DEFAULT_FADE_COLOR);

                mDragViewResId = ta.getResourceId(R.styleable.SwipeUpPanelLayout_swipeDragView, -1);
                mScrollableViewResId = ta.getResourceId(R.styleable.SwipeUpPanelLayout_swipeScrollableView, -1);

                mOverlayContent = ta.getBoolean(R.styleable.SwipeUpPanelLayout_swipeOverlay, DEFAULT_OVERLAY_FLAG);
                mClipPanel = ta.getBoolean(R.styleable.SwipeUpPanelLayout_swipeClipPanel, DEFAULT_CLIP_PANEL_FLAG);

                mAnchorPoint = ta.getFloat(R.styleable.SwipeUpPanelLayout_swipeAnchorPoint, DEFAULT_ANCHOR_POINT);

                mSwipeState = SwipePanelState.values()[ta.getInt(R.styleable.SwipeUpPanelLayout_swipeInitialState, DEFAULT_SWIPE_STATE.ordinal())];

                int interpolatorResId = ta.getResourceId(R.styleable.SwipeUpPanelLayout_swipeScrollInterpolator, -1);
                if (interpolatorResId != -1) {
                    scrollerInterpolator = AnimationUtils.loadInterpolator(context, interpolatorResId);
                }
                ta.recycle();
            }
        }

        final float density = context.getResources().getDisplayMetrics().density;
        if (mPanelHeight == -1) {
            mPanelHeight = (int) (DEFAULT_PANEL_HEIGHT * density + 0.5f);
        }
        if (mShadowHeight == -1) {
            mShadowHeight = (int) (DEFAULT_SHADOW_HEIGHT * density + 0.5f);
        }
        if (mParallaxOffset == -1) {
            mParallaxOffset = (int) (DEFAULT_PARALLAX_OFFSET * density);
        }
        // If the shadow height is zero, don't show the shadow
        if (mShadowHeight > 0) {
            if (mIsSwipingUp) {
                mShadowDrawable = getResources().getDrawable(R.drawable.above_shadow, null);
            } else {
                mShadowDrawable = getResources().getDrawable(R.drawable.below_shadow, null);
            }
        } else {
            mShadowDrawable = null;
        }

        setWillNotDraw(false);

        mDragHelper = SwipeViewDragHelper.create(this, 0.5f, scrollerInterpolator, new DragHelperCallback());
        mDragHelper.setMinVelocity(mMinFlingVelocity * density);

        mIsTouchEnabled = true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mDragViewResId != -1) {
            setDragView(findViewById(mDragViewResId));
        }
        if (mScrollableViewResId != -1) {
            setScrollableView(findViewById(mScrollableViewResId));
        }
    }

    public void setGravity(int gravity) {
        if (gravity != Gravity.TOP && gravity != Gravity.BOTTOM) {
            throw new IllegalArgumentException("gravity must be set to either top or bottom");
        }
        mIsSwipingUp = gravity == Gravity.BOTTOM;
        if (!mFirstLayout) {
            requestLayout();
        }
    }

    public void setCoveredFadeColor(int color) {
        mCoveredFadeColor = color;
        requestLayout();
    }

    public int getCoveredFadeColor() {
        return mCoveredFadeColor;
    }

    public void setTouchEnabled(boolean enabled) {
        mIsTouchEnabled = enabled;
    }

    public boolean isTouchEnabled() {
        return mIsTouchEnabled && mSwipeableView != null && mSwipeState != SwipePanelState.HIDDEN;
    }

    public void setPanelHeight(int val) {
        if (getPanelHeight() == val) {
            return;
        }

        mPanelHeight = val;
        if (!mFirstLayout) {
            requestLayout();
        }

        if (getSwipePanelState() == SwipePanelState.COLLAPSED) {
            smoothToBottom();
            invalidate();
            return;
        }
    }

    protected void smoothToBottom() {
        smoothSwipeTo(0, 0);
    }

    public int getShadowHeight() {
        return mShadowHeight;
    }

    public void setShadowHeight(int val) {
        mShadowHeight = val;
        if (!mFirstLayout) {
            invalidate();
        }
    }

    public int getPanelHeight() {
        return mPanelHeight;
    }

    public int getCurrentParallaxOffset() {
        // Clamp swipe offset at zero for parallax computation;
        int offset = (int) (mParallaxOffset * Math.max(mSwipeOffset, 0));
        return mIsSwipingUp ? -offset : offset;
    }

    public void setParallaxOffset(int val) {
        mParallaxOffset = val;
        if (!mFirstLayout) {
            requestLayout();
        }
    }

    public int getMinFlingVelocity() {
        return mMinFlingVelocity;
    }

    public void setMinFlingVelocity(int val) {
        mMinFlingVelocity = val;
    }

    public void addPanelSwipeListener(PanelSwipeListener listener) {
        synchronized (mPanelSwipeListeners) {
            mPanelSwipeListeners.add(listener);
        }
    }

    public void removePanelSwipeListener(PanelSwipeListener listener) {
        synchronized (mPanelSwipeListeners) {
            mPanelSwipeListeners.remove(listener);
        }
    }

    public void setFadeOnClickListener(View.OnClickListener listener) {
        mFadeOnClickListener = listener;
    }

    public void setDragView(View dragView) {
        if (mDragView != null) {
            mDragView.setOnClickListener(null);
        }
        mDragView = dragView;
        if (mDragView != null) {
            mDragView.setClickable(true);
            mDragView.setFocusable(false);
            mDragView.setFocusableInTouchMode(false);
            mDragView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isEnabled() || !isTouchEnabled()) return;
                    if (mSwipeState != SwipePanelState.EXPANDED && mSwipeState != SwipePanelState.ANCHORED) {
                        if (mAnchorPoint < 1.0f) {
                            setSwipePanelState(SwipePanelState.ANCHORED);
                        } else {
                            setSwipePanelState(SwipePanelState.EXPANDED);
                        }
                    } else {
                        setSwipePanelState(SwipePanelState.COLLAPSED);
                    }
                }
            });
        }
    }

    public void setDragView(int dragViewResId) {
        mDragViewResId = dragViewResId;
        setDragView(findViewById(dragViewResId));
    }

    public void setScrollableView(View scrollableView) {
        mScrollableView = scrollableView;
    }

    public void setScrollableViewHelper(SwipeScrollableViewHelper helper) {
        mScrollableViewHelper = helper;
    }

    public void setAnchorPoint(float anchorPoint) {
        if (anchorPoint > 0 && anchorPoint <= 1) {
            mAnchorPoint = anchorPoint;
            mFirstLayout = true;
            requestLayout();
        }
    }

    public float getAnchorPoint() {
        return mAnchorPoint;
    }

    public void setOverlayed(boolean overlayed) {
        mOverlayContent = overlayed;
    }

    public boolean isOverlayed() {
        return mOverlayContent;
    }

    public void setClipPanel(boolean clip) {
        mClipPanel = clip;
    }

    public boolean isClipPanel() {
        return mClipPanel;
    }


    void dispatchOnPanelSwipe(View panel) {
        synchronized (mPanelSwipeListeners) {
            for (PanelSwipeListener l : mPanelSwipeListeners) {
                l.onPanelSwipe(panel, mSwipeOffset);
            }
        }
    }


    void dispatchOnPanelStateChanged(View panel, SwipePanelState previousState, SwipePanelState newState) {
        synchronized (mPanelSwipeListeners) {
            for (PanelSwipeListener l : mPanelSwipeListeners) {
                l.onSwipePanelStateChanged(panel, previousState, newState);
            }
        }
        sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
    }

    void updateObscuredViewVisibility() {
        if (getChildCount() == 0) {
            return;
        }
        final int leftBound = getPaddingLeft();
        final int rightBound = getWidth() - getPaddingRight();
        final int topBound = getPaddingTop();
        final int bottomBound = getHeight() - getPaddingBottom();
        final int left;
        final int right;
        final int top;
        final int bottom;
        if (mSwipeableView != null && hasOpaqueBackground(mSwipeableView)) {
            left = mSwipeableView.getLeft();
            right = mSwipeableView.getRight();
            top = mSwipeableView.getTop();
            bottom = mSwipeableView.getBottom();
        } else {
            left = right = top = bottom = 0;
        }
        View child = getChildAt(0);
        final int clampedChildLeft = Math.max(leftBound, child.getLeft());
        final int clampedChildTop = Math.max(topBound, child.getTop());
        final int clampedChildRight = Math.min(rightBound, child.getRight());
        final int clampedChildBottom = Math.min(bottomBound, child.getBottom());
        final int vis;
        if (clampedChildLeft >= left && clampedChildTop >= top &&
                clampedChildRight <= right && clampedChildBottom <= bottom) {
            vis = INVISIBLE;
        } else {
            vis = VISIBLE;
        }
        child.setVisibility(vis);
    }

    void setAllChildrenVisible() {
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == INVISIBLE) {
                child.setVisibility(VISIBLE);
            }
        }
    }

    private static boolean hasOpaqueBackground(View v) {
        final Drawable bg = v.getBackground();
        return bg != null && bg.getOpacity() == PixelFormat.OPAQUE;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mFirstLayout = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode != MeasureSpec.EXACTLY && widthMode != MeasureSpec.AT_MOST) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (heightMode != MeasureSpec.EXACTLY && heightMode != MeasureSpec.AT_MOST) {
            throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        }

        final int childCount = getChildCount();

        if (childCount != 2) {
            throw new IllegalStateException("Swiping up panel layout must have exactly 2 children!");
        }

        mMainView = getChildAt(0);
        mSwipeableView = getChildAt(1);
        if (mDragView == null) {
            setDragView(mSwipeableView);
        }

        // If the swiping panel is not visible, then put the whole view in the hidden state
        if (mSwipeableView.getVisibility() != VISIBLE) {
            mSwipeState = SwipePanelState.HIDDEN;
        }

        int layoutHeight = heightSize - getPaddingTop() - getPaddingBottom();
        int layoutWidth = widthSize - getPaddingLeft() - getPaddingRight();

        // First pass. Measure based on child LayoutParams width/height.
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            // We always measure the swiping panel in order to know it's height (needed for show panel)
            if (child.getVisibility() == GONE && i == 0) {
                continue;
            }

            int height = layoutHeight;
            int width = layoutWidth;
            if (child == mMainView) {
                if (!mOverlayContent && mSwipeState != SwipePanelState.HIDDEN) {
                    height -= mPanelHeight;
                }

                width -= lp.leftMargin + lp.rightMargin;
            } else if (child == mSwipeableView) {
                // The swipeable view should be aware of its top margin.
                // See https://github.com/umano/AndroidSlidingUpPanel/issues/412.
                height -= lp.topMargin;
            }

            int childWidthSpec;
            if (lp.width == LayoutParams.WRAP_CONTENT) {
                childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            } else if (lp.width == LayoutParams.MATCH_PARENT) {
                childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            } else {
                childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
            }

            int childHeightSpec;
            if (lp.height == LayoutParams.WRAP_CONTENT) {
                childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            } else {
                // Modify the height based on the weight.
                if (lp.weight > 0 && lp.weight < 1) {
                    height = (int) (height * lp.weight);
                } else if (lp.height != LayoutParams.MATCH_PARENT) {
                    height = lp.height;
                }
                childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }

            child.measure(childWidthSpec, childHeightSpec);

            if (child == mSwipeableView) {
                mSwipeRange = mSwipeableView.getMeasuredHeight() - mPanelHeight;
            }
        }

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();

        final int childCount = getChildCount();

        if (mFirstLayout) {
            switch (mSwipeState) {
                case EXPANDED:
                    mSwipeOffset = 1.0f;
                    break;
                case ANCHORED:
                    mSwipeOffset = mAnchorPoint;
                    break;
                case HIDDEN:
                    int newTop = computePanelTopPosition(0.0f) + (mIsSwipingUp ? +mPanelHeight : -mPanelHeight);
                    mSwipeOffset = computeSwipeOffset(newTop);
                    break;
                default:
                    mSwipeOffset = 0.f;
                    break;
            }
        }

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            // Always layout the swiping view on the first layout
            if (child.getVisibility() == GONE && (i == 0 || mFirstLayout)) {
                continue;
            }

            final int childHeight = child.getMeasuredHeight();
            int childTop = paddingTop;

            if (child == mSwipeableView) {
                childTop = computePanelTopPosition(mSwipeOffset);
            }

            if (!mIsSwipingUp) {
                if (child == mMainView && !mOverlayContent) {
                    childTop = computePanelTopPosition(mSwipeOffset) + mSwipeableView.getMeasuredHeight();
                }
            }
            final int childBottom = childTop + childHeight;
            final int childLeft = paddingLeft + lp.leftMargin;
            final int childRight = childLeft + child.getMeasuredWidth();

            child.layout(childLeft, childTop, childRight, childBottom);
        }

        if (mFirstLayout) {
            updateObscuredViewVisibility();
        }
        applyParallaxForCurrentSwipeOffset();

        mFirstLayout = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Recalculate swiping panes and their details
        if (h != oldh) {
            mFirstLayout = true;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "(Swipe) onInterceptTouchEvent:");
        // If the scrollable view is handling touch, neonMeasurever intercept
        if (mIsScrollableViewHandlingTouch || !isTouchEnabled()) {
            mDragHelper.abort();
            return false;
        }

        final int action = ev.getAction() ;
        final float x = ev.getX();
        final float y = ev.getY();
        final float adx = Math.abs(x - mInitialMotionX);
        final float ady = Math.abs(y - mInitialMotionY);
        final int dragSlop = mDragHelper.getTouchSlop();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mIsUnableToDrag = false;
                mInitialMotionX = x;
                mInitialMotionY = y;
                if (!isViewUnder(mDragView, (int) x, (int) y)) {
                    mDragHelper.cancel();
                    mIsUnableToDrag = true;
                    return false;
                }

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (ady > dragSlop && adx > ady) {
                    mDragHelper.cancel();
                    mIsUnableToDrag = true;
                    return false;
                }
                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // If the dragView is still dragging when we get here, we need to call processTouchEvent
                // so that the view is settled
                // Added to make scrollable views work (tokudu)
                if (mDragHelper.isDragging()) {
                    mDragHelper.processTouchEvent(ev);
                    return true;
                }
                // Check if this was a click on the faded part of the screen, and fire off the listener if there is one.
                if (ady <= dragSlop
                        && adx <= dragSlop
                        && mSwipeOffset > 0 && !isViewUnder(mSwipeableView, (int) mInitialMotionX, (int) mInitialMotionY) && mFadeOnClickListener != null) {
                    playSoundEffect(android.view.SoundEffectConstants.CLICK);
                    mFadeOnClickListener.onClick(this);
                    return true;
                }
                break;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "(Swipe) onTouchEvent:");
        if (!isEnabled() || !isTouchEnabled()) {
            return super.onTouchEvent(ev);
        }
        try {
            mDragHelper.processTouchEvent(ev);
            return true;
        } catch (Exception ex) {
            // Ignore the pointer out of range exception
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "(Swipe) dispatchTouchEvent:");
        final int action = ev.getAction();

        if (!isEnabled() || !isTouchEnabled() || (mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)) {
            Log.i(TAG, "(Swipe) dispatchTouchEvent: !isEnabled() || !isTouchEnabled() || (mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)");
            mDragHelper.abort();
            return super.dispatchTouchEvent(ev);
        }

        final float x = ev.getX();
        final float y = ev.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            log_SwipeMoveEventCounts = 0;

            mIsScrollableViewHandlingTouch = false;
            mPrevMotionX = x;
            mPrevMotionY = y;
        } else if (action == MotionEvent.ACTION_MOVE) {
            Log.i(TAG, "(Swipe) dispatchTouchEvent: log_SwipeMoveEventCounts = " + log_SwipeMoveEventCounts);
            log_SwipeMoveEventCounts++;

            float dx = x - mPrevMotionX;
            float dy = y - mPrevMotionY;
            mPrevMotionX = x;
            mPrevMotionY = y;

            if (Math.abs(dx) > Math.abs(dy)) {
                return super.dispatchTouchEvent(ev);
            }

            if (!isViewUnder(mScrollableView, (int) mInitialMotionX, (int) mInitialMotionY)) {
                return super.dispatchTouchEvent(ev);
            }

            if (dy * (mIsSwipingUp ? 1 : -1) > 0) { // Collapsing

                if (mScrollableViewHelper.getScrollableViewScrollPosition(mScrollableView, mIsSwipingUp) > 0) {
                    mIsScrollableViewHandlingTouch = true;
                    return super.dispatchTouchEvent(ev);
                }


                if (mIsScrollableViewHandlingTouch) {
                    MotionEvent up = MotionEvent.obtain(ev);
                    up.setAction(MotionEvent.ACTION_CANCEL);
                    super.dispatchTouchEvent(up);
                    up.recycle();

                    ev.setAction(MotionEvent.ACTION_DOWN);
                }

                mIsScrollableViewHandlingTouch = false;
                //Log.i(TAG, "(Swipe) dispatchTouchEvent: Down (dy > 0)");
                return this.onTouchEvent(ev);
            } else if (dy * (mIsSwipingUp ? 1 : -1) < 0) { // Expanding

                if (mSwipeOffset < 1.0f) {
                    mIsScrollableViewHandlingTouch = false;
                    //Log.i(TAG, "(Swipe) dispatchTouchEvent: Up (dy < 0)");
                    return this.onTouchEvent(ev);
                }

                if (!mIsScrollableViewHandlingTouch && mDragHelper.isDragging()) {
                    mDragHelper.cancel();
                    ev.setAction(MotionEvent.ACTION_DOWN);
                }

                mIsScrollableViewHandlingTouch = true;
                return super.dispatchTouchEvent(ev);
            }
        } else if (action == MotionEvent.ACTION_UP) {
            if (mIsScrollableViewHandlingTouch) {
                mDragHelper.setDragState(SwipeViewDragHelper.STATE_IDLE);
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean isViewUnder(View view, int x, int y) {
        if (view == null) return false;
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        this.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() &&
                screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
    }

    private int computePanelTopPosition(float swipeOffset) {
        int swipingViewHeight = mSwipeableView != null ? mSwipeableView.getMeasuredHeight() : 0;
        int swipePixelOffset = (int) (swipeOffset * mSwipeRange);
        // Compute the top of the panel if its collapsed
        return mIsSwipingUp
                ? getMeasuredHeight() - getPaddingBottom() - mPanelHeight - swipePixelOffset
                : getPaddingTop() - swipingViewHeight + mPanelHeight + swipePixelOffset;
    }

    private float computeSwipeOffset(int topPosition) {
        final int topBoundCollapsed = computePanelTopPosition(0);

        return (mIsSwipingUp
                ? (float) (topBoundCollapsed - topPosition) / mSwipeRange
                : (float) (topPosition - topBoundCollapsed) / mSwipeRange);
    }

    public SwipePanelState getSwipePanelState() {
        return mSwipeState;
    }

    public void setSwipePanelState(SwipePanelState state) {

        if(mDragHelper.getViewDragState() == SwipeViewDragHelper.STATE_SETTLING){
            Log.d(TAG, "View is settling. Aborting animation.");
            mDragHelper.abort();
        }

        if (state == null || state == SwipePanelState.DRAGGING) {
            throw new IllegalArgumentException("Panel state cannot be null or DRAGGING.");
        }
        if (!isEnabled()
                || (!mFirstLayout && mSwipeableView == null)
                || state == mSwipeState
                || mSwipeState == SwipePanelState.DRAGGING) return;

        if (mFirstLayout) {
            setPanelStateInternal(state);
        } else {
            if (mSwipeState == SwipePanelState.HIDDEN) {
                mSwipeableView.setVisibility(View.VISIBLE);
                requestLayout();
            }
            switch (state) {
                case ANCHORED:
                    smoothSwipeTo(mAnchorPoint, 0);
                    break;
                case COLLAPSED:
                    smoothSwipeTo(0, 0);
                    break;
                case EXPANDED:
                    smoothSwipeTo(1.0f, 0);
                    break;
                case HIDDEN:
                    int newTop = computePanelTopPosition(0.0f) + (mIsSwipingUp ? +mPanelHeight : -mPanelHeight);
                    smoothSwipeTo(computeSwipeOffset(newTop), 0);
                    break;
            }
        }
    }

    private void setPanelStateInternal(SwipePanelState state) {
        if (mSwipeState == state) return;
        SwipePanelState oldState = mSwipeState;
        mSwipeState = state;
        dispatchOnPanelStateChanged(this, oldState, state);
    }

    @SuppressLint("NewApi")
    private void applyParallaxForCurrentSwipeOffset() {
        if (mParallaxOffset > 0) {
            int mainViewOffset = getCurrentParallaxOffset();
            //ViewCompat.setTranslationY(mMainView, mainViewOffset);
            mMainView.setTranslationY(mainViewOffset);
        }
    }

    private void onPanelDragged(int newTop) {
        if (mSwipeState != SwipePanelState.DRAGGING) {
            mLastNotDraggingSwipeState = mSwipeState;
        }
        setPanelStateInternal(SwipePanelState.DRAGGING);
        // Recompute the swipe offset based on the new top position
        mSwipeOffset = computeSwipeOffset(newTop);
        applyParallaxForCurrentSwipeOffset();
        // Dispatch the swipe event
        dispatchOnPanelSwipe(mSwipeableView);
        // If the swipe offset is negative, and overlay is not on, we need to increase the
        // height of the main content
        LayoutParams lp = (LayoutParams) mMainView.getLayoutParams();
        int defaultHeight = getHeight() - getPaddingBottom() - getPaddingTop() - mPanelHeight;

        if (mSwipeOffset <= 0 && !mOverlayContent) {
            // expand the main view
            lp.height = mIsSwipingUp ? (newTop - getPaddingBottom()) : (getHeight() - getPaddingBottom() - mSwipeableView.getMeasuredHeight() - newTop);
            if (lp.height == defaultHeight) {
                lp.height = LayoutParams.MATCH_PARENT;
            }
            mMainView.requestLayout();
        } else if (lp.height != LayoutParams.MATCH_PARENT && !mOverlayContent) {
            lp.height = LayoutParams.MATCH_PARENT;
            mMainView.requestLayout();
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean result;

        final int save = canvas.save();

        if (mSwipeableView != null && mSwipeableView != child) { // if main view
            // Clip against the swiper; no sense drawing what will immediately be covered,
            // Unless the panel is set to overlay content
            canvas.getClipBounds(mTmpRect);
            if (!mOverlayContent) {
                if (mIsSwipingUp) {
                    mTmpRect.bottom = Math.min(mTmpRect.bottom, mSwipeableView.getTop());
                } else {
                    mTmpRect.top = Math.max(mTmpRect.top, mSwipeableView.getBottom());
                }
            }
            if (mClipPanel) {
                canvas.clipRect(mTmpRect);
            }

            result = super.drawChild(canvas, child, drawingTime);

            if (mCoveredFadeColor != 0 && mSwipeOffset > 0) {
                final int baseAlpha = (mCoveredFadeColor & 0xff000000) >>> 24;
                final int imag = (int) (baseAlpha * mSwipeOffset);
                final int color = imag << 24 | (mCoveredFadeColor & 0xffffff);
                mCoveredFadePaint.setColor(color);
                canvas.drawRect(mTmpRect, mCoveredFadePaint);
            }
        } else {
            result = super.drawChild(canvas, child, drawingTime);
        }

        canvas.restoreToCount(save);

        return result;
    }

    boolean smoothSwipeTo(float swipeOffset, int velocity) {
        if (!isEnabled() || mSwipeableView == null) {
            // Nothing to do.
            return false;
        }

        int panelTop = computePanelTopPosition(swipeOffset);

        if (mDragHelper.smoothSwipeViewTo(mSwipeableView, mSwipeableView.getLeft(), panelTop)) {
            setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper != null && mDragHelper.continueSettling(true)) {
            if (!isEnabled()) {
                mDragHelper.abort();
                return;
            }

            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);

        // draw the shadow
        if (mShadowDrawable != null && mSwipeableView != null) {
            final int right = mSwipeableView.getRight();
            final int top;
            final int bottom;
            if (mIsSwipingUp) {
                top = mSwipeableView.getTop() - mShadowHeight;
                bottom = mSwipeableView.getTop();
            } else {
                top = mSwipeableView.getBottom();
                bottom = mSwipeableView.getBottom() + mShadowHeight;
            }
            final int left = mSwipeableView.getLeft();
            mShadowDrawable.setBounds(left, top, right, bottom);
            mShadowDrawable.draw(c);
        }
    }

    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) v;
            final int scrollX = v.getScrollX();
            final int scrollY = v.getScrollY();
            final int count = group.getChildCount();
            // Count backwards - let topmost views consume scroll distance first.
            for (int i = count - 1; i >= 0; i--) {
                final View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() &&
                        y + scrollY >= child.getTop() && y + scrollY < child.getBottom() &&
                        canScroll(child, true, dx, x + scrollX - child.getLeft(),
                                y + scrollY - child.getTop())) {
                    return true;
                }
            }
        }
        return checkV && ViewCompat.canScrollHorizontally(v, -dx);
    }


    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MarginLayoutParams
                ? new LayoutParams((MarginLayoutParams) p)
                : new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams && super.checkLayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putSerializable(SWIPING_STATE, mSwipeState != SwipePanelState.DRAGGING ? mSwipeState : mLastNotDraggingSwipeState);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mSwipeState = (SwipePanelState) bundle.getSerializable(SWIPING_STATE);
            mSwipeState = mSwipeState == null ? DEFAULT_SWIPE_STATE : mSwipeState;
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }

    private class DragHelperCallback extends SwipeViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return !mIsUnableToDrag && child == mSwipeableView;

        }

        @Override
        public void onViewDragStateChanged(int state) {
            if (mDragHelper != null && mDragHelper.getViewDragState() == SwipeViewDragHelper.STATE_IDLE) {
                mSwipeOffset = computeSwipeOffset(mSwipeableView.getTop());
                applyParallaxForCurrentSwipeOffset();

                if (mSwipeOffset == 1) {
                    updateObscuredViewVisibility();
                    setPanelStateInternal(SwipePanelState.EXPANDED);
                } else if (mSwipeOffset == 0) {
                    setPanelStateInternal(SwipePanelState.COLLAPSED);
                } else if (mSwipeOffset < 0) {
                    setPanelStateInternal(SwipePanelState.HIDDEN);
                    mSwipeableView.setVisibility(View.INVISIBLE);
                } else {
                    updateObscuredViewVisibility();
                    setPanelStateInternal(SwipePanelState.ANCHORED);
                }
            }
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            setAllChildrenVisible();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            onPanelDragged(top);
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int target = 0;

            // direction is always positive if we are swiping in the expanded direction
            float direction = mIsSwipingUp ? -yvel : yvel;

            if (direction > 0 && mSwipeOffset <= mAnchorPoint) {
                // swipe up -> expand and stop at anchor point
                target = computePanelTopPosition(mAnchorPoint);
            } else if (direction > 0 && mSwipeOffset > mAnchorPoint) {
                // swipe up past anchor -> expand
                target = computePanelTopPosition(1.0f);
            } else if (direction < 0 && mSwipeOffset >= mAnchorPoint) {
                // swipe down -> collapse and stop at anchor point
                target = computePanelTopPosition(mAnchorPoint);
            } else if (direction < 0 && mSwipeOffset < mAnchorPoint) {
                // swipe down past anchor -> collapse
                target = computePanelTopPosition(0.0f);
            } else if (mSwipeOffset >= (1.f + mAnchorPoint) / 2) {
                // zero velocity, and far enough from anchor point => expand to the top
                target = computePanelTopPosition(1.0f);
            } else if (mSwipeOffset >= mAnchorPoint / 2) {
                // zero velocity, and close enough to anchor point => go to anchor
                target = computePanelTopPosition(mAnchorPoint);
            } else {
                // settle at the bottom
                target = computePanelTopPosition(0.0f);
            }

            if (mDragHelper != null) {
                mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), target);
            }
            invalidate();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mSwipeRange;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int collapsedTop = computePanelTopPosition(0.f);
            final int expandedTop = computePanelTopPosition(1.0f);
            if (mIsSwipingUp) {
                return Math.min(Math.max(top, expandedTop), collapsedTop);
            } else {
                return Math.min(Math.max(top, collapsedTop), expandedTop);
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] ATTRS = new int[]{
                android.R.attr.layout_weight
        };

        public float weight = 0;

        public LayoutParams() {
            super(MATCH_PARENT, MATCH_PARENT);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height);
            this.weight = weight;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            final TypedArray ta = c.obtainStyledAttributes(attrs, ATTRS);
            if (ta != null) {
                this.weight = ta.getFloat(0, 0);
                ta.recycle();
            }


        }
    }
}