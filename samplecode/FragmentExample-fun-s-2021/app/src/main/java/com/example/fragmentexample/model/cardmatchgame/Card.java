package com.example.fragmentexample.model.cardmatchgame;

public class Card {
    public static final int CARD_SHOW = 0;
    public static final int CARD_CLOSE = 1;
    public static final int CARD_PLAYEROPEN = 2;
    public static final int CARD_MATCHED = 3;

    public static final int IMG_RED = 1;
    public static final int IMG_GREEN = 2;
    public static final int IMG_BLUE = 3;

    public int mState;
    public int mColor;

    public Card() {
        mState = CARD_SHOW;
        mColor = -1;
    }

    public Card(int color) {
        mState = CARD_SHOW;
        mColor = color;
    }
}
