package com.example.toronto.mystudyapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerView72Activity extends AppCompatActivity {
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
        activityName.add(0, "RecyclerView2Activity");
        activityNameClassMap.put("RecyclerView2Activity", RecyclerView2Activity.class);

        activityName.add(1, "RecyclerView3Activity");
        activityNameClassMap.put("RecyclerView3Activity", RecyclerView3Activity.class);

        activityName.add(2, "RecyclerView4Activity");
        activityNameClassMap.put("RecyclerView4Activity", RecyclerView4Activity.class);
    }

}

