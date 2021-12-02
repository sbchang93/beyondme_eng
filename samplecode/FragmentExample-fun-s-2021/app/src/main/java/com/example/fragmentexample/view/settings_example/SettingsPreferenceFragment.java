package com.example.fragmentexample.view.settings_example;

import android.content.Context;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;

import static com.example.fragmentexample.constants.Constants.cpUpdateCardKey;
import static com.example.fragmentexample.constants.Constants.emailKey;
import static com.example.fragmentexample.constants.Constants.memoKey;
import static com.example.fragmentexample.constants.Constants.spNameKey;
import static com.example.fragmentexample.constants.Constants.phoneKey;
import static com.example.fragmentexample.constants.Constants.updateCardKey;

public class SettingsPreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    private Context mContext;

    private PreferenceScreen mPreferenceScreen = null;

    private PreferenceCategory mDisplaySettingsPrefenceCategory = null;

    CustomCardPreference cpUpdateCard = null;

    SwitchPreference spName = null;
    SwitchPreference spPhone = null;
    SwitchPreference spEmail = null;
    SwitchPreference spMemo = null;
    SwitchPreference spUpdateCard = null;

    CustomCardPreference mUpdateCard;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        mContext = getContext();

        setPreferencesFromResource(R.xml.setting_preference, rootKey);

        mPreferenceScreen = (PreferenceScreen) findPreference("key_setting_preference");

        cpUpdateCard = findPreference(cpUpdateCardKey);

        spName = findPreference(spNameKey);
        spName.setOnPreferenceChangeListener(this);
        spPhone = findPreference(phoneKey);
        spPhone.setOnPreferenceChangeListener(this);
        spEmail = findPreference(emailKey);
        spEmail.setOnPreferenceChangeListener(this);
        spMemo = findPreference(memoKey);
        spMemo.setOnPreferenceChangeListener(this);
        spUpdateCard = findPreference(updateCardKey);
        spUpdateCard.setOnPreferenceChangeListener(this);

        //init();

        // This code is added for removing Update Card on top of the screen on onCreate --- Timing Issue.
        boolean checkUpdateCard = PreferenceUtils.getInstance().getBoolean(mContext, updateCardKey);
        spUpdateCard.setChecked(checkUpdateCard);
        if(!checkUpdateCard) {
            removeUpdateCard();
        }
    }

    private void init() {
        boolean bChecked = PreferenceUtils.getInstance().getBoolean(mContext, spNameKey);
        spName.setChecked(bChecked);

        bChecked = PreferenceUtils.getInstance().getBoolean(mContext, phoneKey);
        spPhone.setChecked(bChecked);

        bChecked = PreferenceUtils.getInstance().getBoolean(mContext, emailKey);
        spEmail.setChecked(bChecked);

        bChecked = PreferenceUtils.getInstance().getBoolean(mContext, memoKey);
        spMemo.setChecked(bChecked);

        boolean checkUpdateCard = PreferenceUtils.getInstance().getBoolean(mContext, updateCardKey);
        spUpdateCard.setChecked(checkUpdateCard);
        if(!checkUpdateCard) {
            removeUpdateCard();
        }
    }

    private boolean supportSwitchPreference(String key) {
        if (key.equals(spNameKey) || key.equals(phoneKey) || key.equals(emailKey)
                || key.equals(memoKey) || key.equals(updateCardKey)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();

        if (supportSwitchPreference(key)) {
            boolean bChecked = Boolean.valueOf(newValue.toString());
            PreferenceUtils.getInstance().setBoolean(mContext, key, bChecked);

            if (key.equals(updateCardKey)) {
                if (bChecked)
                    mPreferenceScreen.addPreference(cpUpdateCard);
                else
                    mPreferenceScreen.removePreference(cpUpdateCard);
            }
            return true;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    public void removeUpdateCard() {
        mPreferenceScreen.removePreference(cpUpdateCard);
    }
}