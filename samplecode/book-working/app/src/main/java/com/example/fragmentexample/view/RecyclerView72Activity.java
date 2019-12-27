package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fragmentexample.R;

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
        recyclerView.setAdapter(new RecyclerViewItemListAdapter71(getApplicationContext(), activityName, activityNameClassMap, activityName.size()));
    }

    private void createActivityName () {
        int position = 0;

        activityName.add(position, "ViewPagerActivity - Library ViewPager");
        activityNameClassMap.put("ViewPagerActivity - Library ViewPager", ViewPagerActivity.class);

        position++;
        activityName.add(position, "ViewPager31Activity - Lib Modification ViewPager");
        activityNameClassMap.put("ViewPager31Activity - Lib Modification ViewPager", ViewPager31Activity.class);

        position++;
        activityName.add(position, "ViewPager21Activity - Tab ViewPager");
        activityNameClassMap.put("ViewPager21Activity - Tab ViewPager", ViewPager21Activity.class);

        position++;
        activityName.add(position, "ViewPager22Activity");
        activityNameClassMap.put("ViewPager22Activity", ViewPager22Activity.class);

        position++;
        activityName.add(position, "ViewPager11Activity - Simple Text ViewPager");
        activityNameClassMap.put("ViewPager11Activity - Simple Text ViewPager", ViewPager11Activity.class);

        position++;
        activityName.add(position, "ViewPager12Activity - Image ViewPager");
        activityNameClassMap.put("ViewPager12Activity - Image ViewPager", ViewPager12Activity.class);

        position++;
        activityName.add(position, "ViewPager13Activity - Circular ViewPapger");
        activityNameClassMap.put("ViewPager13Activity - Circular ViewPapger", ViewPager13Activity.class);

        position++;
        activityName.add(position, "ViewPager14Activity - ViewPapger Indicator");
        activityNameClassMap.put("ViewPager14Activity - ViewPapger Indicator", ViewPager14Activity.class);



    }

}