package com.example.swipeup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.swipeup.R;

// Reference Homepage : https://ddolcat.tistory.com/543
// Reference Homepage : https://onlyfor-me-blog.tistory.com/122
// Reference Homepage : https://velog.io/@dustndus8/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9CAndroid-%EC%9E%90%EB%B0%94-%EC%BD%94%ED%8B%80%EB%A6%B0-%ED%98%BC%EC%9A%A9%ED%95%B4%EC%84%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0

public class HelloJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        TextView textTV = findViewById(R.id.textView1);
        Hello hello = new Hello();
        hello.test();
        textTV.setText(hello.formatMessage("Android code with Java and Kotlin"));
    }
}
