package com.example.swipeup.view.activity_example;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.swipeup.R;

//Reference Home URL : https://onlyfor-me-blog.tistory.com/342
//Reference Home URL : https://hanyeop.tistory.com/135
//Reference Home URL : https://github.com/bumptech/glide

public class registerForActivityResult_Activity extends AppCompatActivity {
    private final static String TAG = "registerForActivityResult_Activity";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_activity_result);

        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLanuncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> activityResultLanuncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Log.e(TAG, "result : " + result);
                        Intent intent = result.getData();
                        Log.e(TAG, "intent : " + intent);
                        Uri uri = intent.getData();
                        Log.e(TAG, "uri : " + uri);


                        Glide.with(registerForActivityResult_Activity.this)
                                .load(uri)
                                .placeholder(R.drawable.bear)
                                .centerCrop()
                                .into(imageView);


//                        // Get the url address for image in the Internet. ( Copy and Paste image url link. )
//                        Glide.with(registerForActivityResult_Activity.this)
//                                .load("https://t1.daumcdn.net/cfile/tistory/994BEF355CD0313D05")
//                                .placeholder(R.drawable.bear)
//                                .into(imageView);

//                        Glide.with(registerForActivityResult_Activity.this)
//                                .load(uri)
//                                .placeholder(R.drawable.bear)
//                                .override(1000, 1000)
//                                .into(imageView);

//                        Glide.with(registerForActivityResult_Activity.this)
//                                .load(uri)
//                                .placeholder(R.drawable.bear)
//                                .centerCrop()
//                                .into(imageView);

                    }
                }
            });
}