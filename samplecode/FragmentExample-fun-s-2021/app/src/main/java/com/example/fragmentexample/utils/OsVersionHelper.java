package com.example.fragmentexample.utils;

import android.os.Build;
import android.util.Log;

import com.example.fragmentexample.BuildConfig;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class OsVersionHelper {
    private static final String TAG = "OsVersionHelper";
    private static String OS_VERSION_NAME = null;
    private static String mBuildVersionReleseName = Build.VERSION.RELEASE;
    private static String mOsVersionName;
    private static int mSdkVersionName;

    static {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        int sdkVersion = getSdkVersion();
        mSdkVersionName = sdkVersion;

        Optional<Field> versionName = Arrays.stream(fields)
                .filter(field -> {
                    try {
                        int value = field.getInt(null);

                        return sdkVersion == value;
                    } catch (Exception e) {
                        if (BuildConfig.DEBUG)
                            Log.e(TAG, "Failed to get field value", e);
                        else
                            Log.d(TAG, "Failed to get field value");
                        return false;
                    }
                }).findFirst();

        if (versionName.isPresent())
            OS_VERSION_NAME = versionName.get().getName();
        else
            Log.e(TAG, "Failed to find matching os version name");
        mOsVersionName = OS_VERSION_NAME;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOsVersionName() {
        if (OS_VERSION_NAME == null)
            throw new NullPointerException("No os version name found");
        return OS_VERSION_NAME;
    }

}