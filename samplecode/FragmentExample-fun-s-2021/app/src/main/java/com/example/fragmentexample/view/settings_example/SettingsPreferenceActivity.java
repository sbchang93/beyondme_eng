package com.example.fragmentexample.view.settings_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.preference_screen.MySettingsFragment;

public class SettingsPreferenceActivity extends AppCompatActivity {

    SettingsPreferenceFragment mSettingsPreferenceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_preference);
        mSettingsPreferenceFragment = new SettingsPreferenceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer, mSettingsPreferenceFragment).commit();
    }
}