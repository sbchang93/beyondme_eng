package com.example.toronto.mystudyapp.view;

// 자료
// http://biig.tistory.com/44?category=562387

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button text_edit_bt;
    private Button color_edit_bt;
    private Button size_edit_bt;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        tv1 = (TextView) findViewById(R.id.tv1); //xml파일에서 설정한 뷰를 소스코드에서 사용하기 위해 ID로 연결하는 과정입니다.

        text_edit_bt = (Button) findViewById(R.id.text_edit_bt);
        color_edit_bt = (Button) findViewById(R.id.color_edit_bt);
        size_edit_bt = (Button) findViewById(R.id.size_edit_bt);

        text_edit_bt.setOnClickListener(this);
        color_edit_bt.setOnClickListener(this);
        size_edit_bt.setOnClickListener(this);

//		text_edit_bt.setOnClickListener(new View.OnClickListener() {  //이렇게 각각의 리스너를 등록해서 사용 할 수도 있습니다.
//			@Override
//			public void onClick(View v) {
//				tv1.setText(tv1.getText() + "+");
//			}
//		});
    }

    //온클릭을 오버라이드 하기 위해서는 클래스에 OnClickListener를 implements해야합니다. 11번째줄 참조.

    @Override

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_edit_bt :
                tv1.setText(tv1.getText() + "+");  //기존 텍스트를 얻어와서(tv1.getText()) 뒤에 +를 추가합니다.
 			    //tv1.setText("원하는 텍스트");
                break;
            case R.id.color_edit_bt :
                tv1.setTextColor(Color.BLUE);  //Color 클래스에 들어있는 BLUE 색상으로 변환합니다.
			    //tv1.setTextColor(Color.parseColor("#FF0000")); //직접 색상코드를 입력하여 원하는 색으로도 설정 가능합니다.
                break;
            case R.id.size_edit_bt :
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv1.getTextSize() + 1); //기존 텍스트사이즈를 얻어와서 + 1만큼 사이즈를 키웁니다.
			    //tv1.setTextSize(16); //원하는 고정수치(px)로도 텍스트 크기를 변경 가능합니다.
                break;
        }
    }

}
