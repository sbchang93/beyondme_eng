package com.example.fragmentexample.view;

import java.util.List;


// reference link
// : https://github.com/marcoRS/rxjava-essentials/blob/master/app/src/main/java/com/packtpub/apps/rxjava_essentials/example1/FirstExampleFragment.java

public class ApplicationsList {

    private static ApplicationsList ourInstance = new ApplicationsList();

    private List<AppInfo> mList;

    private ApplicationsList() {
    }

    public void setList(List<AppInfo> appInfos) {
        this.mList = appInfos;
    }
    public List<AppInfo> getList() {
        return mList;
    }


    public static ApplicationsList getInstance() {
        return ourInstance;
    }
}
