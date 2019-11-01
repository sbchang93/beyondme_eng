package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerView71Activity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<String> activityName = new ArrayList<>();
    private HashMap<String, Class<?>> activityNameClassMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view71);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        createActivityName ();
        recyclerView.setAdapter(new RecyclerViewItemListAdapter71(getApplication(), activityName, activityNameClassMap, activityName.size()));
    }

    private void createActivityName () {
        activityName.add(0, "ViewPagerActivity");
        activityNameClassMap.put("ViewPagerActivity", ViewPagerActivity.class);

        activityName.add(1, "ViewPagerCustomActivity");
        activityNameClassMap.put("ViewPagerCustomActivity", ViewPagerCustomActivity.class);

        activityName.add(2, "ViewPager2Activity");
        activityNameClassMap.put("ViewPager2Activity", ViewPager2Activity.class);

        activityName.add(3, "ViewPager3Activity");
        activityNameClassMap.put("ViewPager3Activity", ViewPager3Activity.class);

        activityName.add(4, "ViewPager4Activity");
        activityNameClassMap.put("ViewPager4Activity", ViewPager4Activity.class);

        activityName.add(5, "ViewPager5Activity");
        activityNameClassMap.put("ViewPager5Activity", ViewPager5Activity.class);
    }

}
