package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.fragmentexample.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_fragment_layout);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            FragmentLayout.DetailsFragment detailsFragment = FragmentLayout.DetailsFragment.newInstance(getIntent().getIntExtra("index", 0));
            FragmentTransaction ft;
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.details, detailsFragment, "detailsFragment").commit();
        }
    }
}
