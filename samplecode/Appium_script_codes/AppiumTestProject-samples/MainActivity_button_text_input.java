package com.example.toronto.helloworld02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener  {

    private Button button_01;
    private TextView textView_01;
    private int num = 1;

    private Button button_02;
    private TextView textView_02;
    private int num_02 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_01 = (TextView) findViewById(R.id.tv_01);
        button_01 = (Button) findViewById(R.id.btn_01);
        button_01.setOnClickListener(this);

        textView_02 = (TextView) findViewById(R.id.tv_02);
        button_02 = (Button) findViewById(R.id.btn_02);
        button_02.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_01:
                textView_01.setText("HelloWorld : "+num);  //기존 텍스트를 얻어와서(tv1.getText()) 뒤에 +를 추가합니다.
                num++;
                break;
            case R.id.btn_02:
                textView_02.setText("Next : "+num_02);  //기존 텍스트를 얻어와서(tv1.getText()) 뒤에 +를 추가합니다.
                num_02 += 10;
                break;
        }
    }
}
