package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;

public class ListView3Activity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view3);

        ArrayList<String> Generals = new ArrayList<String>();
        Generals.add("김유신");
        Generals.add("이순신");
        Generals.add("강감찬");
        Generals.add("을지문덕");

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Generals);

        ListView list = (ListView) findViewById(R.id.lv);
        list.setAdapter(adapter);

    }
}
