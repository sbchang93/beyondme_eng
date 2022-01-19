package com.example.swipeup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.swipeup.model.DemoModel;
import com.example.swipeup.view.animation_example.AnimationActivity;
import com.example.swipeup.view.animation_example.AnimationDrawableActivity;
import com.example.swipeup.view.animation_example.ViewPropertyAnimatorActivity;
import com.example.swipeup.view.custom_view.CustomLoginButtonActivity;
import com.example.swipeup.view.custom_view.LinedEditTextActivity;
import com.example.swipeup.view.custom_view.VolumeControlActivity;
import com.example.swipeup.view.sliding_up_pannel.DemoActivity;
import com.example.swipeup.view.swipe_up_panel.SwipeUpActivity;
import com.example.swipeup.view.touch_example.MoveLoggerActivity;
import com.example.swipeup.view.touch_example.MultitouchActivity;
import com.example.swipeup.view.touch_example.TouchDelegateActivity;
import com.example.swipeup.view.touch_example.TouchForwardActivity;
import com.example.swipeup.view.touch_example.TouchInterceptActivity;
import com.example.swipeup.view.touch_example.TouchListenerActivity;
import com.example.swipeup.view.touch_example.TwoDimensionGestureScrollActivity;
import com.example.swipeup.view.touch_example.TwoDimensionScrollActivity;
import com.example.swipeup.view.view_example.CanvasActivity;
import com.example.swipeup.view.view_example.CardViewDemoActivity;
import com.example.swipeup.view.view_example.PaintActivity;
import com.example.swipeup.view.view_example.TextViewActivity;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        addItem("SlidingUpPanel DemoActivity", "SlidingUpPanel DemoActivity Description", DemoActivity.class);
        addItem("SwipeUpActivity", "SwipeUpActivity Description", SwipeUpActivity.class);

        addItem("VolumeControlActivity", "VolumeControlActivity Description", VolumeControlActivity.class);
        addItem("LinedEditTextActivity", "LinedEditTextActivity Description", LinedEditTextActivity.class);
        addItem("PaintActivity", "PaintActivity Description", PaintActivity.class);
        addItem("CustomLoginButtonActivity", "CustomLoginButtonActivity Description", CustomLoginButtonActivity.class);
        addItem("CardViewDemoActivity", "CardViewDemoActivity Description", CardViewDemoActivity.class);
        addItem("AnimationActivity", "AnimationActivity Description", AnimationActivity.class);
        addItem("AnimationDrawableActivity", "AnimationDrawableActivity Description", AnimationDrawableActivity.class);
        addItem("ViewPropertyAnimatorActivity", "ViewPropertyAnimatorActivity Description", ViewPropertyAnimatorActivity.class);

        addItem("CanvasActivity", "CanvasActivity Description", CanvasActivity.class);
        addItem("TextViewActivity", "TextViewActivity Description", TextViewActivity.class);

        addItem("MoveLoggerActivity", "MoveLoggerActivity Description", MoveLoggerActivity.class);
        addItem("TouchListenerActivity", "TouchListenerActivity Description", TouchListenerActivity.class);
        addItem("TouchDelegateActivity", "TouchDelegateActivity Description", TouchDelegateActivity.class);
        addItem("TouchForwardActivity", "TouchForwardActivity Description", TouchForwardActivity.class);
        addItem("TwoDimensionScrollActivity", "TwoDimensionScrollActivity Description", TwoDimensionScrollActivity.class);
        addItem("TwoDimensionGestureScrollActivity", "TwoDimensionGestureScrollActivity Description", TwoDimensionGestureScrollActivity.class);
        addItem("MultitouchActivity", "MultitouchActivity Description", MultitouchActivity.class);
        addItem("TouchInterceptActivity", "TouchInterceptActivity Description", TouchInterceptActivity.class);



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

            //Animation Effect : I can not see the animation effect
            ObjectAnimator.ofFloat(convertView, "alpha", 0.0f, 1f).start();

            return convertView;
        }


        static class ViewHolder {
            TextView title;
            TextView description;
        }
    }

}