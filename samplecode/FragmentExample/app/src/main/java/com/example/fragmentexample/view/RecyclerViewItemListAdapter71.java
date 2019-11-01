package com.example.fragmentexample.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewItemListAdapter71 extends RecyclerView.Adapter<RecyclerViewItemListAdapter71.MyViewHolder> {
    private final String TAG = this.getClass().getSimpleName();

    Context mContext;
    private ArrayList<String> activityName;
    private HashMap<String, Class<?>> activityNameClassMap;
    int activitySize = 0;

    public RecyclerViewItemListAdapter71(Context c, ArrayList<String> activityName, HashMap<String, Class<?>> activityNameClassMap, int activitySize) {
        this.mContext = c;
        this.activityName = activityName;
        this.activityNameClassMap = activityNameClassMap;
        this.activitySize = activitySize;
    }

    @Override
    public RecyclerViewItemListAdapter71.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_custom, viewGroup, false);
        return new RecyclerViewItemListAdapter71.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewItemListAdapter71.MyViewHolder myViewHolder, int position) {
        Logger.d(TAG, "RecyclerViewCounterAdapter, onBindViewHolder - position : " + position );
        final String activityNameString = activityName.get(position);
        myViewHolder.tv.setText(activityNameString);

        myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String activityNameString = activityName.get(position);
                Class<?> className = activityNameClassMap.get(activityNameString);
                Intent intent = new Intent(mContext, className);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.activityName.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
