package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.toronto.mystudyapp.R;

public class ImageActivity extends Activity {

    private ImageView mPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        mPicture = (ImageView) findViewById(R.id.picture);
    }


    public void onClick (View v) {
        // 카메라 촬영 요청함. ( requestCode 100으로 요청하고 카메라 촬영이 끝나면 onActivityResult로 촬영한 사진을 보내줌 )
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 100); // 100 : requestCode


        //void startActivityForResult (Intent intent, int requestCode, Bundle options)
        // static final int PICK_CONTACT_REQUEST = 0;
        //startActivityForResult( new Intent(Intent.ACTION_PICK, new Uri("content://contacts")), PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;

        switch(requestCode) {
            case 100: // 100 : requestCode
                Bitmap photo = data.getExtras().getParcelable("data");
                mPicture.setImageBitmap(photo);
                break;

        }
    }
}
