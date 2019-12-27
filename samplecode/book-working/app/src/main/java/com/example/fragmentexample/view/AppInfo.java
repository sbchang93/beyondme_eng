package com.example.fragmentexample.view;

// reference link
// : https://github.com/marcoRS/rxjava-essentials/blob/master/app/src/main/java/com/packtpub/apps/rxjava_essentials/example1/FirstExampleFragment.java

public class AppInfo implements Comparable<Object> {

    long mLastUpdateTime;

    String mName;

    String mIcon;

    public AppInfo(String name, String icon, long lastUpdateTime) {
        mName = name;
        mIcon = icon;
        mLastUpdateTime = lastUpdateTime;
    }

    public String getIcon() {
        return mIcon;
    }

    @Override public int compareTo(Object another) {
        AppInfo f = (AppInfo) another;
        return getClass().getName().compareTo(f.getClass().getName());
    }
}
