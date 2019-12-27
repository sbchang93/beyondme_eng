package com.example.fragmentexample.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsExternalFragment extends Fragment {


    public DetailsExternalFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_details_external, container, false);
//    }


    public static DetailsExternalFragment newInstance(int index){

        DetailsExternalFragment f = new DetailsExternalFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (container == null) {
            return null;
        }
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(com.example.toronto.mystudyapp.constants.Shakespeare.DIALOGUE[getShowIndex()]);
        return scroller;
    }

    public int getShowIndex(){
        return getArguments().getInt("index", 0);
    }
}
