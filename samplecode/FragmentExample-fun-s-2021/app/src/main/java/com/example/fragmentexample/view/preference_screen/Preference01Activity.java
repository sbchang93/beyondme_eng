package com.example.fragmentexample.view.preference_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;

import com.example.fragmentexample.R;

// Reference URL
// SwitchPerferenceCompat, Preference
// https://kumgo1d.tistory.com/31

// http://sjava.net/tag/preferencefragmentcompat/

// PreferenceFragmentCompat
// https://developer.android.com/guide/topics/ui/settings/organize-your-settings?hl=ko

public class Preference01Activity extends AppCompatActivity {

    MySettingsFragment mySettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        mySettingsFragment = new MySettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer_preference, mySettingsFragment).commit();

    }

//    public static class MySettingsFragment extends PreferenceFragmentCompat {
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            //setPreferencesFromResource(R.xml.userpreferences, null);
//            addPreferencesFromResource(R.xml.userpreferences);
//        }
//    }
}

// <Framelayout .. (X)   ===>   <FrameLayout .. (O)
//2021-10-06 18:54:30.813 2284-2284/com.example.fragmentexample E/AndroidRuntime: FATAL EXCEPTION: main
//        Process: com.example.fragmentexample, PID: 2284
//        java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.fragmentexample/com.example.fragmentexample.view.preference_screen.Preference01Activity}: android.view.InflateException: Binary XML file line #11 in com.example.fragmentexample:layout/activity_preference: Binary XML file line #11 in com.example.fragmentexample:layout/activity_preference: Error inflating class Framelayout
//        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3792)
//        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3968)