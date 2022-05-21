package com.example.swipeup.view.zoom_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.example.swipeup.R;

//Reference Home URL : https://intrepidgeeks.com/tutorial/android-program-design-realizes-the-function-of-image-zooming-in-and-out-example-of-zoom-controls-control

public class ZoomImageActivity03 extends AppCompatActivity {

    private LinearLayout llLayout;
    private ZoomControls zoomcontrols;
    private ImageView img;
    private int id=0;
    private int displayWidth;
    private int displayHeight;
    private float scaleWidth = 1;
    private float scaleHeight = 1;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image03);

        llLayout =(LinearLayout)findViewById(R.id.layout);
        //
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        displayWidth = dm.widthPixels;
        //      zoomControls
        displayHeight = dm.heightPixels;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        img =(ImageView)findViewById(R.id.imageView03);
        //zoom.hide();  zoomControls
        //zoom.show();  zoomControls
        zoomcontrols =(ZoomControls)findViewById(R.id.zoomcontrol);
        zoomcontrols.setIsZoomInEnabled(true);
        zoomcontrols.setIsZoomOutEnabled(true);
        //
        zoomcontrols.setOnZoomInClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int bmpWidth = mBitmap.getWidth();
                int bmpHeight = mBitmap.getHeight();
                //
                double scale = 1.25;
                //
                scaleWidth =(float)(scaleWidth*scale);
                scaleHeight =(float)(scaleHeight*scale);
                //       Bitmap
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap resizeBmp = Bitmap.createBitmap(mBitmap,0,0,bmpWidth,bmpHeight,matrix,true);
                img.setImageBitmap(resizeBmp);
            }
        });
        //
        zoomcontrols.setOnZoomOutClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                int bmpWidth = mBitmap.getWidth();
                int bmpHeight = mBitmap.getHeight();
                //
                double scale = 0.8;
                //
                scaleWidth =(float)(scaleWidth*scale);
                scaleHeight =(float)(scaleHeight*scale);
                //       Bitmap
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap resizeBmp = Bitmap.createBitmap(mBitmap,0,0,bmpWidth,bmpHeight,matrix,true);
                img.setImageBitmap(resizeBmp);
            }
        });
    }

    private Bitmap createResizedBitmap(Bitmap bitmap, int x, int y, int width, int height, double scale) {
        int bmpWidth = width;
        int bmpHeight = height;

        scaleWidth =(float)(scaleWidth*scale);
        scaleHeight =(float)(scaleHeight*scale);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap,0,0,bmpWidth,bmpHeight,matrix,true);
        return resizeBitmap;
    }

}