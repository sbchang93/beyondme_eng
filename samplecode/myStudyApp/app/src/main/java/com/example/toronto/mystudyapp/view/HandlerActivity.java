package com.example.toronto.mystudyapp.view;

// Reference Homepage URL (참조 홈페이지 링크)
// http://biig.tistory.com/64?category=562387

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

public class HandlerActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);


        //--------------------------------------------------------------

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                Toast.makeText(HandlerActivity.this, "handle.post(callback)  : " + index, Toast.LENGTH_SHORT).show();
                index++;
            }
        };

        handle.post(callback);


        //--------------------------------------------------------------
        // 1초 뒤에 실행
        Handler delayedHandle = new Handler();
        Runnable delayedCallback = new Runnable() {
            public void run() {
                Toast.makeText(HandlerActivity.this, "delayedHhandle.postDelayed(callback, 10000) : " + index, Toast.LENGTH_LONG).show();
                index++;
            }
        };

        delayedHandle.postDelayed(delayedCallback, 10000);


        //--------------------------------------------------------------

        Handler emptyMsgHandle = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what == 0x0001){
                    Toast.makeText(HandlerActivity.this, "msgHandle.sendEmptyMessage(0x0001) : " + index, Toast.LENGTH_SHORT).show();
                    index++;
                }else if(msg.what == 1) {
                    index++;
                }
            }
        };

        emptyMsgHandle.sendEmptyMessage(0x0001);


        //--------------------------------------------------------------
        Handler msgHandle = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what == 5){
                    Toast.makeText(HandlerActivity.this, "msg.what = 5 : " + index, Toast.LENGTH_SHORT).show();
                    index++;
                }

                if(msg.obj == null){
                    Toast.makeText(HandlerActivity.this, "msg.obj = new String(\"Hi ~~\") : " + index, Toast.LENGTH_SHORT).show();
                    index++;
                }

                if(msg.arg1 == 2222){
                    Toast.makeText(HandlerActivity.this, "msg.arg1 = 2222 : " + index, Toast.LENGTH_SHORT).show();
                    index++;
                }

                if(msg.arg2 == 3333){
                    Toast.makeText(HandlerActivity.this, "msg.arg2 = 3333 : " + index, Toast.LENGTH_SHORT).show();
                    index++;
                }

                Bundle bundle = msg.getData();

                if( msg.getData() != null ){

                    String myTestKey = bundle.getString("Test_key");
                    if(myTestKey != null) {
                        Toast.makeText(HandlerActivity.this, "bundle.putString(\"Test_key\", \"Hello !!!\"): " + index, Toast.LENGTH_SHORT).show();
                        index++;
                    }
                }
            }
        };

        Message msg = msgHandle.obtainMessage();

        msg.what = 5;
        msg.obj = (Object) (HandlerActivity.this);
        msg.obj = new String("Hi ~~");
        msg.arg1 = 2222;
        msg.arg2 = 3333;

        Bundle bundle = new Bundle();
        bundle.putString("Test_key", "Hello !!!");
        msg.setData(bundle);

        msgHandle.sendMessage(msg);



    }
}
