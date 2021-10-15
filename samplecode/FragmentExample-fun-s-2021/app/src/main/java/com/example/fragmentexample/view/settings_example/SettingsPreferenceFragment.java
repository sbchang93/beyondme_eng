package com.example.fragmentexample.view.settings_example;

import android.content.Context;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;

import static com.example.fragmentexample.constants.Constants.emailKey;
import static com.example.fragmentexample.constants.Constants.memoKey;
import static com.example.fragmentexample.constants.Constants.nameKey;
import static com.example.fragmentexample.constants.Constants.phoneKey;

public class SettingsPreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    private Context mContext;

    private PreferenceCategory mDisplaySettingsPrefenceCategory = null;

    SwitchPreference spName = null;
    SwitchPreference spPhone = null;
    SwitchPreference spEmail = null;
    SwitchPreference spMemo = null;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        mContext = getContext();

        setPreferencesFromResource(R.xml.setting_preference, rootKey);

        spName = findPreference(nameKey);
        spName.setOnPreferenceChangeListener(this);
        spPhone = findPreference(phoneKey);
        spPhone.setOnPreferenceChangeListener(this);
        spEmail = findPreference(emailKey);
        spEmail.setOnPreferenceChangeListener(this);
        spMemo = findPreference(memoKey);
        spMemo.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();

        if (key.equals(nameKey) || key.equals(phoneKey) || key.equals(emailKey) || key.equals(memoKey) ) {
            boolean bChecked = Boolean.valueOf(newValue.toString());
            PreferenceUtils.getInstance().setBooleanWithKey(mContext, key, bChecked);
            //PreferenceUtils.getInstance().setBooleanWithKey(mContext, key, Boolean.valueOf(newValue.toString()));
            return true;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean bChecked = PreferenceUtils.getInstance().getBooleanWithKey(mContext, nameKey);
        spName.setChecked(bChecked);

        bChecked = PreferenceUtils.getInstance().getBooleanWithKey(mContext, phoneKey);
        spPhone.setChecked(bChecked);

        bChecked = PreferenceUtils.getInstance().getBooleanWithKey(mContext, emailKey);
        spEmail.setChecked(bChecked);

        bChecked = PreferenceUtils.getInstance().getBooleanWithKey(mContext, memoKey);
        spMemo.setChecked(bChecked);
    }
}