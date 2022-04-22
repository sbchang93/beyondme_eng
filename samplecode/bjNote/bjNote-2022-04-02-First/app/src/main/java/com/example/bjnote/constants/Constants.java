package com.example.bjnote.constants;

import android.os.Environment;

public class Constants {
    public static final String ExternalAbsoluteStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String ExternalDownloadsFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    public static final String bjDefaultFileName = "bjNote.txt";
}
