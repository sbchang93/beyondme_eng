package com.example.fragmentexample.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fragmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitlesExternalFragment  extends ListFragment {

    private static final String TAG = "TitlesExternalFragment";

    public TitlesExternalFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_titles_external, container, false);
//    }

    boolean mLandPane;
    int mCurCheckPosition = 0;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "onActivityCreated");

        setListAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_activated_1, com.example.toronto.mystudyapp.constants.Shakespeare.TITLES));

        View detailsFrame = getActivity().findViewById(R.id.details);
        mLandPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mLandPane) {
            //showDetails(mCurCheckPosition);

            //If we move fragments from inner fragment class to external fragment class,
            // external fragment should not call the other fragments directly,
            // it should request something ( to want to do ) to Activity class (Mother class).
            // it is a safety way to communication between external fragments.
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
//        showDetails(position);
    }

//    void showDetails(int index){
//        Log.v(TAG, "index : " + index);
//        mCurCheckPosition = index;
//        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        getListView().setItemChecked(index, true);
//
//        if (mLandPane) {
//            Log.v(TAG, "mLandPane index : " + index);
//            DetailsExternalFragment details = (DetailsExternalFragment) getFragmentManager().findFragmentById(R.id.details);
//            if (details == null || details.getShowIndex() != index) {
//                details = DetailsExternalFragment.newInstance(index);
//
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.details, details);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.commit();
//            }
//        }else {
//            Intent intent = new Intent();
//            intent.setClass(getActivity(), DetailsActivity.class);
//            intent.putExtra("index", index);
//            startActivity(intent);
//        }
//
//    }
}
