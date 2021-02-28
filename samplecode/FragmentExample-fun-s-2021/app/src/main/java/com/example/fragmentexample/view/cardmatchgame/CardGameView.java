package com.example.fragmentexample.view.cardmatchgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.example.fragmentexample.R;
import com.example.fragmentexample.model.cardmatchgame.Card;

public class CardGameView extends View {
    Bitmap mBackgroundImage;
    Bitmap mBacksideCard;
    Bitmap mCardRed;
    Bitmap mCardBlue;
    Bitmap mCardGreen;
    Card mShuffle[][];

    public CardGameView(Context context) {
        super(context);
        mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
        mBacksideCard = BitmapFactory.decodeResource(getResources(), R.drawable.backside, null);
        mCardRed = BitmapFactory.decodeResource(getResources(), R.drawable.front_red, null);
        mCardBlue = BitmapFactory.decodeResource(getResources(), R.drawable.front_blue, null);
        mCardGreen = BitmapFactory.decodeResource(getResources(), R.drawable.front_green, null);

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
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackgroundImage, 0, 0, null);
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                if (mShuffle[x][y].mColor == Card.IMG_RED)
                    canvas.drawBitmap(mCardRed, 80 + x * 150, 400 + y * 200, null);
                else if (mShuffle[x][y].mColor == Card.IMG_GREEN)
                    canvas.drawBitmap(mCardGreen, 80 + x * 150, 400 + y * 200, null);
                else if (mShuffle[x][y].mColor == Card.IMG_BLUE)
                    canvas.drawBitmap(mCardBlue, 80 + x * 150, 400 + y * 200, null);
            }
        }

        //canvas.drawBitmap(mBacksideCard, 80+x*150, 400+y*200, null);
    }


}
