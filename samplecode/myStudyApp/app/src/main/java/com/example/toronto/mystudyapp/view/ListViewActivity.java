package com.example.toronto.mystudyapp.view;

// 참조 홈페이지
//https://medium.com/android-develop-android/android개발-5-listview를-이용한-리스트만들기-215b9693d33b

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        ListView listView=(ListView)findViewById(R.id.listview);

        ArrayList<Listviewitem> data=new ArrayList<>();
        Listviewitem lion=new Listviewitem(R.drawable.lion,"Lion");
        Listviewitem tiger=new Listviewitem(R.drawable.tiger,"Tiger");
        Listviewitem dog=new Listviewitem(R.drawable.dog,"Dog");
        Listviewitem cat=new Listviewitem(R.drawable.cat,"Cat");

        data.add(lion);
        data.add(tiger);
        data.add(dog);
        data.add(cat);

        ListViewAdapter adapter = new ListViewAdapter(this,R.layout.listviewitem,data);
        listView.setAdapter(adapter);
    }
}
