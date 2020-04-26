package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.happybom.tabscreen.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends Fragment {
    private static final String TAG = "TabFragment1";

    private TextView selected_item_textview;

    public TabFragment1 newInstance(int index) {
        TabFragment1 f = new TabFragment1();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    //Listview reference : https://webnautes.tistory.com/1020
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment1, container, false);

        ListView listView = (ListView)view.findViewById(R.id.tabfragment1_listview);
        selected_item_textview = (TextView)view.findViewById(R.id.selected_item_textview);

        List<String> list = new ArrayList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selected_item = (String)adapterView.getItemAtPosition(position);
                selected_item_textview.setText(selected_item);
            }
        });

        list.add("Apple");
        list.add("Pear");
        list.add("Mandarin");
        list.add("Banana");

        return view;
    }

}
