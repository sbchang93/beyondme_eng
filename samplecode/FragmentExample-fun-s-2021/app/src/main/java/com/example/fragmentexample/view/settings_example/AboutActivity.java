package com.example.fragmentexample.view.settings_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.view.commonActivity.BaseUIActivity;

//public class AboutActivity extends AppCompatActivity {
public class AboutActivity extends BaseUIActivity {

    TextView tvAppVersionName;
    TextView tvOpenSourceLicensesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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

        tvOpenSourceLicensesText = findViewById(R.id.open_source_licenses_text);
        tvOpenSourceLicensesText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OpenSourceLicenseActivity.class);
                startActivity(intent);
            }
        });

    }
}