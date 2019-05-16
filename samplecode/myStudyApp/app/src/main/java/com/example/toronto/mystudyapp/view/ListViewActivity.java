package com.example.toronto.mystudyapp.view;

// Reference Homepage URL
// https://medium.com/android-develop-android/android...-215b9693d33b

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.toronto.mystudyapp.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu1:
                startActivity(new Intent(this, ObserverActivity.class));
                break;
            case R.id.action_menu2:
                startActivity(new Intent(this, ImageActivity.class));
                break;
            case R.id.action_settings:
                startActivity(new Intent(this, DialogActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
