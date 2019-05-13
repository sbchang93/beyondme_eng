package com.example.toronto.mystudyapp.view;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;

public class DialogTest02Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test01);

        Button button = (Button)findViewById(R.id.call);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(DialogTest02Activity.this)
                .setTitle("Notification.")
                .setMessage("This message is open.")
                .setIcon(R.drawable.icon);
                build.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                build.show();
            }
        });

        Button button02 = (Button)findViewById(R.id.call02);
        button02.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(DialogTest02Activity.this)
                        .setTitle("Notification.")
                        .setMessage("This message should be read.")
                        .setIcon(R.drawable.icon);
                build.setCancelable(false)  // <= "Back Key" is inoperable.
                     .setPositiveButton("Close", null);
                build.show();
            }
        });

    }
}
