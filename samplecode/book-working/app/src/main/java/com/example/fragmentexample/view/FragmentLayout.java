package com.example.fragmentexample.view;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.fragment.app.ListFragment;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.util.Log;
        import android.util.TypedValue;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.ScrollView;
        import android.widget.TextView;

        import com.example.fragmentexample.R;

public class FragmentLayout extends AppCompatActivity {

    private static final String TAG = "FragmentLayout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        TitlesFragment titleFragment = new TitlesFragment();
        FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.titles, titleFragment, "titleFragment").commit();
    }

//    public class DetailsActivity extends Activity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            // TODO Auto-generated method stub
//            super.onCreate(savedInstanceState);
//
//            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                finish();
//                return;
//            }
//
//            if (savedInstanceState == null) {
//                DetailsFragment detailsFragment = DetailsFragment.newInstance(getIntent().getIntExtra("index", 0));
//                FragmentTransaction ft;
//                ft = getSupportFragmentManager().beginTransaction();
//                ft.add(R.id.details, detailsFragment, "detailsFragment").commit();
//            }
//        }
//    }

    public static class TitlesFragment extends ListFragment {
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
                showDetails(mCurCheckPosition);
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
            showDetails(position);
        }

        void showDetails(int index){
            Log.v(TAG, "index : " + index);
            mCurCheckPosition = index;
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(index, true);

            if (mLandPane) {
                Log.v(TAG, "mLandPane index : " + index);
                DetailsFragment details = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);
                if (details == null || details.getShowIndex() != index) {
                    details = DetailsFragment.newInstance(index);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.details, details);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            }else {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailsActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }

        }
    }

    public static class DetailsFragment extends Fragment {

        public static DetailsFragment newInstance(int index){

            DetailsFragment f = new DetailsFragment();
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
}
