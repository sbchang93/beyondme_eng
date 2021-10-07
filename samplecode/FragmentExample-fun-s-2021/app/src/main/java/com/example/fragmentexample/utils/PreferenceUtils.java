package com.example.fragmentexample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.fragmentexample.constants.PreferenceContants;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtils {
    private final static String TAG = "PreferenceUtils";

    private static PreferenceUtils instance = new PreferenceUtils();

    private PreferenceUtils() {
    }

    public static PreferenceUtils getInstance() {
        return instance;
    }

    // Specific one
    public String getFristKeyString(Context context) {
        return (context == null) ? "0" :
                context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE).getString("key_basic_preference", "0");
    }

    public void setFirstKeyString(Context context, String id) {
        context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE).edit()
                .putString("key_basic_preference", id).apply();
    }

    public void setBooleanWithKey(Context context, String key, boolean value) {
        Log.d(TAG, "setBooleanWithKey " + key + "," + value);
        SharedPreferences.Editor editor = context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanWithKey(Context context, String key) {
        return (context != null) &&
                context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE)
                        .getBoolean(key, false);
    }

    public void setStringWithKey(Context context, String key, String value) {
        Log.d(TAG, "setStringWithKey " + key + "," + value);
        SharedPreferences.Editor editor = context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringWithKey(Context context, String key) {
        if (context == null) return "";
        return context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE)
                .getString(key, "");
    }

    public String getStringWithKey(Context context, String key, String defaultValue) {
        if (context == null) return defaultValue;
        return context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE)
                .getString(key, defaultValue);
    }
}
