package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

public class DialogTest04Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test01);

        Button button = (Button)findViewById(R.id.call);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                try{
                    Thread.sleep(500);
                } catch (Exception e) {;}

                // Please act it out like some errors occur in this point ! ( Please imagine this situation! )
                boolean ErrorOccur = true;

                if (ErrorOccur) {
                    new AlertDialog.Builder(DialogTest04Activity.this)
                            .setTitle("Error Occurs.")
                            .setMessage("Some errors occur now. It will be closed now.")
                            .setIcon(R.drawable.icon)
                            .setPositiveButton("Finished", null)  // It is closed directly without user confirmation.
                            .show();
                    finish();
                }

                Toast.makeText(DialogTest04Activity.this, "The job is finished now", Toast.LENGTH_LONG).show();

            }
        });

        Button button02 = (Button)findViewById(R.id.call02);
        button02.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                try{
                    Thread.sleep(500);
                } catch (Exception e) {;}

                // Please act it out like some errors occur in this point ! ( Please imagine this situation! )
                boolean ErrorOccur = true;

                if (ErrorOccur) {
                    new AlertDialog.Builder(DialogTest04Activity.this)
                            .setTitle("Error Occurs.")
                            .setMessage("Some errors occur now. It will be closed now.")
                            .setIcon(R.drawable.icon)
                            .setPositiveButton("Finished",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            finish();
                                        }
                                    })
                            .show();
                } else {
                    Toast.makeText(DialogTest04Activity.this, "The job is finished now", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
