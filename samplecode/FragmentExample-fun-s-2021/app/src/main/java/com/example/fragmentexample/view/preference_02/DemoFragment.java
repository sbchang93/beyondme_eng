package com.example.fragmentexample.view.preference_02;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;

public class DemoFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    private Context mContext;

    public static final String basicPreferenceKey = "key_basic_preference";
    public static final String addShortcutKey = "key_add_shortcut";
    public static final String switchOnKey = "key_switch_on";
    public static final String editNameKey = "key_edit_name";
    public static final String setItemKey = "key_set_item";

    private PreferenceCategory mBasicPrefenceCategory = null;
    private PreferenceCategory mFunctionsPrefenceCategory = null;
    private PreferenceCategory mOptionsPrefenceCategory = null;

    Preference basicPreference = null;
    Preference addShortCutPreference = null;
    Preference switchPreference = null;
    Preference editPreference = null;
    Preference actionPreference = null;
    Preference goToUrlPreference = null;


    private String mBasicString = null;
    private boolean mSwitchOnChecked = false;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        mContext = getContext();

        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);

        mBasicPrefenceCategory = (PreferenceCategory) findPreference("Basic_category");
        mFunctionsPrefenceCategory = (PreferenceCategory) findPreference("Functions_category");
        mOptionsPrefenceCategory = (PreferenceCategory) findPreference("Options_category");

        basicPreference = findPreference(basicPreferenceKey);
        basicPreference.setOnPreferenceChangeListener(this);

        addShortCutPreference = (CheckBoxPreference) findPreference(addShortcutKey);
        addShortCutPreference.setOnPreferenceChangeListener(this);

        switchPreference = (SwitchPreference) findPreference(switchOnKey);
        switchPreference.setOnPreferenceChangeListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        String basicPreferenceStirng = PreferenceUtils.getInstance().getStringWithKey(mContext, basicPreferenceKey, "");
        basicPreference.setTitle(basicPreferenceStirng);

        // Switch Preference is set by User setting action.
        boolean bSwitchPreference = PreferenceUtils.getInstance().getBooleanWithKey(mContext, switchOnKey);
        ((SwitchPreference)switchPreference).setChecked(bSwitchPreference);

//        // Set false in Switch Preference whenever it is run. ( Initiation for Test. )
//        PreferenceUtils.getInstance().setBooleanWithKey(mContext, switchOnKey, false);
//        ((SwitchPreference)switchPreference).setChecked(false);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (key.equals(basicPreferenceKey)) {
            mBasicString = newValue.toString();
            return true;
        } else if (key.equals(addShortcutKey)) {
            // Do something
            return true;
        } else if (key.equals(switchOnKey)) {
            mSwitchOnChecked = Boolean.valueOf(newValue.toString());
            PreferenceUtils.getInstance().setBooleanWithKey(mContext, switchOnKey, mSwitchOnChecked);
            return true;
        }
        return false;
    }
}