package com.example.toronto.mystudyapp.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

import java.util.List;

// Reference Homepage Link : https://itpangpang.xyz/31?category=555744


public class RecyclerViewCounterAdapter3 extends RecyclerView.Adapter<RecyclerViewCounterAdapter3.MyViewHolder> {
    private final String TAG = this.getClass().getSimpleName();

    Context mContext;
    List<String> items;
    String text;

    public RecyclerViewCounterAdapter3(Context c, List<String> items, String text)
    {
        this.mContext = c;
        this.items = items;
        this.text = text;
    }
    @Override
    public RecyclerViewCounterAdapter3.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_custom, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCounterAdapter3.MyViewHolder myViewHolder, int position)
    {
        final String item = items.get(position);
        myViewHolder.tv.setText("" + item);
        myViewHolder.tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext,item,Toast.LENGTH_SHORT).show();
            }
        });
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

