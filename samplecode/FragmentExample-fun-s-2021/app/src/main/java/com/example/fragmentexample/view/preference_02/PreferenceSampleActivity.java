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
import android.widget.Toast;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;
import com.example.fragmentexample.view.preference_screen.MySettingsFragment;

// Reference Link
// https://codechacha.com/ko/android-preference/

public class PreferenceSampleActivity extends AppCompatActivity {

    DemoFragment demoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_sample);
        demoFragment = new DemoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer_preference_sample, demoFragment).commit();

        PreferenceUtils.getInstance().setFirstKeyString(this,"First String");
        String firstKeyStirng = PreferenceUtils.getInstance().getFristKeyString(this);

        PreferenceUtils.getInstance().setStringWithKey(this, "key_basic_preference","Second String");
        String secondKeyStirng = PreferenceUtils.getInstance().getStringWithKey(this, "key_basic_preference");

//        int temp = 9;
//        Toast.makeText(this, "" + temp, Toast.LENGTH_LONG).show();
    }

//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        String basicPreferenceStirng = pref.getString("key_basic_preference", "");


//    public static class MySettingsFragment extends PreferenceFragmentCompat {
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            //setPreferencesFromResource(R.xml.userpreferences, null);
//            addPreferencesFromResource(R.xml.userpreferences);
//        }
//    }
}