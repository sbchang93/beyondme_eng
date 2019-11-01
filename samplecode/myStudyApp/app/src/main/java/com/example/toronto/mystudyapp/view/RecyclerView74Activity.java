package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerView74Activity extends AppCompatActivity {
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
        activityName.add(0, "DrawActivity");
        activityNameClassMap.put("DrawActivity", DrawActivity.class);

        activityName.add(1, "ImageActivity");
        activityNameClassMap.put("ImageActivity", ImageActivity.class);

        activityName.add(2, "VideoActivity");
        activityNameClassMap.put("VideoActivity", VideoActivity.class);

        activityName.add(3, "DrawTestActivity");
        activityNameClassMap.put("DrawTestActivity", DrawTestActivity.class);

        activityName.add(4, "ReflectionActivity");
        activityNameClassMap.put("ReflectionActivity", ReflectionActivity.class);
    }
}
