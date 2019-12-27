package com.example.fragmentexample.view;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.fragmentexample.R;

//Homepage : https://bitsoul.tistory.com/147 [Happy Programmer~]
public class MyService01 extends Service {
    public MyService01() {
    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    MediaPlayer mp; // 음악 재생을 위한 객체

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // 서비스에서 가장 먼저 호출됨(최초에 한번만)
        Log.d("test", "서비스의 onCreate");
        mp = MediaPlayer.create(this, R.raw.test_01);
        mp.setLooping(false); // 반복재생
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스가 호출될 때마다 실행
        Log.d("test", "서비스의 onStartCommand");
        mp.start(); // 노래 시작
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스가 종료될 때 실행
        mp.stop(); // 음악 종료
        Log.d("test", "서비스의 onDestroy");
    }

}
