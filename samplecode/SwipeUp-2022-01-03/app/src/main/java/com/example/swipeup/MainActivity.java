package com.example.swipeup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipeup.view.activity_example.registerForActivityResult_Activity;
import com.example.swipeup.view.android_hero_samples.CanvasPaintActivity;
import com.example.swipeup.view.android_hero_samples.ClockActivity;
import com.example.swipeup.view.android_hero_samples.ColorMatrixActivity;
import com.example.swipeup.view.android_hero_samples.CustomViewGroupActivity;
import com.example.swipeup.view.android_hero_samples.CustomViewLayoutActivity;
import com.example.swipeup.view.android_hero_samples.CustomViewSamples01Activity;
import com.example.swipeup.view.AboutActivity;
import com.example.swipeup.view.android_hero_samples.DragViewGroupTest;
import com.example.swipeup.view.android_hero_samples.DragViewTest;
import com.example.swipeup.view.android_hero_samples.ImageMatrixTestActivity;
import com.example.swipeup.view.android_hero_samples.LayerActivity;
import com.example.swipeup.view.android_hero_samples.PathEffectViewTestActivity;
import com.example.swipeup.view.android_hero_samples.PixelsEffectActivity;
import com.example.swipeup.view.android_hero_samples.PrimaryColorActivity;
import com.example.swipeup.view.android_hero_samples.ReflectViewTestActivity;
import com.example.swipeup.view.android_hero_samples.XfermodeViewTestActivity;
import com.example.swipeup.view.animation_example.AnimationActivity;
import com.example.swipeup.view.animation_example.AnimationDrawableActivity;
import com.example.swipeup.view.animation_example.ViewPropertyAnimatorActivity;
import com.example.swipeup.view.camera_example.TakePictureActivity;
import com.example.swipeup.view.custom_view.CustomLoginButtonActivity;
import com.example.swipeup.view.custom_view.LinedEditTextActivity;
import com.example.swipeup.view.custom_view.VolumeControlActivity;
import com.example.swipeup.view.scroller_example.ScrollerMainActivity;
import com.example.swipeup.view.sliding_up_pannel.DemoActivity;
import com.example.swipeup.view.swipe_up_panel.SwipeUpActivity;
import com.example.swipeup.view.touch_example.MoveLoggerActivity;
import com.example.swipeup.view.touch_example.MultitouchActivity;
import com.example.swipeup.view.touch_example.TouchDelegateActivity;
import com.example.swipeup.view.touch_example.TouchForwardActivity;
import com.example.swipeup.view.touch_example.TouchInterceptActivity;
import com.example.swipeup.view.touch_example.TouchListenerActivity;
import com.example.swipeup.view.touch_example.TwoDimensionGestureScrollActivity;
import com.example.swipeup.view.touch_example.TwoDimensionScrollActivity;
import com.example.swipeup.view.view_example.CanvasActivity;
import com.example.swipeup.view.view_example.CardViewDemoActivity;
import com.example.swipeup.view.view_example.PaintActivity;
import com.example.swipeup.view.view_example.TextViewActivity;
import com.example.swipeup.view.zoom_image.ZoomImageActivity;
import com.example.swipeup.view.zoom_image.ZoomImageActivity02;
import com.example.swipeup.view.zoom_image.ZoomImageActivity03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;
    private final int MY_PERMISSIONS_REQUEST_STORAGE = 1002;

    private ListView mListView;
    private ArrayList<Map<String, Object>> mDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listview);
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            Map<String, Object> map = (Map<String, Object>) parent.getAdapter().getItem(position);
            Intent intent = (Intent) map.get("intent");
            startActivity(intent);
        });
        MyAdapter adapter = new MyAdapter(mDataList);
        mListView.setAdapter(adapter);

        // addItem (title, desc, cls)

        addItem("ZoomImageActivity", "ZoomImageActivity Description", ZoomImageActivity.class);
        addItem("ZoomImageActivity02", "ZoomImageActivity02 Description", ZoomImageActivity02.class);
        addItem("ZoomImageActivity03", "ZoomImageActivity03 Description", ZoomImageActivity03.class);

        addItem("TakePictureActivity", "TakePictureActivity Description", TakePictureActivity.class);
        addItem("registerForActivityResult_Activity", "registerForActivityResult_Activity Description", registerForActivityResult_Activity.class);


        addItem("SlidingUpPanel DemoActivity", "SlidingUpPanel DemoActivity Description", DemoActivity.class);
        addItem("SwipeUpActivity", "SwipeUpActivity Description", SwipeUpActivity.class);

        addItem("AboutActivity", "AboutActivity Description", AboutActivity.class);

        addItem("VolumeControlActivity", "VolumeControlActivity Description", VolumeControlActivity.class);
        addItem("LinedEditTextActivity", "LinedEditTextActivity Description", LinedEditTextActivity.class);
        addItem("PaintActivity", "PaintActivity Description", PaintActivity.class);
        addItem("CustomLoginButtonActivity", "CustomLoginButtonActivity Description", CustomLoginButtonActivity.class);
        addItem("CardViewDemoActivity", "CardViewDemoActivity Description", CardViewDemoActivity.class);
        addItem("AnimationActivity", "AnimationActivity Description", AnimationActivity.class);
        addItem("AnimationDrawableActivity", "AnimationDrawableActivity Description", AnimationDrawableActivity.class);
        addItem("ViewPropertyAnimatorActivity", "ViewPropertyAnimatorActivity Description", ViewPropertyAnimatorActivity.class);

        addItem("CanvasActivity", "CanvasActivity Description", CanvasActivity.class);
        addItem("TextViewActivity", "TextViewActivity Description", TextViewActivity.class);

        addItem("MoveLoggerActivity", "MoveLoggerActivity Description", MoveLoggerActivity.class);
        addItem("TouchListenerActivity", "TouchListenerActivity Description", TouchListenerActivity.class);
        addItem("TouchDelegateActivity", "TouchDelegateActivity Description", TouchDelegateActivity.class);
        addItem("TouchForwardActivity", "TouchForwardActivity Description", TouchForwardActivity.class);
        addItem("TwoDimensionScrollActivity", "TwoDimensionScrollActivity Description", TwoDimensionScrollActivity.class);
        addItem("TwoDimensionGestureScrollActivity", "TwoDimensionGestureScrollActivity Description", TwoDimensionGestureScrollActivity.class);
        addItem("MultitouchActivity", "MultitouchActivity Description", MultitouchActivity.class);
        addItem("TouchInterceptActivity", "TouchInterceptActivity Description", TouchInterceptActivity.class);

        addItem("ScrollerMainActivity", "ScrollerMainActivity Description", ScrollerMainActivity.class);


        // Andorid Hero Samples
        addItem("CustomViewSamples01Activity", "CustomViewSamples01Activity Description", CustomViewSamples01Activity.class);
        addItem("CustomViewGroupActivity", "CustomViewGroupActivity Description", CustomViewGroupActivity.class);
        addItem("CustomViewLayoutActivity", "CustomViewLayoutActivity Description", CustomViewLayoutActivity.class);
        addItem("DragViewTest", "DragViewTest Description", DragViewTest.class);
        addItem("DragViewGroupTest", "DragViewGroupTest Description", DragViewGroupTest.class);

        addItem("CanvasPaintActivity", "CanvasPaintActivity Description", CanvasPaintActivity.class);
        addItem("ClockActivity", "ClockActivity Description", ClockActivity.class);
        addItem("LayerActivity", "LayerActivity Description", LayerActivity.class);
        addItem("PrimaryColorActivity", "PrimaryColorActivity Description", PrimaryColorActivity.class);
        addItem("ColorMatrixActivity", "ColorMatrixActivity Description", ColorMatrixActivity.class);
        addItem("PixelsEffectActivity", "PixelsEffectActivity Description", PixelsEffectActivity.class);
        addItem("ReflectViewTestActivity", "ReflectViewTestActivity Description", ReflectViewTestActivity.class);
        addItem("PathEffectViewTestActivity", "PathEffectViewTestActivity Description", PathEffectViewTestActivity.class);
        addItem("ImageMatrixTestActivity", "ImageMatrixTestActivity Description", ImageMatrixTestActivity.class);
        addItem("XfermodeViewTestActivity", "XfermodeViewTestActivity Description", XfermodeViewTestActivity.class);


//         text1 = findViewById(R.id.textView1);
//         checkPermission();

    }

    private void addItem(String title, String desc, Class cls) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("desc", desc);
        map.put("intent", new Intent(this, cls));
        mDataList.add(map);
    }

    public static class MyAdapter extends BaseAdapter {
        private final List<Map<String, Object>> mData;

        public MyAdapter(List<Map<String, Object>> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myadapter, parent, false);

                holder = new ViewHolder();
                holder.title = convertView.findViewById(R.id.title_text);
                holder.description = convertView.findViewById(R.id.description_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Map<String, Object> twoLineMenu = (Map<String, Object>) getItem(position);
            holder.title.setText((String) twoLineMenu.get("title"));
            holder.description.setText((String) twoLineMenu.get("desc"));

            // Animation Effect
            // If I set duration, I can see the animation effect.
            ObjectAnimator.ofFloat(convertView, "alpha", 0.0f, 1f)
                    .setDuration(1000)
                    .start();

            return convertView;
        }


        static class ViewHolder {
            TextView title;
            TextView description;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "외부 저장소 사용을 위해 읽기/쓰기 필요", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA:
            case MY_PERMISSIONS_REQUEST_STORAGE: {
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
