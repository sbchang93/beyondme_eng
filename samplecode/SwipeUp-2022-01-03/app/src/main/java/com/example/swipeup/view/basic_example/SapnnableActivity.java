package com.example.swipeup.view.basic_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipeup.R;

// Reference Homepage : https://m.blog.naver.com/manhdh/120137532414
// Reference Homepage : https://developer.android.com/guide/topics/text/spans?hl=ko#java
public class SapnnableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sapnnable);

        TextView textView1 = findViewById(R.id.textView1);
        SpannableString sText = new SpannableString(textView1.getText().toString());
        sText.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, 0);
        sText.setSpan(new StyleSpan(Typeface.BOLD), 6, 10, 0);
        sText.setSpan(new BackgroundColorSpan(Color.YELLOW), 11, 15, 0);
        textView1.setText(sText);

        //"chocolates-Life is a box of chocolates."
        // android:bufferType="spannable"  사용
        TextView tv2 = findViewById(R.id.textView2);
        Spannable span2 = (Spannable) tv2.getText();
        span2.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv2.setText(span2);

        //"chocolates-Life is a box of chocolates."
        // android:bufferType="spannable"  사용
        TextView tv3 = findViewById(R.id.textView3);
        Spannable span3 = (Spannable) tv3.getText();
        span3.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE); // Not working

        //"chocolates-Life is a box of chocolates."
        // android:bufferType="spannable"  사용
        TextView tv4 = findViewById(R.id.textView4);
        Spannable span4 = (Spannable) tv4.getText();
        span4.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE); // Not working

        //"chocolates-Life is a box of chocolates."
        // android:bufferType="spannable"  사용
        TextView tv5 = findViewById(R.id.textView5);
        Spannable span5 = (Spannable) tv5.getText();
        span5.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // Not working



        TextView tv6 = findViewById(R.id.textView6);
        SpannableStringBuilder spannable = new SpannableStringBuilder("Text is spantastic!");
        spannable.setSpan(
                new ForegroundColorSpan(Color.RED),
                8, // start
                12, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        tv6.setText(spannable);



        // android:bufferType="spannable"  사용
        TextView tv7 = findViewById(R.id.textView7);
        Spannable span7 = (Spannable) tv7.getText();
        span7.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(SapnnableActivity.this, "ClickableSpan ~ !", Toast.LENGTH_SHORT).show();
            }
        }, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv7.setMovementMethod(LinkMovementMethod.getInstance()); // Supporting Click Event.


        // android:bufferType="spannable"  사용
        TextView tv8 = findViewById(R.id.textView8);
        Spannable span8 = (Spannable) tv8.getText();
        String url = "http://gogole.com";
        span8.setSpan(new URLSpan(url), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv8.setMovementMethod(LinkMovementMethod.getInstance()); // Supporting Click Event.


    }
}