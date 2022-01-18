package com.example.swipeup.view_utils.swipeuppanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel

public class SwipeUpPanelLayout extends ViewGroup {

    private static final String TAG = "SwipeUpPanelLayout";

    private static final int DEFAULT_PANEL_HEIGHT = 68; // dp;
    private static final float DEFAULT_ANCHOR_POINT = 1.0f; // In relative %
    private static final int DEFAULT_SHADOW_HEIGHT = 4; // dp;

    //Default parallax length of the main view
    private static final int DEFAULT_PARALLAX_OFFSET = 0;

    private static final boolean DEFAULT_OVERLAY_FLAG = false;
    private static final boolean DEFAULT_CLIP_PANEL_FLAG = true;

    private static final int DEFAULT_MIN_FLING_VELOCITY = 400; // dips per second
    private static final int DEFAULT_FADE_COLOR = 0x99000000;

    public enum SwipePanelState {
        EXPANDED,   //- 확장된
        COLLAPSED,  //- 접힌, 접혀진
        ANCHORED,   //- 고정된
        HIDDEN,     //- 감춰진
        DRAGGING    //- 질질끄는
    }

    private static SwipePanelState DEFAULT_SWIPE_STATE = SwipePanelState.COLLAPSED;

    private static final int[] DEFAULT_ATTRIBUTES_FOR_XML = new int[]{
            android.R.attr.gravity
    };

    //private int mParallaxOffset = -1;
    private View mSwipeMainView;
    private View mDragView;
    private View mScrollableView;
    private ScrollableViewHelper mScrollableViewHelper = new ScrollableViewHelper();
    private final SwipeViewDragHelper mSwipeViewDragHelper;
    private final Drawable mSwipeShadowDrawable;
    private final List<SwipePanelListener> mSwipePanelListeners = new CopyOnWriteArrayList<>();

    // Read information from "activity_swipe_up.xml"
    private int mSwipePanelHeight = -1;
    private int mSwipeShadowHeight = -1;
    //private int mSwipeParallaxOffset = -1;
    private int mSwipeDragViewResId = -1;
    private int mSwipeScrollableViewResId;
    private boolean mSwipeOverlayContent = DEFAULT_OVERLAY_FLAG;
    private boolean mSwipeClipPanel = DEFAULT_CLIP_PANEL_FLAG;
    private float mSwipeAnchorPoint = 1.f;
    private SwipePanelState mSwipeState = DEFAULT_SWIPE_STATE;
    private int mSwipeMinFlingVelocity = DEFAULT_MIN_FLING_VELOCITY;
    private int mSwipeCoveredFadeColor = DEFAULT_FADE_COLOR;

    private boolean mIsSwipingUp;    // (gravity == Gravity.BOTTOM) => mSwipingUp is true
    private boolean mFirstLayout = true;
    private boolean mIsTouchEnabled;
    private float mAnchorPoint = 1.f;
    private int mSwipeRange;

    private boolean mIsScrollableViewHandlingTouch = false;
    private float mPrevMotionX;
    private float mPrevMotionY;
    private float mInitialMotionX;
    private float mInitialMotionY;

    private final Rect mTmpRect = new Rect();
    private boolean mOverlayContent = DEFAULT_OVERLAY_FLAG;  // default : false
    private float mSwipeOffset;
    private final Paint mCoveredFadePaint = new Paint();

    private boolean mIsUnableToDrag;
    private View mSwipeableView;


    public SwipeUpPanelLayout(Context context) {
        this(context, null);
    }

    public SwipeUpPanelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeUpPanelLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) {
            mSwipeShadowDrawable = null;
            mSwipeViewDragHelper = null;
            return;
        }

        Interpolator scrollerInterpolator = null;
        if (attrs != null) {
            TypedArray defaultAttributes = context.obtainStyledAttributes(attrs, DEFAULT_ATTRIBUTES_FOR_XML);

            if (defaultAttributes != null) {
                int gravity = defaultAttributes.getInt(0, Gravity.NO_GRAVITY);
                setGravity(gravity);
                defaultAttributes.recycle();
            }

            TypedArray customTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SwipeUpPanelLayout);

            if (customTypedArray != null) {
                mSwipePanelHeight = customTypedArray.getDimensionPixelSize(R.styleable.SwipeUpPanelLayout_swipePanelHeight, -1);
                mSwipeShadowHeight = customTypedArray.getDimensionPixelSize(R.styleable.SwipeUpPanelLayout_swipeShadowHeight, -1);
                //mSwipeParallaxOffset = customTypedArray.getDimensionPixelSize(R.styleable.SwipeUpPanelLayout_swipeParallaxOffset, -1);

                mSwipeDragViewResId = customTypedArray.getResourceId(R.styleable.SwipeUpPanelLayout_swipeDragView, -1);
                mSwipeScrollableViewResId = customTypedArray.getResourceId(R.styleable.SwipeUpPanelLayout_swipeScrollableView, -1);

                mSwipeOverlayContent = customTypedArray.getBoolean(R.styleable.SwipeUpPanelLayout_swipeOverlay, DEFAULT_OVERLAY_FLAG);
                mSwipeClipPanel = customTypedArray.getBoolean(R.styleable.SwipeUpPanelLayout_swipeClipPanel, DEFAULT_CLIP_PANEL_FLAG); // default : true

                mSwipeAnchorPoint = customTypedArray.getFloat(R.styleable.SwipeUpPanelLayout_swipeAnchorPoint, DEFAULT_ANCHOR_POINT); // default : 1.0f

                // default : DEFAULT_SWIPE_STATE - COLLAPSED,  //- 접힌, 접혀진
                mSwipeState = SwipePanelState.values()[customTypedArray.getInt(R.styleable.SwipeUpPanelLayout_swipeInitialState, DEFAULT_SWIPE_STATE.ordinal())];

                mSwipeMinFlingVelocity = customTypedArray.getInt(R.styleable.SwipeUpPanelLayout_swipeFlingVelocity, DEFAULT_MIN_FLING_VELOCITY); // default : 400 dips per second
                mSwipeCoveredFadeColor = customTypedArray.getColor(R.styleable.SwipeUpPanelLayout_swipeFadeColor, DEFAULT_FADE_COLOR); // default : 0x99000000

                int interpolatorResId = customTypedArray.getResourceId(R.styleable.SwipeUpPanelLayout_swipeScrollInterpolator, -1);
                if (interpolatorResId != -1) {
                    scrollerInterpolator = AnimationUtils.loadInterpolator(context, interpolatorResId);
                }
                customTypedArray.recycle();
            }
        }

        final float density = context.getResources().getDisplayMetrics().density;
        if (mSwipePanelHeight == -1) {
            mSwipePanelHeight = (int) (DEFAULT_PANEL_HEIGHT * density + 0.5f); // default : 68dp
        }
        if (mSwipeShadowHeight == -1) {
            mSwipeShadowHeight = (int) (DEFAULT_SHADOW_HEIGHT * density + 0.5f); // default : 4dp
        }
//        if (mSwipeParallaxOffset == -1) {
//            mSwipeParallaxOffset = (int) (DEFAULT_PARALLAX_OFFSET * density); // default : 0dp
//        }
        // If the shadow height is zero, don't show the shadow
        if (mSwipeShadowHeight > 0) {
            if (mIsSwipingUp) {
                mSwipeShadowDrawable = getResources().getDrawable(R.drawable.above_shadow, null);
            } else {
                mSwipeShadowDrawable = getResources().getDrawable(R.drawable.below_shadow, null);
            }
        } else {
            mSwipeShadowDrawable = null;
        }

        setWillNotDraw(false);

        mSwipeViewDragHelper = SwipeViewDragHelper.create(this, 0.5f, scrollerInterpolator, new SwipeDragHelperCallback());
        mSwipeViewDragHelper.setMinVelocity(mSwipeMinFlingVelocity * density);

        mIsTouchEnabled = true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mSwipeDragViewResId != -1) {
            setDragView(findViewById(mSwipeDragViewResId));
        }
        if (mSwipeScrollableViewResId != -1) {
            setScrollableView(findViewById(mSwipeScrollableViewResId));
        }
    }

    public void setScrollableView(View scrollableView) {
        mScrollableView = scrollableView;
    }

    public void setGravity(int gravity) {
        if (gravity != Gravity.TOP && gravity != Gravity.BOTTOM) {
            throw new IllegalArgumentException("gravity must be set to either top or bottom");
        }
        mIsSwipingUp = (gravity == Gravity.BOTTOM);
        if (!mFirstLayout) {
            requestLayout();
        }
    }

    public void addPanelSwipeListener(SwipePanelListener listener) {
        synchronized (mSwipePanelListeners) {
            mSwipePanelListeners.add(listener);
        }
    }

    public interface SwipePanelListener {
        public void onPanelSwipe(View panel, float swipeOffset);

        public void onPanelStateChanged(View panel, SwipePanelState previousState, SwipePanelState newState);
    }

    private View.OnClickListener mFadeOnClickListener;

    public void setFadeOnClickListener(View.OnClickListener listener) {
        mFadeOnClickListener = listener;
    }

    public void setPanelState(SwipePanelState state) {

        // Abort any running animation, to allow state change
        if (mSwipeViewDragHelper.getViewDragState() == SwipeViewDragHelper.STATE_SETTLING) {
            Log.d(TAG, "View is settling. Aborting animation.");
            mSwipeViewDragHelper.abort();
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
                    smoothSwipeTo(mSwipeAnchorPoint, 0);
                    break;
                case COLLAPSED:
                    smoothSwipeTo(0, 0);
                    break;
                case EXPANDED:
                    smoothSwipeTo(1.0f, 0);
                    break;
                case HIDDEN:
                    //int newTop = computePanelTopPosition(0.0f) + (mIsSwipingUp ? +mSwipePanelHeight : -mSwipePanelHeight);
                    int newTop = computePanelTopPosition(0.0f) + mSwipePanelHeight; /* mIsSwipingUp */
                    smoothSwipeTo(computeSwipeOffset(newTop), 0);
                    break;
            }
        }
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

        public LayoutParams(SlidingUpPanelLayout.LayoutParams source) {
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
            throw new IllegalStateException("Sliding up panel layout must have exactly 2 children!");
        }

        mSwipeMainView = getChildAt(0);
        mSwipeableView = getChildAt(1);
        if (mSwipeMainView == null) {
            setDragView(mSwipeableView);
        }

        // If the sliding panel is not visible, then put the whole view in the hidden state
        if (mSwipeableView.getVisibility() != VISIBLE) {
            mSwipeState = SwipePanelState.HIDDEN;
        }

        int layoutHeight = heightSize - getPaddingTop() - getPaddingBottom();
        int layoutWidth = widthSize - getPaddingLeft() - getPaddingRight();

        // First pass. Measure based on child LayoutParams width/height.
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
// ToDo : Fix this later
//            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            // We always measure the sliding panel in order to know it's height (needed for show panel)
            if (child.getVisibility() == GONE && i == 0) {
                continue;
            }

// ToDo : Fix this later
//            int height = layoutHeight;
//            int width = layoutWidth;
//            if (child == mSwipeMainView) {
//                if (!mOverlayContent && mSwipeState != SwipePanelState.HIDDEN) {
//                    height -= mSwipePanelHeight;
//                }
//
//                width -= lp.leftMargin + lp.rightMargin;
//            } else if (child == mSwipeableView) {
//                // The swipeable view should be aware of its top margin.
//                // See https://github.com/umano/AndroidSlidingUpPanel/issues/412.
//                height -= lp.topMargin;
//            }
//
//            int childWidthSpec;
//            if (lp.width == LayoutParams.WRAP_CONTENT) {
//                childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
//            } else if (lp.width == LayoutParams.MATCH_PARENT) {
//                childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
//            } else {
//                childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
//            }
//
//            int childHeightSpec;
//            if (lp.height == LayoutParams.WRAP_CONTENT) {
//                childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
//            } else {
//                // Modify the height based on the weight.
//                if (lp.weight > 0 && lp.weight < 1) {
//                    height = (int) (height * lp.weight);
//                } else if (lp.height != LayoutParams.MATCH_PARENT) {
//                    height = lp.height;
//                }
//                childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//            }
//
//            child.measure(childWidthSpec, childHeightSpec);

            child.measure(widthMeasureSpec, heightMeasureSpec);

            if (child == mSwipeableView) {
                mSwipeRange = mSwipeableView.getMeasuredHeight() - mSwipePanelHeight;
            }
        }

        setMeasuredDimension(widthSize, heightSize);
    }

    public boolean isTouchEnabled() {
        return mIsTouchEnabled && mSwipeableView != null && mSwipeState != SwipeUpPanelLayout.SwipePanelState.HIDDEN;
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
                    if (mSwipeState != SwipeUpPanelLayout.SwipePanelState.EXPANDED && mSwipeState != SwipeUpPanelLayout.SwipePanelState.ANCHORED) {
                        if (mAnchorPoint < 1.0f) {
                            setPanelState(SwipeUpPanelLayout.SwipePanelState.ANCHORED);
                        } else {
                            setPanelState(SwipeUpPanelLayout.SwipePanelState.EXPANDED);
                        }
                    } else {
                        setPanelState(SwipeUpPanelLayout.SwipePanelState.COLLAPSED);
                    }
                }
            });
        }
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
                    int newTop = computePanelTopPosition(0.0f) + (mIsSwipingUp ? +mSwipePanelHeight : -mSwipePanelHeight);
                    mSwipeOffset = computeSwipeOffset(newTop);
                    break;
                default:
                    mSwipeOffset = 0.f;
                    break;
            }
        }

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
// ToDo : Fix this later
//            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            // Always layout the sliding view on the first layout
            if (child.getVisibility() == GONE && (i == 0 || mFirstLayout)) {
                continue;
            }

            final int childHeight = child.getMeasuredHeight();
            int childTop = paddingTop;

            if (child == mSwipeableView) {
                childTop = computePanelTopPosition(mSwipeOffset);
            }

            if (!mIsSwipingUp) {
                if (child == mSwipeMainView && !mOverlayContent) {
                    childTop = computePanelTopPosition(mSwipeOffset) + mSwipeableView.getMeasuredHeight();
                }
            }
            final int childBottom = childTop + childHeight;
// ToDo : Fix this later
//            final int childLeft = paddingLeft + lp.leftMargin;
            final int childLeft = paddingLeft + 20; /*lp.leftMargin*/
            final int childRight = childLeft + child.getMeasuredWidth();

            child.layout(childLeft, childTop, childRight, childBottom);
        }

        if (mFirstLayout) {
            updateObscuredViewVisibility();
        }
        //applyParallaxForCurrentSwipeOffset();

        mFirstLayout = false;
    }


    @Override
    public void draw(Canvas c) {
        super.draw(c);

        // draw the shadow
        if (mSwipeShadowDrawable != null && mSwipeableView != null) {
            final int right = mSwipeableView.getRight();
            final int top;
            final int bottom;
            if (mIsSwipingUp) {
                top = mSwipeableView.getTop() - mSwipeShadowHeight;
                bottom = mSwipeableView.getTop();
            } else {
                top = mSwipeableView.getBottom();
                bottom = mSwipeableView.getBottom() + mSwipeShadowHeight;
            }
            final int left = mSwipeableView.getLeft();
            mSwipeShadowDrawable.setBounds(left, top, right, bottom);
            mSwipeShadowDrawable.draw(c);
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean result;
        final int save = canvas.save();

        if (mSwipeableView != null && mSwipeableView != child) { // if main view
            // Clip against the swiper; no sense drawing what will immediately be covered, Unless the panel is set to overlay content
            canvas.getClipBounds(mTmpRect);
            if (!mOverlayContent) {
                if (mIsSwipingUp) {
                    mTmpRect.bottom = Math.min(mTmpRect.bottom, mSwipeableView.getTop());
                } else {
                    //mTmpRect.top = Math.max(mTmpRect.top, mSwipeableView.getBottom());
                }
            }
            if (mSwipeClipPanel) {
                canvas.clipRect(mTmpRect);
            }

            result = super.drawChild(canvas, child, drawingTime);

            if (mSwipeCoveredFadeColor != 0 && mSwipeOffset > 0) {
                final int baseAlpha = (mSwipeCoveredFadeColor & 0xff000000) >>> 24;
                final int imag = (int) (baseAlpha * mSwipeOffset);
                final int color = imag << 24 | (mSwipeCoveredFadeColor & 0xffffff);
                mCoveredFadePaint.setColor(color);
                canvas.drawRect(mTmpRect, mCoveredFadePaint);
            }
        } else {
            result = super.drawChild(canvas, child, drawingTime);
        }

        canvas.restoreToCount(save);

        return result;
    }

    private class SwipeDragHelperCallback extends SwipeViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return !mIsUnableToDrag && child == mSwipeableView;

        }

        @Override
        public void onViewDragStateChanged(int state) {
            if (mSwipeViewDragHelper != null && mSwipeViewDragHelper.getViewDragState() == SwipeViewDragHelper.STATE_IDLE) {
                mSwipeOffset = computeSwipeOffset(mSwipeableView.getTop());
                //applyParallaxForCurrentSwipeOffset();

                if (mSwipeOffset == 1) {
                    updateObscuredViewVisibility();
                    setPanelStateInternal(SwipeUpPanelLayout.SwipePanelState.EXPANDED);
                } else if (mSwipeOffset == 0) {
                    setPanelStateInternal(SwipeUpPanelLayout.SwipePanelState.COLLAPSED);
                } else if (mSwipeOffset < 0) {
                    setPanelStateInternal(SwipeUpPanelLayout.SwipePanelState.HIDDEN);
                    mSwipeableView.setVisibility(View.INVISIBLE);
                } else {
                    updateObscuredViewVisibility();
                    setPanelStateInternal(SwipeUpPanelLayout.SwipePanelState.ANCHORED);
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

            // direction is always positive if we are sliding in the expanded direction
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

            if (mSwipeViewDragHelper != null) {
                mSwipeViewDragHelper.settleCapturedViewAt(releasedChild.getLeft(), target);
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


    private float computeSwipeOffset(int topPosition) {
        // Compute the panel top position if the panel is collapsed (offset 0)
        final int topBoundCollapsed = computePanelTopPosition(0);

        // Determine the new swipe offset based on the collapsed top position and the new required
        // top position
        return (mIsSwipingUp
                ? (float) (topBoundCollapsed - topPosition) / mSwipeRange
                : (float) (topPosition - topBoundCollapsed) / mSwipeRange);
    }

    private int computePanelTopPosition(float swipeOffset) {
        int slidingViewHeight = mSwipeableView != null ? mSwipeableView.getMeasuredHeight() : 0;
        int swipePixelOffset = (int) (swipeOffset * mSwipeRange);
        // Compute the top of the panel if its collapsed
        return mIsSwipingUp
                ? getMeasuredHeight() - getPaddingBottom() - mSwipePanelHeight - swipePixelOffset
                : getPaddingTop() - slidingViewHeight + mSwipePanelHeight + swipePixelOffset;
    }

    private void setPanelStateInternal(SwipeUpPanelLayout.SwipePanelState state) {
        if (mSwipeState == state) return;
        SwipeUpPanelLayout.SwipePanelState oldState = mSwipeState;
        mSwipeState = state;
        dispatchOnPanelStateChanged(this, oldState, state);
    }

    void dispatchOnPanelStateChanged(View panel, SwipeUpPanelLayout.SwipePanelState previousState, SwipeUpPanelLayout.SwipePanelState newState) {
        synchronized (mSwipePanelListeners) {
            for (SwipePanelListener l : mSwipePanelListeners) {
                l.onPanelStateChanged(panel, previousState, newState);
            }
        }
        sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
    }

//    @SuppressLint("NewApi")
//    private void applyParallaxForCurrentSwipeOffset() {
//        if (mSwipeParallaxOffset > 0) {
//            int mainViewOffset = getCurrentParallaxOffset();
//            //ViewCompat.setTranslationY(mMainView, mainViewOffset);
//            mSwipeMainView.setTranslationY(mainViewOffset);
//        }
//    }

//    public int getCurrentParallaxOffset() {
//        // Clamp swipe offset at zero for parallax computation;
//        int offset = (int) (mSwipeParallaxOffset * Math.max(mSwipeOffset, 0));
//        return mIsSwipingUp ? -offset : offset;
//    }


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

    boolean smoothSwipeTo(float swipeOffset, int velocity) {
        if (!isEnabled() || mSwipeableView == null) {
            // Nothing to do.
            return false;
        }

        int panelTop = computePanelTopPosition(swipeOffset);

        if (mSwipeViewDragHelper.smoothSwipeViewTo(mSwipeableView, mSwipeableView.getLeft(), panelTop)) {
            setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    void setAllChildrenVisible() {
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == INVISIBLE) {
                child.setVisibility(VISIBLE);
            }
        }
    }

    private SwipeUpPanelLayout.SwipePanelState mLastNotDraggingSwipeState = DEFAULT_SWIPE_STATE;

    private void onPanelDragged(int newTop) {
        if (mSwipeState != SwipeUpPanelLayout.SwipePanelState.DRAGGING) {
            mLastNotDraggingSwipeState = mSwipeState;
        }
        setPanelStateInternal(SwipeUpPanelLayout.SwipePanelState.DRAGGING);
        // Recompute the swipe offset based on the new top position
        mSwipeOffset = computeSwipeOffset(newTop);
        //applyParallaxForCurrentSwipeOffset();

        // Dispatch the swipe event
        dispatchOnPanelSwipe(mSwipeableView);
        // If the swipe offset is negative, and overlay is not on, we need to increase the
        // height of the main content
        SwipeUpPanelLayout.LayoutParams lp = (SwipeUpPanelLayout.LayoutParams) mSwipeMainView.getLayoutParams();
        int defaultHeight = getHeight() - getPaddingBottom() - getPaddingTop() - mSwipePanelHeight;

        if (mSwipeOffset <= 0 && !mOverlayContent) {
            // expand the main view
            lp.height = mIsSwipingUp ? (newTop - getPaddingBottom()) : (getHeight() - getPaddingBottom() - mSwipeableView.getMeasuredHeight() - newTop);
            if (lp.height == defaultHeight) {
                lp.height = SwipeUpPanelLayout.LayoutParams.MATCH_PARENT;
            }
            mSwipeMainView.requestLayout();
        } else if (lp.height != SwipeUpPanelLayout.LayoutParams.MATCH_PARENT && !mOverlayContent) {
            lp.height = SwipeUpPanelLayout.LayoutParams.MATCH_PARENT;
            mSwipeMainView.requestLayout();
        }
    }

    private static boolean hasOpaqueBackground(View v) {
        final Drawable bg = v.getBackground();
        return bg != null && bg.getOpacity() == PixelFormat.OPAQUE;
    }

    void dispatchOnPanelSwipe(View panel) {
        synchronized (mSwipePanelListeners) {
            for (SwipePanelListener l : mSwipePanelListeners) {
                l.onPanelSwipe(panel, mSwipeOffset);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();

        if (!isEnabled() || !isTouchEnabled() || (mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)) {
            mSwipeViewDragHelper.abort();
            return super.dispatchTouchEvent(ev);
        }

        final float x = ev.getX();
        final float y = ev.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            mIsScrollableViewHandlingTouch = false;
            mPrevMotionX = x;
            mPrevMotionY = y;
        } else if (action == MotionEvent.ACTION_MOVE) {
            float dx = x - mPrevMotionX;
            float dy = y - mPrevMotionY;
            mPrevMotionX = x;
            mPrevMotionY = y;

            if (Math.abs(dx) > Math.abs(dy)) {
                // Scrolling horizontally, so ignore
                return super.dispatchTouchEvent(ev);
            }

            // If the scroll view isn't under the touch, pass the
            // event along to the dragView.
            if (!isViewUnder(mScrollableView, (int) mInitialMotionX, (int) mInitialMotionY)) {
                return super.dispatchTouchEvent(ev);
            }

            // Which direction (up or down) is the drag moving?
            if (dy * (mIsSwipingUp ? 1 : -1) > 0) { // Collapsing
                // Is the child less than fully scrolled?
                // Then let the child handle it.
                if (mScrollableViewHelper.getScrollableViewScrollPosition(mScrollableView, mIsSwipingUp) > 0) {
                    mIsScrollableViewHandlingTouch = true;
                    return super.dispatchTouchEvent(ev);
                }

                // Was the child handling the touch previously?
                // Then we need to rejigger things so that the
                // drag panel gets a proper down event.
                if (mIsScrollableViewHandlingTouch) {
                    // Send an 'UP' event to the child.
                    MotionEvent up = MotionEvent.obtain(ev);
                    up.setAction(MotionEvent.ACTION_CANCEL);
                    super.dispatchTouchEvent(up);
                    up.recycle();

                    // Send a 'DOWN' event to the panel. (We'll cheat
                    // and hijack this one)
                    ev.setAction(MotionEvent.ACTION_DOWN);
                }

                mIsScrollableViewHandlingTouch = false;
                return this.onTouchEvent(ev);
            } else if (dy * (mIsSwipingUp ? 1 : -1) < 0) { // Expanding
                // Is the panel less than fully expanded?
                // Then we'll handle the drag here.
                if (mSwipeOffset < 1.0f) {
                    mIsScrollableViewHandlingTouch = false;
                    return this.onTouchEvent(ev);
                }

                // Was the panel handling the touch previously?
                // Then we need to rejigger things so that the
                // child gets a proper down event.
                if (!mIsScrollableViewHandlingTouch && mSwipeViewDragHelper.isDragging()) {
                    mSwipeViewDragHelper.cancel();
                    ev.setAction(MotionEvent.ACTION_DOWN);
                }

                mIsScrollableViewHandlingTouch = true;
                return super.dispatchTouchEvent(ev);
            }
        } else if (action == MotionEvent.ACTION_UP) {
            // If the scrollable view was handling the touch and we receive an up
            // we want to clear any previous dragging state so we don't intercept a touch stream accidentally
            if (mIsScrollableViewHandlingTouch) {
                mSwipeViewDragHelper.setDragState(ViewDragHelper.STATE_IDLE);
            }
        }

        // In all other cases, just let the default behavior take over.
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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isEnabled() || !isTouchEnabled()) {
            return super.onTouchEvent(ev);
        }
        try {
            mSwipeViewDragHelper.processTouchEvent(ev);
            return true;
        } catch (Exception ex) {
            // Ignore the pointer out of range exception
            return false;
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // If the scrollable view is handling touch, neonMeasurever intercept
        if (mIsScrollableViewHandlingTouch || !isTouchEnabled()) {
            mSwipeViewDragHelper.abort();
            return false;
        }

        final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();
        final float adx = Math.abs(x - mInitialMotionX);
        final float ady = Math.abs(y - mInitialMotionY);
        final int dragSlop = mSwipeViewDragHelper.getTouchSlop();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mIsUnableToDrag = false;
                mInitialMotionX = x;
                mInitialMotionY = y;
                if (!isViewUnder(mDragView, (int) x, (int) y)) {
                    mSwipeViewDragHelper.cancel();
                    mIsUnableToDrag = true;
                    return false;
                }

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (ady > dragSlop && adx > ady) {
                    mSwipeViewDragHelper.cancel();
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
                if (mSwipeViewDragHelper.isDragging()) {
                    mSwipeViewDragHelper.processTouchEvent(ev);
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
        return mSwipeViewDragHelper.shouldInterceptTouchEvent(ev);
    }

}
