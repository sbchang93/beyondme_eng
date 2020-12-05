package com.example.toronto.mystudyapp.view;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView01Activity extends ListActivity {   // <---    ListActivity를 상속 받아서 간단한 예제 만들기
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] values = {"Apple", "Avocado", "Banana", "Blackberry", "Blueberry",
                "Cherry", "Coconut", "Grape", "Lemon", "Lime", "Mango", "Watermelon", "Strawberry", "Orange"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
    }

    // simple_list_item_1
    // simple_list_item_2
    // simple_list_item_checked
    // simple_list_item_single_choice
    // simepl_list_item_mulitple_choice

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      String item = (String) getListAdapter().getItem(position);
      Toast.makeText(this, item+" selected", Toast.LENGTH_LONG).show();
    }
}
