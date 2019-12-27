package com.example.fragmentexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmentexample.R;

public class FragmentMain01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main01);

        TitlesExternalFragment titleFragment = new TitlesExternalFragment();
        FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.titles, titleFragment, "titleFragment").commit();

//        //Old style of fragmentManager
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, new FirstExampleFragment())
//                .commit();
    }
}
