package com.example.toronto.mystudyapp.view;

// 참조 홈페이지
// http://biig.tistory.com/44?category=562387

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

import java.util.HashMap;
import java.util.Iterator;

public class HashMapActivity extends AppCompatActivity {

    Button btn;
    int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hash_map);

        HashMap<Integer, String> map = new HashMap <Integer, String> ();

        map.put(1, "치킨");
        map.put(2, "삼겹살");
        map.put(3, "소고기");

        Iterator<Integer> iter = map.keySet().iterator();
        while(iter.hasNext()) { // iter 안에 "내용"이 있는가 체크
            int key = iter.next(); // key id를 알아오기
            String value = map.get(key);
            Log.d("test", "Key: " + key  + ", value: " + value);

            if(key == 1) { // 테스트용으로 첫번째 하나만 띄움
                Toast.makeText(this, "Key: " + key + ", value: " + value, Toast.LENGTH_SHORT).show();
            }

            if(map.containsKey(key)) {
                Log.d("test", ""+ key + " key exists. -  ");
            }
        }


        //--------------------------------------------------------------

        btn = (Button) findViewById(R.id.btn);

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                Toast.makeText(HashMapActivity.this, "handle.post(callback) : " + index, Toast.LENGTH_SHORT).show();
                index++;
            }
        };

        handle.post(callback);

        // 1초 뒤에 실행
        handle.postDelayed(callback, 1000);



        //--------------------------------------------------------------

        Handler msgHandle = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what == 0x0001){
                    Toast.makeText(HashMapActivity.this, "msgHandle.sendEmptyMessage(0x0001) : " + index, Toast.LENGTH_SHORT).show();
                    index++;
                }else if(msg.what == 1) {
                    index++;
                }
            }
        };

        msgHandle.sendEmptyMessage(0x0001);

        //--------------------------------------------------------------



    }

    public void onClick(View v){
        switch (v.getId()){
            case  R.id.btn:
                btn.setText("버튼1");
                break;
            default:
                break;
        }
    }

}
