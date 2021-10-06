package com.example.fragmentexample.view.preference_02;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.fragmentexample.R;

public class DemoFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}