package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;

public class AdapterActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter);
//        setListAdapter(new MyAdapter());
    }
//    private class MyAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return Cheeses.CHEESES.length;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
//            }
//
//            ((TextView) convertView.findViewById(android.R.id.text1))
//                    .setText(getItem(position));
//            return convertView;
//        }
//
//        @Override
//        public String getItem(int position) {
//            return Cheeses.CHEESES[position];
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return Cheeses.CHEESES[position].hashCode();
//        }


//    }

}
