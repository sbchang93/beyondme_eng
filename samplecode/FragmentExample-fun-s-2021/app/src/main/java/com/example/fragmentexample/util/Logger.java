package com.example.fragmentexample.util;


import android.util.Log;

import com.example.fragmentexample.BuildConfig;
import com.example.fragmentexample.constants.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    private static final String PREFIX = "mystudyapp_";

    public static void d(String tag, String msg) {
        if (Constants.LOGGING_LEVEL >= 2)
            Log.d(PREFIX + tag, filterPrivacyPolicy(msg));
    }

    public static void v(String tag, String msg) {
        if (Constants.LOGGING_LEVEL >= 3)
            Log.v(PREFIX + tag, filterPrivacyPolicy(msg));
    }

    public static void i(String tag, String msg) {
        Log.i(PREFIX + tag, filterPrivacyPolicy(msg));
    }

    public static void e(String tag, String msg) {
        Log.e(PREFIX + tag, filterPrivacyPolicy(msg));
    }

    private static String filterPrivacyPolicy(String msg) {
        String result;
        if (!BuildConfig.DEBUG) {
            Pattern pattern;
            Matcher matcher;
            pattern = Pattern.compile("0x[0-9a-zA-Z]{60}", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(msg);
            result = matcher.replaceAll("0x************************************************************");
            pattern = Pattern.compile("0x[0-9a-zA-Z]{36}", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(result);
            result = matcher.replaceAll("0x************************************");
        } else {
            result = msg;
        }
        return result;
    }
}
