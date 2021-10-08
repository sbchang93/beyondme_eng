package com.example.fragmentexample.view.settings_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fragmentexample.R;

// Reference URLs
// https://kumgo1d.tistory.com/31

public class SettingsActivity extends AppCompatActivity {

    private static final int SHOW_SETTINGS_PREFERENCE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("Settings");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case 0:
                Intent intent = new Intent(this, SettingsPreferenceActivity.class);
                startActivityForResult(intent, SHOW_SETTINGS_PREFERENCE);
        }
        return false;
    }


}