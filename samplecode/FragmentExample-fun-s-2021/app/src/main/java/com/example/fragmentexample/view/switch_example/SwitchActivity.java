package com.example.fragmentexample.view.switch_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.TextView;

import com.example.fragmentexample.R;

public class SwitchActivity extends AppCompatActivity {

    private TextView mSwitchOnText;
    private TextView mSwitchOffText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        final SwitchCompat toggle = findViewById(R.id.swith_button);
        mSwitchOnText = findViewById(R.id.swith_on);
        mSwitchOffText = findViewById(R.id.swith_off);

        // toggle 버튼 이벤트
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mSwitchOnText.setVisibility(View.VISIBLE);
                    mSwitchOffText.setVisibility(View.INVISIBLE);
                }
                else{
                    mSwitchOnText.setVisibility(View.INVISIBLE);
                    mSwitchOffText.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}