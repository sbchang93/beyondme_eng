package com.example.fragmentexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentexample.view.DetailsActivity;
import com.example.fragmentexample.view.FragmentLayout;
import com.example.fragmentexample.view.ShowTextActivity;
import com.example.fragmentexample.view.ViewPagerActivity;
import com.example.fragmentexample.view.actionbar_example.ActionBarActivity;
import com.example.fragmentexample.view.cardmatchgame.CardGameActivity;
import com.example.fragmentexample.view.gesture_example.GestureActivity;
import com.example.fragmentexample.view.image_example.ImageExample;
import com.example.fragmentexample.view.keydown_example.KeyDownActivity;
import com.example.fragmentexample.view.popuup_window.PopupWindowActivity;
import com.example.fragmentexample.view.preference_02.PreferenceSampleActivity;
import com.example.fragmentexample.view.preference_screen.Preference01Activity;
import com.example.fragmentexample.view.roomdb.RoomDbActivity;
import com.example.fragmentexample.view.searchview.SearchViewActivity;
import com.example.fragmentexample.view.settings_example.SettingsActivity;
import com.example.fragmentexample.view.sliding_up_pannel.DemoActivity;
import com.example.fragmentexample.view.switch_example.SwitchActivity;
import com.example.fragmentexample.view.touchevent_example.TouchEventActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private ListView mListView;
    private ArrayList<Map<String, Object>> mDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listview);
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            Map<String, Object> map = (Map<String, Object>) parent.getAdapter().getItem(position);
            Intent intent = (Intent) map.get("intent");
            startActivity(intent);
        });
        MyAdapter adapter = new MyAdapter(mDataList);
        mListView.setAdapter(adapter);

        // addItem (title, desc, cls)

        addItem("SettingsActivity", "SettingsActivity Description", SettingsActivity.class);


        addItem("SlidingUpPanel DemoActivity", "SlidingUpPanel DemoActivity Description", DemoActivity.class);

        addItem("ActionBarActivity", "ActionBarActivity Description", ActionBarActivity.class);

        addItem("PreferenceActivity", "PreferenceActivity Description", Preference01Activity.class);
        addItem("PreferenceSampleActivity", "PreferenceSampleActivity Description", PreferenceSampleActivity.class);

        addItem("RoomDbActivity", "RoomDbActivity Description", RoomDbActivity.class);

        addItem("PopupWindowActivity", "PopupWindowActivity Description", PopupWindowActivity.class);
        addItem("ShowTextActivity", "ShowTextActivity Description", ShowTextActivity.class);
        addItem("ViewPagerActivity", "ViewPagerActivity Description", ViewPagerActivity.class);
        addItem("FragmentLayout", "FragmentLayout Description", FragmentLayout.class);
        addItem("DetailsActivity", "DetailsActivity Description", DetailsActivity.class);
        addItem("ImageExample", "ImageExample Description", ImageExample.class);
        addItem("CardGameActivity", "CardGameActivity Description", CardGameActivity.class);
        addItem("KeyDownActivity", "KeyDownActivity Description", KeyDownActivity.class);
        addItem("TouchEventActivity", "TouchEventActivity Description", TouchEventActivity.class);
        addItem("GestureActivity", "GestureActivity Description", GestureActivity.class);
        addItem("SearchViewActivity", "SearchViewActivity Description", SearchViewActivity.class);
        addItem("SwitchActivity", "SwitchActivity Description", SwitchActivity.class);
        addItem("ImageExample", "ImageExample Description", ImageExample.class);

//         text1 = findViewById(R.id.textView1);
//         checkPermission();
    }

    private void addItem(String title, String desc, Class cls) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("desc", desc);
        map.put("intent", new Intent(this, cls));
        mDataList.add(map);
    }

    public static class MyAdapter extends BaseAdapter {
        private final List<Map<String, Object>> mData;

        public MyAdapter(List<Map<String, Object>> data) {
            mData = data;
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
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myadapter, parent, false);

                holder = new ViewHolder();
                holder.title = convertView.findViewById(R.id.title_text);
                holder.description = convertView.findViewById(R.id.description_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Map<String, Object> twoLineMenu = (Map<String, Object>) getItem(position);
            holder.title.setText((String)twoLineMenu.get("title"));
            holder.description.setText((String)twoLineMenu.get("desc"));

            return convertView;
        }


        static class ViewHolder {
            TextView title;
            TextView description;
        }
    }

    TextView text1;
    String [] permission_list = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS
    };

    public void checkPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        for(String permission : permission_list) {
            int checked = checkCallingOrSelfPermission(permission);
            if(checked == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permission_list, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        text1.setText("");

        for(int i=0; i<grantResults.length; i++) {
            if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                text1.append(permissions[i] + " : Allowed\n");
            } else {
                text1.append(permissions[i] + " : Denied\n");
            }
        }
    }
}

//public class MainActivity extends AppCompatActivity {
//    private final String TAG = this.getClass().getSimpleName();
//    private HashMap<Integer, String> sampleNameMap = new HashMap();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Logger.d(TAG, "onCreate ... ");
//
//        Observable.just("")
//                .delay(1, TimeUnit.SECONDS)
//                .subscribe(s -> {
//                    // runOnUiThread : https://itmining.tistory.com/6
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            Toast.makeText(MainActivity.this, "An Observable run once in MainActivity", Toast.LENGTH_LONG).show();
//                        }
//                    });
//
////                    startActivity(new Intent(this, FragmentLayout.class));
////                    finish();
//
//                });
//
//        //Handler
//        Handler handle = new Handler();
//        Runnable callback = new Runnable() {
//            public void run() {
//                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
//                Toast.makeText(MainActivity.this, "An handler run once in MainActivity" , Toast.LENGTH_LONG).show();
//            }
//        };
//
//        handle.post(callback);
//
//        //Button Event
//        findViewById(R.id.normal_samples).setOnClickListener(mClickListener);
//        findViewById(R.id.db_samples).setOnClickListener(mClickListener);
//        findViewById(R.id.observalbe_samples).setOnClickListener(mClickListener);
//        findViewById(R.id.list_menu_samples).setOnClickListener(mClickListener);
//        findViewById(R.id.draw_samples).setOnClickListener(mClickListener);
//        findViewById(R.id.book_fragment_samples).setOnClickListener(mClickListener);
//        findViewById(R.id.view_page_samples).setOnClickListener(mClickListener);
//
//
//        // ------------------------------------------------------------------------------------------------
//        // For Debugging Message
//        sampleNameMap.put(R.id.normal_samples, "R.id.normal_samples");
//        sampleNameMap.put(R.id.db_samples, "R.id.db_samples");
//        sampleNameMap.put(R.id.observalbe_samples, "R.id.observalbe_samples");
//        sampleNameMap.put(R.id.list_menu_samples, "R.id.list_menu_samples");
//        sampleNameMap.put(R.id.draw_samples, "R.id.draw_samples");
//        sampleNameMap.put(R.id.book_fragment_samples, "R.id.book_fragment_samples");
//        sampleNameMap.put(R.id.view_page_samples, "R.id.view_page_samples");
//        //sampleNameMap.put(, "");
//    }
//
//    Button.OnClickListener mClickListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            Logger.d(TAG, "mClickListener : " + sampleNameMap.get(v.getId()));
//
//            switch(v.getId()) {
//                case R.id.normal_samples:
//                    launchSampleActivities();
//                    break;
//                case R.id.db_samples:
//                    launchRoomDBSamples();
//                    break;
//                case R.id.observalbe_samples:
//                    launchObservableSamples();
//                    break;
//                case R.id.list_menu_samples:
//                    listMenusSamples();
//                    break;
//                case R.id.draw_samples:
//                    drawSamplesSamples();
//                    break;
//                case R.id.book_fragment_samples:
//                    fragmentSamples();
//                    break;
//                case R.id.view_page_samples:
//                    viewPageSamples();
//                    break;
//
//                // Remember ===> findViewById(R.id.normalSamples).setOnClickListener(mClickListener);
//            }
//        }
//    };
//
//
//    private void launchSampleActivities() {
//        Observable.just("")
//                .delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
//                    //startActivity(new Intent(this, LiveDataActivity.class));
//                    //startActivity(new Intent(this, ObserverActivity.class));
//
////                    startActivity(new Intent(this, CallOtherActivity.class));
////                    startActivity(new Intent(this, HashMapActivity.class));
////                    startActivity(new Intent(this, ViewActivity.class));
////                    startActivity(new Intent(this, HandlerActivity.class));
////                    startActivity(new Intent(this, LocationManagerActivity.class));
////                    startActivity(new Intent(this, TextViewActivity.class));
////                    startActivity(new Intent(this, AudioActivity.class));
////                    startActivity(new Intent(this, DialogActivity.class));
////                    startActivity(new Intent(this, Dialog2Activity.class));
////                    startActivity(new Intent(this, Dialog3Activity.class));
////                    startActivity(new Intent(this, Dialog4Activity.class));
////                    startActivity(new Intent(this, Dialog5Activity.class));
////                    startActivity(new Intent(this, Test01Activity.class));
////                    startActivity(new Intent(this, Test02_MyViewActivity.class));
////                    startActivity(new Intent(this, SaveState02Activity.class));
////                    startActivity(new Intent(this, SaveState03Activity.class));
////                    startActivity(new Intent(this, DialogTest01Activity.class));
////                    startActivity(new Intent(this, DialogTest02Activity.class));
////                    startActivity(new Intent(this, DialogTest03Activity.class));
////                    startActivity(new Intent(this, DialogTest04Activity.class));
////                    startActivity(new Intent(this, DataBindingTest01Activity.class));
////                    startActivity(new Intent(this, NameActivityForViewModel.class));
//                    //finish();
//                });
//    }
//
//
//    private void launchRoomDBSamples() {
//        Observable.just("Room DB")
//                .delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
////                    startActivity(new Intent(this, UserActivity.class));
//                    //finish();
//                });
//    }
//
//    private void launchObservableSamples() {
//        Observable.just("")
//                .delay(1, TimeUnit.MILLISECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
//                    if (true) {
////                        startActivity(new Intent(this, RecyclerViewActivity.class));
//                    } else {
////                        startActivity(new Intent(this, ReflectionActivity.class));
//                    }
////                    startActivity(new Intent(this, ObserverMainActivity.class));
//
////                    startActivity(new Intent(this, LiveDataActivity.class));
////                    startActivity(new Intent(this, ObserverActivity.class));
//                    //finish();
//                });
//    }
//
//    private void listMenusSamples() {
//        Observable.just("")
//                .delay(1, TimeUnit.MILLISECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
////                    startActivity(new Intent(this, ListView3Activity.class));
////                    startActivity(new Intent(this, RecyclerViewActivity.class));
////                    startActivity(new Intent(this, ListViewActivity.class));
////                    startActivity(new Intent(this, ListView2Activity.class));
////                    startActivity(new Intent(this, ListView02CustomActivity.class));
////                    startActivity(new Intent(this, ListView01Activity.class));
//
//                    //finish();
//                });
//    }
//
//    private void drawSamplesSamples() {
//        Observable.just("")
//                .delay(1, TimeUnit.MILLISECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
////                    startActivity(new Intent(this, DrawActivity.class));
////                    startActivity(new Intent(this, ImageActivity.class));
////                    startActivity(new Intent(this, VideoActivity.class));
////                    startActivity(new Intent(this, DrawTestActivity.class));
////                    startActivity(new Intent(this, ReflectionActivity.class));
//
//                    //finish();
//                });
//    }
//
//    private void fragmentSamples() {
//        Observable.just("")
//                .delay(1, TimeUnit.MILLISECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
//                    startActivity(new Intent(this, FragmentLayout.class));
//                    //finish();
//                });
//    }
//
//    private void viewPageSamples() {
//        Observable.just("")
//                .delay(1, TimeUnit.MILLISECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
//                    startActivity(new Intent(this, ViewPagerActivity.class));
//                    //finish();
//                });
//    }
//
//}
