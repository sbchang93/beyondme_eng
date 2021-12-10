package com.example.fragmentexample.view.actionbar_example;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentexample.R;

public class ActionBarActivity extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        //setTitle("");

        textView1 = findViewById(R.id.textView1);

        ActionBar action = getSupportActionBar();

        // Allowing ActionBar Customization
        action.setDisplayShowCustomEnabled(true);

        // Hide ActionBar components
        action.setDisplayHomeAsUpEnabled(false);
        action.setDisplayShowTitleEnabled(false);
        action.setDisplayShowHomeEnabled(false);

        // Set Custom actionbar into ActionBar
        View actionView = getLayoutInflater().inflate(R.layout.custom_actionbar, null);
        action.setCustomView(actionView);

        TextView textView3 = actionView.findViewById(R.id.textView3);
        textView3.setText("Custom ActionBar");
        textView3.setTextColor(Color.WHITE);

        Button button1 = (Button)actionView.findViewById(R.id.button1);
        ActionButtonListener btnListener = new ActionButtonListener();
        button1.setOnClickListener(btnListener);
    }

    class ActionButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(ActionBarActivity.this, "Button is pressed", Toast.LENGTH_SHORT).show();
        }
    }
}