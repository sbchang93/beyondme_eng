package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;

public class DialogTest01Activity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test01);

        Button button = (Button)findViewById(R.id.call);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(DialogTest01Activity.this);
                build.setTitle("Notification.");
                build.setMessage("This Dialog Box is opened.");
                build.setIcon(R.drawable.icon);
                build.show();
            }
        });
    }
}
