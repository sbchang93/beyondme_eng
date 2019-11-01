package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerView75Activity extends AppCompatActivity {
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
        activityName.add(0, "ListView3Activity");
        activityNameClassMap.put("ListView3Activity", ListView3Activity.class);

        activityName.add(1, "RecyclerViewActivity");
        activityNameClassMap.put("RecyclerViewActivity", RecyclerViewActivity.class);

        activityName.add(2, "ListViewActivity");
        activityNameClassMap.put("ListViewActivity", ListViewActivity.class);

        activityName.add(3, "ListView2Activity");
        activityNameClassMap.put("ListView2Activity", ListView2Activity.class);

        activityName.add(4, "ListView02CustomActivity");
        activityNameClassMap.put("ListView02CustomActivity", ListView02CustomActivity.class);

        activityName.add(5, "ListView01Activity");
        activityNameClassMap.put("ListView01Activity", ListView01Activity.class);
    }
}
