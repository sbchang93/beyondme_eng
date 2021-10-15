package com.example.fragmentexample.view.settings_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreference;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;

import static com.example.fragmentexample.constants.Constants.emailKey;
import static com.example.fragmentexample.constants.Constants.memoKey;
import static com.example.fragmentexample.constants.Constants.nameKey;
import static com.example.fragmentexample.constants.Constants.phoneKey;

// Reference URLs
// https://kumgo1d.tistory.com/31

public class SettingsActivity extends AppCompatActivity {

    private static final int SHOW_SETTINGS_PREFERENCE = 1;

    TextView mtvName;
    TextView mtvPhone;
    TextView mtvEmail;
    TextView mtvMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean bChecked = PreferenceUtils.getInstance().getBooleanWithKey(this, nameKey);
        mtvName = findViewById(R.id.textview_name);
        mtvName.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);

        bChecked = PreferenceUtils.getInstance().getBooleanWithKey(this, phoneKey);
        mtvPhone = findViewById(R.id.textview_phone);
        mtvPhone.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);

        bChecked = PreferenceUtils.getInstance().getBooleanWithKey(this, emailKey);
        mtvEmail = findViewById(R.id.textview_email);
        mtvEmail.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);

        bChecked = PreferenceUtils.getInstance().getBooleanWithKey(this, memoKey);
        mtvMemo = findViewById(R.id.textview_memo);
        mtvMemo.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);
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