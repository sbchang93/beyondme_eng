package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;

import java.util.Calendar;

public class DialogTest03Activity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    final static int SampleDialog = 0;
    final static int QuestionDialog = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test01);

        Button button = (Button)findViewById(R.id.call);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDialog(SampleDialog);
            }
        });

        Button button02 = (Button)findViewById(R.id.call02);
        button02.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDialog(QuestionDialog);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case SampleDialog:
                return new AlertDialog.Builder(DialogTest03Activity.this)
                        .setTitle("Notification.")
                        .setMessage("This message is open.")
                        .setIcon(R.drawable.icon)
                        .setPositiveButton("Close", null)
                        .show();
                        //.create(); // what is the difference between .show() and .create()

            case QuestionDialog:
                return new AlertDialog.Builder(DialogTest03Activity.this)
                        .setTitle("Question.")
                        .setMessage("Did you have something.")
                        .setIcon(R.drawable.icon)
                        .setPositiveButton("Yes, I did", null)
                        .setNegativeButton("No, I didn't", null)
                        .show();
                        //.create(); // what is the difference between .show() and .create()
        }
        return null;
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch(id) {
            case SampleDialog:
                break;
            case QuestionDialog:
                Calendar calendar = Calendar.getInstance();
                String stime = String.format("%dh %dm %ds",
                        calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
                dialog.setTitle(stime);
                break;
        }

    }
}
