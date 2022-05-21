package com.example.swipeup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
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

    private boolean needPermissions = true;


    private static String[] mPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private final int PERMISSION_CAMERA = 9001;
    private final int PERMISSION_STORAGE = 9002;
    private final int PERMISSION_CONTACT = 9003;
    private final int PERMISSION_READ_PHONE_STATE = 9004;

    private final int MY_PERMISSIONS_REQUEST = 1000;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;
    private final int MY_PERMISSIONS_REQUEST_STORAGE = 1002;

    @Override
    protected void onResume() {
        super.onResume();
        checkAndRequestPermission(this, mPermissions, MY_PERMISSIONS_REQUEST);
    }

    //Reference Home URL : https://blog.dramancompany.com/2015/11/%EB%A6%AC%EB%A9%A4%EB%B2%84%EC%9D%98-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-6-0-m%EB%B2%84%EC%A0%84-%EB%8C%80%EC%9D%91%EA%B8%B0/
    public boolean checkAndRequestPermission(Activity activity, String[] permissions, int permissionRequestCode) {
        String[] requiredPermissions = getRequiredPermissions(activity, permissions);
        //if (requiredPermissions.length > 0 && !activity.isDestroyedCompat()) {
        if (requiredPermissions.length > 0) {
//            int currentRequestCode = changePermissionCode(requiredPermissions, permissionRequestCode);
//            ActivityCompat.requestPermissions(activity, requiredPermissions, currentRequestCode);
            ActivityCompat.requestPermissions(activity, requiredPermissions, permissionRequestCode);
            return false;
        } else {
            return true;
        }
    }

    public String[] getRequiredPermissions(Context context, String... permissions) {
        List<String> requiredPermissions = new ArrayList<>();

        if (context == null)
            return requiredPermissions.toArray(new String[1]);

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                requiredPermissions.add(permission);
            }
        }
        return requiredPermissions.toArray(new String[requiredPermissions.size()]);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST:
                if (verifyPermissions(grantResults)) {
                    Toast.makeText(this, getString(R.string.permission_is_granted), Toast.LENGTH_SHORT).show();
                } else {
                    //String message = getRationalMessage(this, PERMISSION_CAMERA);
                    Toast.makeText(this, getString(R.string.permission_is_not_granted), Toast.LENGTH_SHORT).show();
                }
                break;

//            case MY_PERMISSIONS_REQUEST_CAMERA:
//                if (verifyPermissions(grantResults)) {
//                    Toast.makeText(this, getString(R.string.permission_is_granted), Toast.LENGTH_SHORT).show();
//                } else {
//                    String message = getRationalMessage(this, PERMISSION_CAMERA);
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case MY_PERMISSIONS_REQUEST_STORAGE:
//                if (verifyPermissions(grantResults)) {
//                    Toast.makeText(this, getString(R.string.permission_is_granted), Toast.LENGTH_SHORT).show();
//                } else {
//                    String message = getRationalMessage(this, PERMISSION_STORAGE);
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                }
//                break;
        }
    }

    public boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) return false;

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

//    int changePermissionCode(final @NonNull String[] permissions, final @IntRange(from = 0) int requestCode) {
//        int currentRequestCode = requestCode;
//        for (String permission : permissions) {
//            if (permission == Manifest.permission.CAMERA) {
//                currentRequestCode = MY_PERMISSIONS_REQUEST_CAMERA;
//                break;
//            } else if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE || permission == Manifest.permission.READ_EXTERNAL_STORAGE ) {
//                currentRequestCode = MY_PERMISSIONS_REQUEST_STORAGE;
//                break;
//            }
//        }
//        return currentRequestCode;
//    }


//    public String getRationalMessage(Context context, int code) {
//        switch (code) {
//            case PERMISSION_CAMERA:
//                return getRationalMessage(context, context.getString(R.string.permission_camera));
//            case PERMISSION_STORAGE:
//                return getRationalMessage(context, context.getString(R.string.permission_storage));
//            case PERMISSION_CONTACT:
//                return getRationalMessage(context, context.getString(R.string.permission_contact));
//            case PERMISSION_READ_PHONE_STATE:
//                return getRationalMessage(context, context.getString(R.string.permission_read_phone_state));
//        }
//        return "";
//    }
//
//    public static String getRationalMessage(Context context, String permission) {
//        return String.format(context.getString(R.string.request_permission), permission);
//    }

//    public boolean checkAndRequestPermission(Fragment fragment, int permissionRequestCode, String... permissions) {
//        String[] requiredPermissions = getRequiredPermissions(fragment.getContext() != null ?
//                fragment.getContext() : fragment.getActivity(), permissions);
//
//        if (requiredPermissions.length > 0 && fragment.isAdded()) {
//            fragment.requestPermissions(requiredPermissions, permissionRequestCode);
//            return false;
//        } else {
//            return true;
//        }
//    }



//Reference Home URL : https://bottlecok.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EA%B6%8C%ED%95%9C%EC%9A%94%EC%B2%AD-%EA%B6%8C%ED%95%9C%EC%84%A4%EC%A0%95-%ED%8D%BC%EB%AF%B8%EC%85%98%EC%B2%B4%ED%81%AC
//    ######***#####
//    ######***#####
//    ######***##### Simple Codes for Get Permissions
//
//    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;
//    private final int MY_PERMISSIONS_REQUEST_STORAGE = 1002;
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//                Toast.makeText(this, "Need Camera Permission", Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
//            }
//        }
//
//        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Toast.makeText(this, "외부 저장소 사용을 위해 읽기/쓰기 필요", Toast.LENGTH_SHORT).show();
//            }
//            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        String requestPermissionName = null;
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_CAMERA:
//                requestPermissionName = new String("Camera");
//                break;
//            case MY_PERMISSIONS_REQUEST_STORAGE:
//                requestPermissionName = new String("Storage");
//                break;
//            default:
//                break;
//        }
//
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, requestPermissionName + " is granted", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, requestPermissionName + " is not granted", Toast.LENGTH_SHORT).show();
//        }
//    }

}
