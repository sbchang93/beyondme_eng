package com.example.toronto.mystudyapp.view;

// 참조 홈페이지
// https://medium.com/android-develop-android/android-%EA%B0%9C%EB%B0%9C-11-recyclerview%EC%99%80-cardview-fce1f4fc6c23

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item> items=new ArrayList<>();
        Recycler_item[] item = new Recycler_item[5];
        item[0] = new Recycler_item(R.drawable.a,"#1");
        item[1] = new Recycler_item(R.drawable.b,"#2");
        item[2] = new Recycler_item(R.drawable.c,"#3");
        item[3] = new Recycler_item(R.drawable.d,"#4");
        item[4] = new Recycler_item(R.drawable.e,"#5");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
    }

}

