package com.example.fragmentexample.view.preference_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.preference_screen.MySettingsFragment;

// Reference Link

public class PreferenceSampleActivity extends AppCompatActivity {

    DemoFragment demoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_sample);
        demoFragment = new DemoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer_preference_sample, demoFragment).commit();

    }

//    public static class MySettingsFragment extends PreferenceFragmentCompat {
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            //setPreferencesFromResource(R.xml.userpreferences, null);
//            addPreferencesFromResource(R.xml.userpreferences);
//        }
//    }
}