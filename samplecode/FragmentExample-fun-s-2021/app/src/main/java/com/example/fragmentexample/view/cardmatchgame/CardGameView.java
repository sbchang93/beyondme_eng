package com.example.fragmentexample.view.cardmatchgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.example.fragmentexample.R;

public class CardGameView extends View {
    Bitmap mBackgroundImage;
    Bitmap mBacksideCard;

    public CardGameView(Context context) {
        super(context);
        mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
        mBacksideCard = BitmapFactory.decodeResource(getResources(), R.drawable.backside, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBackgroundImage, 0, 0, null);
        for(int y=0; y<2; y++)
            for(int x=0; x<3; x++)
                canvas.drawBitmap(mBacksideCard, 80+x*150, 400+y*200, null);
    }


}
