package com.example.fragmentexample.view.keydown_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentexample.view.cardmatchgame.CardGameView;

public class KeyDownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new KeyDownView(this));
    }
}