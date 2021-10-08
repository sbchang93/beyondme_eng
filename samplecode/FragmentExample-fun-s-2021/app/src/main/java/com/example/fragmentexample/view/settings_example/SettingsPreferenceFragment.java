package com.example.fragmentexample.view.settings_example;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.fragmentexample.R;

public class SettingsPreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preference, rootKey);
    }
}