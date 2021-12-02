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

    public void setBoolean(Context context, String key, boolean value) {
        Log.d(TAG, "setBoolean " + key + "," + value);
        SharedPreferences.Editor editor = context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(Context context, String key) {
        return (context != null) &&
                context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE)
                        .getBoolean(key, false);
    }

    public void setString(Context context, String key, String value) {
        Log.d(TAG, "setStringWithKey " + key + "," + value);
        SharedPreferences.Editor editor = context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(Context context, String key) {
        if (context == null) return "";
        return context.getSharedPreferences(PreferenceContants.MAIN_PREFERENCE, MODE_PRIVATE)
                .getString(key, "");
    }

}
