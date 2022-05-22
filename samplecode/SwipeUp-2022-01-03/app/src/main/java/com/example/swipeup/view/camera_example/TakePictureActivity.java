package com.example.swipeup.view.camera_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.swipeup.BuildConfig;
import com.example.swipeup.R;

import java.io.File;

//Reference Home URL : https://stackoverflow.com/questions/29777098/attempt-to-invoke-virtual-method-void-android-widget-imageview-setimagebitmapa
//Reference Home URL : https://developer.android.com/training/camera/photobasics?hl=ko#java
//Reference Home URL : https://bottlecok.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EA%B6%8C%ED%95%9C%EC%9A%94%EC%B2%AD-%EA%B6%8C%ED%95%9C%EC%84%A4%EC%A0%95-%ED%8D%BC%EB%AF%B8%EC%85%98%EC%B2%B4%ED%81%AC

// Caused by: android.os.FileUriExposedException: file:///storage/emulated/0/Download/image.jpg exposed beyond app through ClipData.Item.getUri(
// => Trouble shooting => https://rlg1133.tistory.com/53
//                     => https://mond-al.github.io/cachefile-share

public class TakePictureActivity extends AppCompatActivity {
    private final static String TAG = "TakePictureActivity";

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;

    public static final String ExternalDownloadsFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE_WITH_BOTTON_2 = 2;
    static final int REQUEST_IMAGE_CAPTURE_WITH_BOTTON_3 = 3;
    static final int REQUEST_IMAGE_CAPTURE_WITH_BOTTON_4 = 4;

    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }

        // openCamera();
    }


    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check that request code matches ours:
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            //Get our saved file into a bitmap object:
            //File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
            File file = new File(ExternalDownloadsFolderPath + File.separator + "image.jpg");
            Bitmap imageBitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1000, 700);
            imageView.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_WITH_BOTTON_2) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView2.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_WITH_BOTTON_3) {
            File file = new File(ExternalDownloadsFolderPath + File.separator + "image.jpg");
            Bitmap imageBitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1000, 700);
            imageView3.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_WITH_BOTTON_4) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView4.setImageBitmap(imageBitmap);
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth,
                                                     int reqHeight) {

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    public void capture_btn(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(ExternalDownloadsFolderPath + File.separator + "image.jpg");
        Uri photoUri = FileProvider.getUriForFile(this, "com.example.swipeup.fileprovider", file);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        // File file = new File(uri.getPath());
        //Uri photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", file);

        // Caused by: android.os.FileUriExposedException: file:///storage/emulated/0/Download/image.jpg exposed beyond app through ClipData.Item.getUri(
        // => Use FileProvider class
        //File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
        //File file = new File(ExternalDownloadsFolderPath + File.separator + "image.jpg");
        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
    }

    public void capture_btn2(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_WITH_BOTTON_2);
    }

    public void capture_btn3(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //todo: (NeedToFix) When we receive image data from Camera, There is an problem in onActivityResult(..) function.
        // => Can not get the capture image infromation from "image.jpg" by saved by Camera App.
        File file = new File(ExternalDownloadsFolderPath + File.separator + "image.jpg");
        Uri photoUri = FileProvider.getUriForFile(this, "com.example.swipeup.fileprovider", file);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_WITH_BOTTON_3);
    }

    public void capture_btn4(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //todo: (NeedToFix) When we receive image data from Camera, There is an problem in onActivityResult(..) function.
        // => Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'android.os.Bundle android.content.Intent.getExtras()' on a null object reference0
        File file = new File(ExternalDownloadsFolderPath + File.separator + "image.jpg");
        Uri photoUri = FileProvider.getUriForFile(this, "com.example.swipeup.fileprovider", file);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_WITH_BOTTON_4);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}