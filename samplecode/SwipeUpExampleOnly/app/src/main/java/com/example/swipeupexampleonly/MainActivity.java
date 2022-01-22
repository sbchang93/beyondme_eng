package com.example.swipeupexampleonly;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.swipeupexampleonly.view.AboutActivity;
import com.example.swipeupexampleonly.view.DemoActivity;
import com.example.swipeupexampleonly.view.SwipeUpActivity;

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
        addItem("SwipeUpActivity", "SwipeUpActivity Description", SwipeUpActivity.class);
        addItem("SlidingUpPanel DemoActivity", "SlidingUpPanel DemoActivity Description", DemoActivity.class);
        addItem("AboutActivity", "AboutActivity Description", AboutActivity.class);



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