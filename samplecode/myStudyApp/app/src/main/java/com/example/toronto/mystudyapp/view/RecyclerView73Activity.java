package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerView73Activity extends AppCompatActivity {
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
        activityName.add(0, "LiveDataActivity");
        activityNameClassMap.put("LiveDataActivity", LiveDataActivity.class);

        activityName.add(1, "NameActivityForViewModel");
        activityNameClassMap.put("NameActivityForViewModel", NameActivityForViewModel.class);

        activityName.add(2, "RecyclerView4Activity");
        activityNameClassMap.put("RecyclerView4Activity", RecyclerView4Activity.class);

        activityName.add(3, "CallOtherActivity");
        activityNameClassMap.put("CallOtherActivity", CallOtherActivity.class);

        activityName.add(4, "HashMapActivity");
        activityNameClassMap.put("HashMapActivity", HashMapActivity.class);

        activityName.add(5, "ViewActivity");
        activityNameClassMap.put("ViewActivity", ViewActivity.class);

        activityName.add(6, "HandlerActivity");
        activityNameClassMap.put("HandlerActivity", HandlerActivity.class);

        activityName.add(7, "LocationManagerActivity");
        activityNameClassMap.put("LocationManagerActivity", LocationManagerActivity.class);

        activityName.add(8, "TextViewActivity");
        activityNameClassMap.put("TextViewActivity", TextViewActivity.class);

        activityName.add(9, "AudioActivity");
        activityNameClassMap.put("AudioActivity", AudioActivity.class);

        activityName.add(10, "DialogActivity");
        activityNameClassMap.put("DialogActivity", DialogActivity.class);

        activityName.add(11, "Dialog2Activity");
        activityNameClassMap.put("Dialog2Activity", Dialog2Activity.class);

        activityName.add(12, "Dialog3Activity");
        activityNameClassMap.put("Dialog3Activity", Dialog3Activity.class);

        activityName.add(13, "Dialog4Activity");
        activityNameClassMap.put("Dialog4Activity", Dialog4Activity.class);

        activityName.add(14, "Dialog5Activity");
        activityNameClassMap.put("Dialog5Activity", Dialog5Activity.class);

        activityName.add(15, "Test01Activity");
        activityNameClassMap.put("Test01Activity", Test01Activity.class);

        activityName.add(16, "SaveState02Activity");
        activityNameClassMap.put("SaveState02Activity", SaveState02Activity.class);

        activityName.add(17, "SaveState03Activity");
        activityNameClassMap.put("SaveState03Activity", SaveState03Activity.class);

        activityName.add(18, "DialogTest01Activity");
        activityNameClassMap.put("DialogTest01Activity", DialogTest01Activity.class);

        activityName.add(19, "DialogTest02Activity");
        activityNameClassMap.put("DialogTest02Activity", DialogTest02Activity.class);

        activityName.add(20, "DialogTest03Activity");
        activityNameClassMap.put("DialogTest03Activity", DialogTest03Activity.class);

        activityName.add(21, "DialogTest04Activity");
        activityNameClassMap.put("DialogTest04Activity", DialogTest04Activity.class);

        activityName.add(22, "DataBindingTest01Activity");
        activityNameClassMap.put("DataBindingTest01Activity", DataBindingTest01Activity.class);

        activityName.add(23, "ObserverActivity");
        activityNameClassMap.put("ObserverActivity", ObserverActivity.class);
    }
}
