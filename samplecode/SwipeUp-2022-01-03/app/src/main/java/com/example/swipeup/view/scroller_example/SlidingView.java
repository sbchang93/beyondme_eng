package com.example.swipeup.view.scroller_example;

// Reference Homepage URL : https://blog.naver.com/PostView.nhn?isHttpsRedirect=true&blogId=huj277&logNo=70163018748

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

import com.example.swipeup.R;

public class SlidingView extends ViewGroup {
    private static final String TAG = "SlidingView";

    private VelocityTracker mVelocityTracker = null;
    private static final int SNAP_VELOCITY = 100;
    private int mTouchSlop = 10;

    private Bitmap mWallpaper = null;
    private Paint mPaint = null;

    private Scroller mScroller = null;
    private PointF mLastPoint = null;
    private int mCurPage = 0;

    private int mCurTouchState;
    private static final int TOUCH_STATE_SCROLLING = 0;
    private static final int TOUCH_STATE_NORMAL = 1;

    private Toast mToast;

    public SlidingView(Context context) {
        super(context);
        init();
    }

    public SlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //mWallpaper = BitmapFactory.decodeResource(getResources(), R.drawable.background_black_1280x1024);
        mWallpaper = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_bg); // 배경화면 불러오기
        mPaint = new Paint();
        mScroller = new Scroller(getContext());
        mLastPoint = new PointF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout");
        for (int i = 0; i < getChildCount(); i++) {
            int child_left = getChildAt(i).getMeasuredWidth() * i;
            getChildAt(i).layout(child_left, t, child_left + getChildAt(i).getMeasuredWidth(), getChildAt(i).getMeasuredHeight());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawBitmap(mWallpaper, 0, 0, mPaint);  // 첫페이지만 배경 이미지 보여줄 때 사용
        for (int i = 0; i < getChildCount(); i++) {
            canvas.drawBitmap(mWallpaper, i * getChildAt(i).getWidth(), 0, mPaint);  // 다른 페이지에도 배경 이미지를 보이게 하고 싶은 경우에 사용
            drawChild(canvas, getChildAt(i), 100);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "event Action : " + event.getAction());

        if (mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();

        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                int x = (int) (event.getX() - mLastPoint.x);
                scrollBy(-x, 0);
                invalidate();
                mLastPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int v = (int) mVelocityTracker.getXVelocity();

                int gap = getScrollX() - mCurPage * getWidth();
                Log.d(TAG, "mVelocityTracker : " + v);
                int nextPage = mCurPage;

                // 드래그 속도가 SNAP_VELOCITY 보다 높거니 화면 반이상 드래그 했으면
                // 화면전환 할것이라고 nextPage 변수를 통해 저장.
                if ((v > SNAP_VELOCITY || gap < -getWidth() / 2) && mCurPage > 0) {
                    nextPage--;
                } else if ((v < -SNAP_VELOCITY || gap > getWidth() / 2) && mCurPage < getChildCount() - 1) {
                    nextPage++;
                }

                int move = 0;
                if (mCurPage != nextPage) { // 화면 전환 스크롤 계산
                    // 현재 스크롤 지점에서 화면전환을 위해 이동해야하는 지점과의 거리 계산
                    move = getChildAt(0).getWidth() * nextPage - getScrollX();
                } else { // 원래 화면 복귀 스크롤 계산
                    // 화면 전환 하지 않을것이며 원래 페이지로 돌아가기 위한 이동해야하는 거리 계산
                    move = getWidth() * mCurPage - getScrollX();
                }

                mScroller.startScroll(getScrollX(), 0, move, 0, Math.abs(move));

//                if (mToast != null) {
//                    mToast.setText("page : " + nextPage);
//                } else {
//                    mToast = Toast.makeText(getContext(), "page : " + nextPage, Toast.LENGTH_SHORT);
//                }
//                mToast.show();
                Toast.makeText(getContext(), "page : " + nextPage, Toast.LENGTH_SHORT).show();
                invalidate();
                mCurPage = nextPage;

                mCurTouchState = TOUCH_STATE_NORMAL;
                mVelocityTracker.recycle();
                mVelocityTracker = null;
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent : " + ev.getAction());
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCurTouchState = mScroller.isFinished() ? TOUCH_STATE_NORMAL : TOUCH_STATE_SCROLLING;
                mLastPoint.set(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                int move_x = Math.abs(x - (int) mLastPoint.x);
                if (move_x > mTouchSlop) {
                    mCurTouchState = TOUCH_STATE_SCROLLING;
                    mLastPoint.set(x, y);
                }
                break;
        }

        return mCurTouchState == TOUCH_STATE_SCROLLING;
    }
}

