package com.example.fragmentexample.view.cardmatchgame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.fragmentexample.R;
import com.example.fragmentexample.model.cardmatchgame.Card;
import com.example.fragmentexample.utils.DisplayUtil;

public class CardGameView extends View {
    public static final int STATE_READY = 0;
    public static final int STATE_GAME = 1;
    public static final int STATE_END = 2;
    private int mState = STATE_READY;

    Bitmap mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
    Bitmap mCardBackside = BitmapFactory.decodeResource(getResources(), R.drawable.backside, null);
    Bitmap mCardRed = BitmapFactory.decodeResource(getResources(), R.drawable.front_red, null);
    Bitmap mCardBlue = BitmapFactory.decodeResource(getResources(), R.drawable.front_blue, null);
    Bitmap mCardGreen = BitmapFactory.decodeResource(getResources(), R.drawable.front_green, null);
    Card mShuffle[][];

    int _0dp = DisplayUtil.dpToPx(getContext(), 0);
    int _20dp = DisplayUtil.dpToPx(getContext(), 20);
    int _35dp = DisplayUtil.dpToPx(getContext(), 35);
    int _40dp = DisplayUtil.dpToPx(getContext(), 40);
    int _57dp = DisplayUtil.dpToPx(getContext(), 57);
    int _90dp = DisplayUtil.dpToPx(getContext(), 90);
    int _130dp = DisplayUtil.dpToPx(getContext(), 130);
    int _150dp = DisplayUtil.dpToPx(getContext(), 150);

//    int _0dp = (int) DisplayUtil.convertDpToPixel(getContext(), 0);
//    int _20dp = (int) DisplayUtil.convertDpToPixel(getContext(), 20);
//    int _35dp = (int) DisplayUtil.convertDpToPixel(getContext(), 35);
//    int _40dp = (int) DisplayUtil.convertDpToPixel(getContext(), 40);
//    int _57dp = (int) DisplayUtil.convertDpToPixel(getContext(), 57);
//    int _90dp = (int) DisplayUtil.convertDpToPixel(getContext(), 90);
//    int _130dp = (int) DisplayUtil.convertDpToPixel(getContext(), 130);
//    int _150dp = (int) DisplayUtil.convertDpToPixel(getContext(), 150);

    private int mX;
    private int mY;

    public CardGameView(Context context) {
        super(context);
        mShuffle = new Card[3][2];

        setCardShuffle();
    }

    public void setCardShuffle() {
        mShuffle[0][0] = new Card(Card.IMG_RED);
        mShuffle[0][1] = new Card(Card.IMG_BLUE);
        mShuffle[1][0] = new Card(Card.IMG_GREEN);
        mShuffle[1][1] = new Card(Card.IMG_GREEN);
        mShuffle[2][0] = new Card(Card.IMG_BLUE);
        mShuffle[2][1] = new Card(Card.IMG_RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mState == STATE_READY) {
            mState = STATE_GAME;
        } else if (mState == STATE_GAME) {
            mX = (int) event.getX();
            mY = (int) event.getY();

        } else if (mState == STATE_END) {
            mState = STATE_READY;
        }

        invalidate();
        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackgroundImage, 0, 0, null);
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                if (mShuffle[x][y].mColor == Card.IMG_RED)
                    canvas.drawBitmap(mCardRed, _35dp + x * _90dp, _150dp + y * _130dp, null);
                else if (mShuffle[x][y].mColor == Card.IMG_GREEN)
                    canvas.drawBitmap(mCardGreen, _35dp + x * _90dp, _150dp + y * _130dp, null);
                else if (mShuffle[x][y].mColor == Card.IMG_BLUE)
                    canvas.drawBitmap(mCardBlue, _35dp + x * _90dp, _150dp + y * _130dp, null);
            }
        }

        Paint p = new Paint();
        p.setTextSize(_20dp);
        p.setColor(Color.BLUE);
        canvas.drawText("Event Position X:" + mX + ", Y:" + mY, _0dp, _20dp, p);

        int x_dp = (int) DisplayUtil.convertPixelsToDp(getContext(), mX);
        int y_dp = (int) DisplayUtil.convertPixelsToDp(getContext(), mY);
        canvas.drawText("x_dp:" + x_dp + ", y_dp:" + y_dp, _0dp, _20dp*2, p);
    }


}