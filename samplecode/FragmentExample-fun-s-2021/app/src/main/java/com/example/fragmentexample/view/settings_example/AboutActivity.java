package com.example.fragmentexample.view.settings_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.commonActivity.BaseUIActivity;

//public class AboutActivity extends AppCompatActivity {
public class AboutActivity extends BaseUIActivity {

    TextView tvAppVersionName;
    TextView tvPrivacyNoticeText;
    TextView tvPermissionText;
    TextView tvOpenSourceLicensesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle("About Fragment Example");

        // ActionBar's Back Press key
        // Inherited from BaseUIActivity class    <===  Supporting ActionBar's Back Press key.

        tvAppVersionName = findViewById(R.id.app_version_text);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tvAppVersionName.setText("Ver : " + packageInfo.versionName);

            //packageInfo = getPackageManager().getPackageInfo("com.android.settings", 0);
            packageInfo = getPackageManager().getPackageInfo("com.google.android.youtube", 0);
            tvAppVersionName.append("\n");
            tvAppVersionName.append("youtube - Ver : " + packageInfo.versionName);
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //privacy_notice_text
        tvPrivacyNoticeText = findViewById(R.id.privacy_notice_text);
        tvPrivacyNoticeText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrivacyNoticeActivity.class);
                startActivity(intent);
            }
        });

        tvPermissionText = findViewById(R.id.permission_text);
        tvPermissionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PermissionActivity.class);
                startActivity(intent);
            }
        });

        tvOpenSourceLicensesText = findViewById(R.id.open_source_licenses_text);
        tvOpenSourceLicensesText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OpenSourceLicenseActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.app_info:
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getApplicationInfo().packageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            default :
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}