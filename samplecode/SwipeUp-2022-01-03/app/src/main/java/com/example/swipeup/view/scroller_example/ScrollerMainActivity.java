package com.example.swipeup.view.scroller_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.swipeup.R;

// Reference Homepage URL : https://blog.naver.com/PostView.nhn?isHttpsRedirect=true&blogId=huj277&logNo=70163018748

public class ScrollerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SlidingView sv = new SlidingView(this);
        View v1 = View.inflate(this, R.layout.t1, null);
        View v2 = View.inflate(this, R.layout.t2, null);
        sv.addView(v1);
        sv.addView(v2);
        setContentView(sv);

    }

}