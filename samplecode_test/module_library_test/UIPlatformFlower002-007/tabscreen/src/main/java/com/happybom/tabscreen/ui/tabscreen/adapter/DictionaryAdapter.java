package com.happybom.tabscreen.ui.tabscreen.adapter;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happybom.tabscreen.R;
import com.happybom.tabscreen.data.Dictionary;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    private ArrayList<Dictionary> dictionaryList;

    public DictionaryAdapter(ArrayList<Dictionary> list) {
        this.dictionaryList = list;
    }

    @Override
    public int getItemCount() {
        return (dictionaryList != null ? dictionaryList.size() : 0);
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dictionary_item, viewGroup, false);
        DictionaryViewHolder viewHolder = new DictionaryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder viewHolder, int position) {

        viewHolder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewHolder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewHolder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewHolder.id.setGravity(Gravity.CENTER);
        viewHolder.english.setGravity(Gravity.CENTER);
        viewHolder.korean.setGravity(Gravity.CENTER);


        viewHolder.id.setText(dictionaryList.get(position).getId());
        viewHolder.english.setText(dictionaryList.get(position).getEnglish());
        viewHolder.korean.setText(dictionaryList.get(position).getKorean());
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView english;
        protected TextView korean;

        public DictionaryViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.dictionary_id_item);
            this.english = (TextView) view.findViewById(R.id.dictionary_english_item);
            this.korean = (TextView) view.findViewById(R.id.dictionary_korean_item);
        }
    }
}