package com.example.fragmentexample.view.settings_example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.preference_screen.MySettingsFragment;

import java.util.ArrayList;

public class SettingsPreferenceActivity extends AppCompatActivity {
    private final static String TAG = "SettingsPreferenceActivity";

    SettingsPreferenceFragment mSettingsPreferenceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_preference);
        mSettingsPreferenceFragment = new SettingsPreferenceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer, mSettingsPreferenceFragment).commit();
    }

    public void clickUpdate(View v) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("FragmentExample");
        alertdialog.setMessage("Message for Information!");
        alertdialog.setPositiveButton(
                "Update",
                (dialog, which) -> {
                    Log.e(TAG, "Update Example Apk");
                    Toast.makeText(this, "Update Example Apk", Toast.LENGTH_SHORT).show();
                });
        alertdialog.setNegativeButton("Cancel",
                (dialog, which) -> {
                    Log.e(TAG, "Cancel Update for Example Apk");
                    Toast.makeText(this, "Cancel Update for Example Apk", Toast.LENGTH_SHORT).show();
                });

        AlertDialog alert = alertdialog.create();
        alert.show();
    }

    public void clickClose(View v) {
        Log.e(TAG, "Close Update Card");
        Toast.makeText(this, "Close Update Card", Toast.LENGTH_SHORT).show();
    }

}