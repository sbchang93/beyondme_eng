package com.happybom.tabscreen.ui.tabscreen.dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.happybom.tabscreen.R;
import com.happybom.tabscreen.data.Dictionary;

import java.util.ArrayList;

// Reference Homepage : https://webnautes.tistory.com/1214
public class DictionaryFragment extends Fragment {
    private static final String TAG = "TabFragment3";
    private ArrayList<Dictionary> dictionaryArrayList;
    private DictionaryAdapter dictionaryAdapter;
    private int count = 0;

    public DictionaryFragment newInstance(int index) {
        DictionaryFragment f = new DictionaryFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dictionary_fragment, container, false);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dictionary_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        dictionaryArrayList = new ArrayList<>();
        dictionaryAdapter = new DictionaryAdapter(dictionaryArrayList);
        recyclerView.setAdapter(dictionaryAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        Button insertButton = (Button) view.findViewById(R.id.dictionary_insert_button);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appleKoreanText = getString(R.string.apple_korean);
                Dictionary data = new Dictionary("" + count, "Apple" + count,  appleKoreanText + count);
                dictionaryArrayList.add(data); // add it in the end
                //dictionaryArrayList.add(0,data); // add it in the first
                dictionaryAdapter.notifyDataSetChanged();
                count++;
            }
        });

        String appleKoreanText = getString(R.string.apple_korean);
        Dictionary data = new Dictionary("" + count, "Apple" + count,  appleKoreanText + count);
        dictionaryArrayList.add(data);
        count++;
        data = new Dictionary("" + count, "Apple" + count,  appleKoreanText + count);
        dictionaryArrayList.add(data);
        count++;
        data = new Dictionary("" + count, "Apple" + count,  appleKoreanText + count);
        dictionaryArrayList.add(data);
        dictionaryAdapter.notifyDataSetChanged();
        count++;

        return view;
    }

}
