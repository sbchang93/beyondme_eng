package com.example.toronto.mystudyapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

import java.util.List;

// Reference Homepage Link : https://itpangpang.xyz/31?category=555744

public class RecyclerViewCounterAdapter extends RecyclerView.Adapter<RecyclerViewCounterAdapter.MyViewHolder> {
    private final String TAG = this.getClass().getSimpleName();

    Context mContext;
    List<Integer> items;
    int i = 0;

    public RecyclerViewCounterAdapter(Context c, List<Integer> items, int i) {
        this.mContext = c;
        this.items = items;
        this.i = i;
    }

    @Override
    public RecyclerViewCounterAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_custom, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCounterAdapter.MyViewHolder myViewHolder, int position) {
        Logger.d(TAG, "RecyclerViewCounterAdapter, onBindViewHolder - position : " + position );

        final Integer item = items.get(position);
        myViewHolder.tv.setText(""+item);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}

