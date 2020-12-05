package com.example.toronto.mystudyapp;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.toronto.mystudyapp.view.AudioActivity;
import com.example.toronto.mystudyapp.view.CallOtherActivity;
import com.example.toronto.mystudyapp.view.DataBindingTest01Activity;
import com.example.toronto.mystudyapp.view.Dialog2Activity;
import com.example.toronto.mystudyapp.view.Dialog3Activity;
import com.example.toronto.mystudyapp.view.Dialog4Activity;
import com.example.toronto.mystudyapp.view.Dialog5Activity;
import com.example.toronto.mystudyapp.view.DialogActivity;
import com.example.toronto.mystudyapp.view.DialogTest01Activity;
import com.example.toronto.mystudyapp.view.DialogTest02Activity;
import com.example.toronto.mystudyapp.view.DialogTest03Activity;
import com.example.toronto.mystudyapp.view.DialogTest04Activity;
import com.example.toronto.mystudyapp.view.DrawActivity;
import com.example.toronto.mystudyapp.view.DrawTestActivity;
import com.example.toronto.mystudyapp.view.HandlerActivity;
import com.example.toronto.mystudyapp.view.HashMapActivity;
import com.example.toronto.mystudyapp.view.ImageActivity;
import com.example.toronto.mystudyapp.view.ListView01Activity;
import com.example.toronto.mystudyapp.view.ListView02CustomActivity;
import com.example.toronto.mystudyapp.view.ListView2Activity;
import com.example.toronto.mystudyapp.view.ListView3Activity;
import com.example.toronto.mystudyapp.view.ListViewActivity;
import com.example.toronto.mystudyapp.view.LiveDataActivity;
import com.example.toronto.mystudyapp.view.LocationManagerActivity;
import com.example.toronto.mystudyapp.view.NameActivityForViewModel;
import com.example.toronto.mystudyapp.view.ObserverActivity;
import com.example.toronto.mystudyapp.view.ObserverMainActivity;
import com.example.toronto.mystudyapp.view.RecyclerViewActivity;
import com.example.toronto.mystudyapp.view.ReflectionActivity;
import com.example.toronto.mystudyapp.view.SaveState02Activity;
import com.example.toronto.mystudyapp.view.SaveState03Activity;
import com.example.toronto.mystudyapp.view.Test01Activity;
import com.example.toronto.mystudyapp.view.Test02_MyViewActivity;
import com.example.toronto.mystudyapp.view.TextViewActivity;
import com.example.toronto.mystudyapp.view.UserActivity;
import com.example.toronto.mystudyapp.view.VideoActivity;
import com.example.toronto.mystudyapp.view.ViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ListView mListView;
    private ArrayList<Map<String, Object>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰
        mListView = (ListView) findViewById(R.id.list_view);

        // 데이터
        mDataList = new ArrayList<>();

        // addItem (title, description, class) 함수 호출해서 항목을 mDataList에 추가함
        addItem("CallOtherActivity 실행", "연습(설명내용)", CallOtherActivity.class);
        addItem("HashMapActivity 실행", "연습(설명내용)", HashMapActivity.class);
        addItem("ViewActivity 실행", "연습(설명내용)", ViewActivity.class);
        addItem("HandlerActivity 실행", "연습(설명내용)", HandlerActivity.class);
        addItem("LocationManagerActivity 실행", "연습(설명내용)", LocationManagerActivity.class);
        addItem("TextViewActivity 실행", "연습(설명내용)", TextViewActivity.class);
        addItem("AudioActivity 실행", "연습(설명내용)", AudioActivity.class);
        addItem("DialogActivity 실행", "연습(설명내용)", DialogActivity.class);
        addItem("Dialog2Activity 실행", "연습(설명내용)", Dialog2Activity.class);
        addItem("Dialog3Activity 실행", "연습(설명내용)", Dialog3Activity.class);
        addItem("Dialog4Activity 실행", "연습(설명내용)", Dialog4Activity.class);
        addItem("Dialog5Activity 실행", "연습(설명내용)", Dialog5Activity.class);
        addItem("Test01Activity 실행", "연습(설명내용)", Test01Activity.class);
        addItem("Test02_MyViewActivity 실행", "연습(설명내용)", Test02_MyViewActivity.class);
        addItem("SaveState02Activity 실행", "연습(설명내용)", SaveState02Activity.class);
        addItem("SaveState03Activity 실행", "연습(설명내용)", SaveState03Activity.class);
        addItem("DialogTest01Activity 실행", "연습(설명내용)", DialogTest01Activity.class);
        addItem("DialogTest02Activity 실행", "연습(설명내용)", DialogTest02Activity.class);
        addItem("DialogTest03Activity 실행", "연습(설명내용)", DialogTest03Activity.class);
        addItem("DialogTest04Activity 실행", "연습(설명내용)", DialogTest04Activity.class);
        addItem("DataBindingTest01Activity 실행", "연습(설명내용)", DataBindingTest01Activity.class);
        addItem("NameActivityForViewModel 실행", "연습(설명내용)", NameActivityForViewModel.class);

        MyAdapter adapter = new MyAdapter(mDataList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener((parent, view, position, id) -> {
            Map<String, Object> map = (Map<String, Object>) parent.getAdapter().getItem(position);
            Intent intent = (Intent) map.get("intent");
            startActivity(intent);
        });

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                //Toast.makeText(MainActivity.this, "handler in MainActivity" , Toast.LENGTH_SHORT).show();
            }
        };
        handle.post(callback);

        findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
        findViewById(R.id.dbSamples).setOnClickListener(mClickListener);
        findViewById(R.id.observalbeSamples).setOnClickListener(mClickListener);
        findViewById(R.id.listMenuSamples).setOnClickListener(mClickListener);
        findViewById(R.id.drawSamples).setOnClickListener(mClickListener);

        testCode(100);
    }

    private void addItem(String title, String desc, Class cls) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("desc", desc);
        map.put("intent", new Intent(this, cls));
        mDataList.add(map);
    }

    private static class MyAdapter extends BaseAdapter {
        private final List<Map<String, Object>> mData;

        public MyAdapter(ArrayList<Map<String, Object>> mDataList) {
            mData = mDataList;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                 holder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_main_listview, parent, false);

                holder.titleText = (TextView) convertView.findViewById(R.id.title_text);
                holder.descriptionText = (TextView) convertView.findViewById(R.id.description_text);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Map<String, Object> data = mData.get(position);

            holder.titleText.setText(data.get("title").toString());
            holder.descriptionText.setText(data.get("desc").toString());

            return convertView;
        }

        static class ViewHolder {
            TextView titleText;
            TextView descriptionText;
        }
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.normalSamples:
                    launchSampleActivities();
                    break;
                case R.id.dbSamples:
                    launchRoomDBSamples();
                    break;
                case R.id.observalbeSamples:
                    launchObservableSamples();
                    break;
                case R.id.listMenuSamples:
                    listMenusSamples();
                    break;
                case R.id.drawSamples:
                    drawSamplesSamples();
                    break;

                // Remember ===> findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
            }
        }
    };


    private void launchSampleActivities() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    //startActivity(new Intent(this, LiveDataActivity.class));
                    //startActivity(new Intent(this, ObserverActivity.class));

                    startActivity(new Intent(this, CallOtherActivity.class));
                    startActivity(new Intent(this, HashMapActivity.class));
                    startActivity(new Intent(this, ViewActivity.class));
                    startActivity(new Intent(this, HandlerActivity.class));
                    startActivity(new Intent(this, LocationManagerActivity.class));
                    startActivity(new Intent(this, TextViewActivity.class));
                    startActivity(new Intent(this, AudioActivity.class));
                    startActivity(new Intent(this, DialogActivity.class));
                    startActivity(new Intent(this, Dialog2Activity.class));
                    startActivity(new Intent(this, Dialog3Activity.class));
                    startActivity(new Intent(this, Dialog4Activity.class));
                    startActivity(new Intent(this, Dialog5Activity.class));
                    startActivity(new Intent(this, Test01Activity.class));
                    startActivity(new Intent(this, Test02_MyViewActivity.class));
                    startActivity(new Intent(this, SaveState02Activity.class));
                    startActivity(new Intent(this, SaveState03Activity.class));
                    startActivity(new Intent(this, DialogTest01Activity.class));
                    startActivity(new Intent(this, DialogTest02Activity.class));
                    startActivity(new Intent(this, DialogTest03Activity.class));
                    startActivity(new Intent(this, DialogTest04Activity.class));
                    startActivity(new Intent(this, DataBindingTest01Activity.class));
                    startActivity(new Intent(this, NameActivityForViewModel.class));
                    finish();
                });
    }


    private void launchRoomDBSamples() {
        Observable.just("Room DB")
                .delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, UserActivity.class));
                    finish();
                });
    }

    private void launchObservableSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    if (true) {
                        startActivity(new Intent(this, RecyclerViewActivity.class));
                    } else {
                        startActivity(new Intent(this, ReflectionActivity.class));
                    }
                    startActivity(new Intent(this, ObserverMainActivity.class));

//                    startActivity(new Intent(this, LiveDataActivity.class));
//                    startActivity(new Intent(this, ObserverActivity.class));
                    finish();
                });
    }

    private void listMenusSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, ListView3Activity.class));
                    startActivity(new Intent(this, RecyclerViewActivity.class));
                    startActivity(new Intent(this, ListViewActivity.class));
                    startActivity(new Intent(this, ListView2Activity.class));
                    startActivity(new Intent(this, ListView02CustomActivity.class));
                    startActivity(new Intent(this, ListView01Activity.class));

                    finish();
                });
    }

    private void drawSamplesSamples() {
        Observable.just("")
                .delay(1, TimeUnit.MILLISECONDS)
                //.delay(1, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    startActivity(new Intent(this, DrawActivity.class));
                    startActivity(new Intent(this, ImageActivity.class));
                    startActivity(new Intent(this, VideoActivity.class));
                    startActivity(new Intent(this, DrawTestActivity.class));
                    startActivity(new Intent(this, ReflectionActivity.class));

                    finish();
                });
    }

    public void testCode(int testCode) {
        AlarmManager mAlarmManager;

        // 알람 매니저 인스턴스 얻기
        mAlarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);

        // 현재 시간으로 타임 피커를 설정
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(this, (TimePickerDialog.OnTimeSetListener) this, hour, minute,
                DateFormat.is24HourFormat(this));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

}


