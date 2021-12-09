package com.example.fragmentexample.view.settings_example;

import static com.example.fragmentexample.constants.Constants.updateCardKey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;
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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
        PreferenceUtils.getInstance().setBoolean(this, updateCardKey, false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mSettingsPreferenceFragment.removeUpdateCard();
        ft.detach(mSettingsPreferenceFragment)
                .attach(mSettingsPreferenceFragment)
                .commit();
        Toast.makeText(this, "Close Update Card", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}