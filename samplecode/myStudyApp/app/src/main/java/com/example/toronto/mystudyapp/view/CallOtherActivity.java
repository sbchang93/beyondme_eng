package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;

import java.io.File;

public class CallOtherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_other);

        findViewById(R.id.web).setOnClickListener(mClickListener);
        findViewById(R.id.dial).setOnClickListener(mClickListener);
        findViewById(R.id.picture).setOnClickListener(mClickListener);
        findViewById(R.id.other).setOnClickListener(mClickListener);
        findViewById(R.id.other_listview).setOnClickListener(mClickListener);
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()) {
                case R.id.web:
//                    String url ="http://www.google.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);

                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    startActivity(intent) ;
                    break;

                case R.id.dial:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:015-123-4567"));
                    startActivity(intent) ;
                    break;

                case R.id.picture:
                    //ms06s.png, samples_wtr.jpg
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/samples_wtr.jpg");
                    if (file == null || !file.exists()) {
                        return;
                    }
                    intent=new Intent(Intent.ACTION_VIEW, FileProvider.getUriForFile(CallOtherActivity.this, "com.example.toronto.mystudyapp.fileprovider", file));
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);

//                    String url = Environment.getExternalStorageDirectory().getAbsolutePath() + "/samples_wtr.jpg";
//                    Uri uri = Uri.fromFile(new File(url));
//                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    intent.setDataAndType(uri, "image/jpeg");
//                    startActivity(intent) ;

//                    Uri uri = FileProvider.getUriForFile(CallOtherActivity.this, "com.example.toronto.mystudyapp.fileprovider", new File("/sdcard/samples_wtr.jpg"));
//                    intent = new Intent(Intent.ACTION_VIEW, uri);
//                    intent.setDataAndType(uri, "image/jpeg");
//                    startActivity(intent) ;

//                    // error
//                    intent = new Intent(Intent.ACTION_VIEW);
//                    Uri uri = Uri.fromFile(new File("/sdcard/samples_wtr.jpg")); <= die.
//                    intent.setDataAndType(uri, "image/jpeg");
//                    startActivity(intent) ;

//                    File file = new File("/storage/emulated/0/test.txt");
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.fromFile(file), "text/*");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent); // Crashes on this line

                    break;

                case R.id.other:
                    intent = new Intent(Intent.ACTION_MAIN);
                    intent.setComponent(new ComponentName("com.example.toronto.mystudyapp", "com.example.toronto.mystudyapp.view.RecyclerViewActivity"));
                    //intent.setClassName("com.example.toronto.mystudyapp", "com.example.toronto.mystudyapp.view.RecyclerViewActivity");
                    startActivity(intent) ;
                    break;

                case R.id.other_listview:
                    intent = new Intent(Intent.ACTION_MAIN);
                    intent.setComponent(new ComponentName("com.example.toronto.mystudyapp", "com.example.toronto.mystudyapp.view.ListViewActivity"));
                    //intent.setClassName("com.example.toronto.mystudyapp", "com.example.toronto.mystudyapp.view.RecyclerViewActivity");
                    startActivity(intent) ;
                    break;
            }


        }
    };
}
