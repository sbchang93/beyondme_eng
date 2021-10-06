package com.example.fragmentexample.view.preference_screen;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.fragmentexample.R;

public class MySettingsFragment  extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //setPreferencesFromResource(R.xml.preferences, rootKey);

        setPreferencesFromResource(R.xml.userpreferences, rootKey);
        //setPreferencesFromResource(R.xml.userpreferences, null);
        //addPreferencesFromResource(R.xml.userpreferences);
    }
}