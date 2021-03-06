package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fragmentexample.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerOthers01Activity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<String> activityName = new ArrayList<>();
    private HashMap<String, Class<?>> activityNameClassMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_others01);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        createActivityName ();
        recyclerView.setAdapter(new RecyclerViewItemListAdapter71(getApplicationContext(), activityName, activityNameClassMap, activityName.size()));
    }

    private void createActivityName () {
        int position = 0;

        activityName.add(position, "Notification Example");
        activityNameClassMap.put("Notification Example", Notification01Activity.class);

        position++;


    }

}
