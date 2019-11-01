package com.example.toronto.mystudyapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

import java.util.List;

public class RecyclerViewCounterAdapter4 extends RecyclerView.Adapter<RecyclerViewCounterAdapter4.MyViewHolder> {
    private final String TAG = this.getClass().getSimpleName();

    Context mContext;
    List<String> items;
    String text;

    public RecyclerViewCounterAdapter4(Context c, List<String> items, String text) {
        this.mContext = c;
        this.items = items;
        this.text = text;
    }
    @Override
    public RecyclerViewCounterAdapter4.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_custom, viewGroup, false);
        return new RecyclerViewCounterAdapter4.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCounterAdapter4.MyViewHolder myViewHolder, int position) {
        final String item = items.get(position);
        myViewHolder.tv.setText("" + item);
    }

    @Override
    public int getItemCount()
    {
        return this.items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}