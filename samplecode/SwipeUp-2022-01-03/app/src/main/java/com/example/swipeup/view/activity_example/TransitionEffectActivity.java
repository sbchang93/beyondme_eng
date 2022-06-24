package com.example.swipeup.view.activity_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.swipeup.R;

//Reference Home URL : https://mine-it-record.tistory.com/235
//Reference Home URL : https://hijjang2.tistory.com/320

public class TransitionEffectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_effect);
    }


    public void btn1Click(View view) {
        Intent intent = new Intent(this, Test01Activity.class);
        intent.putExtra("test", "mine-it-record"); // Not used Parameter.
        startActivity(intent);
        overridePendingTransition(0, 0);  // Remove the Transition Effect in Activity
    }

    public void btn2Click(View view) {
        Intent intent = new Intent(this, Test02Activity.class);
        intent.putExtra("test", "mine-it-record"); // Not used Parameter.
        startActivity(intent);

        //If it's blocked, System Transition Effect will be applied.
        //overridePendingTransition(0,0);  // Remove the Transition Effect in Activity
    }

    public void btn3Click(View view) {
        Intent intent = new Intent(this, Test01Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    public void btn4Click(View view) {
        Intent intent = new Intent(this, Test02Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_with_left_movement, 0);
    }
}