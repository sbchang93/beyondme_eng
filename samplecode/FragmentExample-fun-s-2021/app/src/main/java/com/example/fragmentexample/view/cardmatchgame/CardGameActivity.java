package com.example.fragmentexample.view.cardmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentexample.view.image_example.ImageView;

public class CardGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CardGameView(this));
    }
}