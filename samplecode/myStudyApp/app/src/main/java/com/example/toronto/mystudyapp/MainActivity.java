package com.example.toronto.mystudyapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.toronto.mystudyapp.view.AudioActivity;
import com.example.toronto.mystudyapp.view.CallOtherActivity;
import com.example.toronto.mystudyapp.view.DataBindingTest01Activity;
import com.example.toronto.mystudyapp.view.Dialog2Activity;
import com.example.toronto.mystudyapp.view.Dialog3Activity;
import com.example.toronto.mystudyapp.view.Dialog4Activity;
import com.example.toronto.mystudyapp.view.Dialog5Activity;
import com.example.toronto.mystudyapp.view.DialogActivity;
import com.example.toronto.mystudyapp.view.DialogTest01Activity;
import com.example.toronto.mystudyapp.view.DialogTest02Activity;
import com.example.toronto.mystudyapp.view.DialogTest03Activity;
import com.example.toronto.mystudyapp.view.DialogTest04Activity;
import com.example.toronto.mystudyapp.view.DrawActivity;
import com.example.toronto.mystudyapp.view.DrawTestActivity;
import com.example.toronto.mystudyapp.view.HandlerActivity;
import com.example.toronto.mystudyapp.view.HashMapActivity;
import com.example.toronto.mystudyapp.view.ImageActivity;
import com.example.toronto.mystudyapp.view.ListView2Activity;
import com.example.toronto.mystudyapp.view.ListView3Activity;
import com.example.toronto.mystudyapp.view.ListViewActivity;
import com.example.toronto.mystudyapp.view.LiveDataActivity;
import com.example.toronto.mystudyapp.view.LocationManagerActivity;
import com.example.toronto.mystudyapp.view.NameActivityForViewModel;
import com.example.toronto.mystudyapp.view.ObserverActivity;
import com.example.toronto.mystudyapp.view.RecyclerViewActivity;
import com.example.toronto.mystudyapp.view.ReflectionActivity;
import com.example.toronto.mystudyapp.view.SaveState02Activity;
import com.example.toronto.mystudyapp.view.SaveState03Activity;
import com.example.toronto.mystudyapp.view.Test01Activity;
import com.example.toronto.mystudyapp.view.Test02_MyViewActivity;
import com.example.toronto.mystudyapp.view.TextViewActivity;
import com.example.toronto.mystudyapp.view.VideoActivity;
import com.example.toronto.mystudyapp.view.ViewActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just("")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s -> {
//                    //startActivity(new Intent(this, LiveDataActivity.class));
//                    //startActivity(new Intent(this, ObserverActivity.class));

                    startActivity(new Intent(this, RecyclerViewActivity.class));
                    startActivity(new Intent(this, ListViewActivity.class));
                    startActivity(new Intent(this, ListView2Activity.class));
                    startActivity(new Intent(this, HashMapActivity.class));
                    startActivity(new Intent(this, ViewActivity.class));
                    startActivity(new Intent(this, HandlerActivity.class));
                    startActivity(new Intent(this, LocationManagerActivity.class));
                    startActivity(new Intent(this, ListView3Activity.class));
                    startActivity(new Intent(this, TextViewActivity.class));
                    startActivity(new Intent(this, DrawActivity.class));
                    startActivity(new Intent(this, AudioActivity.class));
                    startActivity(new Intent(this, ImageActivity.class));
                    startActivity(new Intent(this, VideoActivity.class));
                    startActivity(new Intent(this, DialogActivity.class));
                    startActivity(new Intent(this, Dialog2Activity.class));
                    startActivity(new Intent(this, Dialog3Activity.class));
                    startActivity(new Intent(this, Dialog4Activity.class));
                    startActivity(new Intent(this, Dialog5Activity.class));
                    startActivity(new Intent(this, Test01Activity.class));
                    startActivity(new Intent(this, Test02_MyViewActivity.class));
                    startActivity(new Intent(this, SaveState02Activity.class));
                    startActivity(new Intent(this, SaveState03Activity.class));
                    startActivity(new Intent(this, CallOtherActivity.class));
                    startActivity(new Intent(this, DrawTestActivity.class));
                    startActivity(new Intent(this, ReflectionActivity.class));
                    startActivity(new Intent(this, DialogTest01Activity.class));
                    startActivity(new Intent(this, DialogTest02Activity.class));
                    startActivity(new Intent(this, DialogTest03Activity.class));
                    startActivity(new Intent(this, DialogTest04Activity.class));
                    startActivity(new Intent(this, DataBindingTest01Activity.class));
                    startActivity(new Intent(this, NameActivityForViewModel.class));

                    finish();
                });

        Handler handle = new Handler();
        Runnable callback = new Runnable() {
            public void run() {
                //startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                //Toast.makeText(MainActivity.this, "handler in MainActivity" , Toast.LENGTH_SHORT).show();
            }
        };

        handle.post(callback);


//        Observable.just("")
//                .delay(1, TimeUnit.SECONDS)
//                //.delay(1, TimeUnit.MILLISECONDS)
//                .subscribe(s -> {
//                    if (true) {
//                        startActivity(new Intent(this, RecyclerViewActivity.class));
//                    } else {
//                        startActivity(new Intent(this, EmptyActivity.class));
//                    }
//                    finish();
//                });

    }
}


/*

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.naver.blog.myhelloworld">

    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.WRITE_CONTACTS" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MyHelloWorld">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".absolute_layout_Activity" />
        <activity android:name=".linear_layout_Activity" />
        <activity android:name=".table_layout_Activity" />
        <activity android:name=".frame_layout_Activity" />
        <activity android:name=".relative_layout_Activity" />
        <activity android:name=".multiple_layout_Activity" />
        <activity android:name=".HelloWorldString_Activity" />
        <activity android:name=".list.grocerylist_Activity" />
        <activity android:name=".layout.Layout_Activity" />
        <activity android:name=".layout.BasicLayout_Activity" />
        <activity android:name=".layout.List_Activity" />
        <activity android:name=".layout.GridLayout_Activity" />
        <activity android:name=".layout.TabLayout_Activity" />
        <activity android:name=".layout.Adapters_Activity" />
        <activity android:name=".layout.ContactAdapter_Activity" />
        <activity android:name=".layout.TrackPointList_Activity" />
        <activity android:name=".layout.StyleSamples_Activity" />
        <activity android:name=".layout.Scratch_Activity" />
        <activity android:name=".layout.Scratch_Activity" />
        <activity android:name=".layout.DialogDisplay_Activity" />
        <activity android:name=".layout.Drawer_Activity" />
        <activity android:name=".MyListMain" />
        <activity android:name=".frameAnimation_Activity" />
        <activity android:name=".ImageTranslateActivity" />
        <activity android:name=".BroadcastReceiverDemoActivity" />
        <activity android:name=".TransparentMain_Activity" />
        <!--
<activity android:name=".TransparentMain_Activity"/>
        <activity
            android:label="@string/app_name"
            android:name=".TransparentMain_Activity" >
            <intent-filter >
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        -->

        <activity
            android:name=".Transparent_Activity"
            android:label="@string/app_name"
            android:theme="@style/Theme.Transparent" />





        <!-- android:theme="@android:style/Theme.Translucent.NoTitleBar" -->


        <!--
        <activity android:name=".layout."/>
        <activity android:name="."/>

        <activity android:name=".TransparentMain_Activity"/>
        <activity android:name=".Transparent_Activity"/>
        -->

        <activity
            android:name=".MyDrawActivity"
            android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" />
        <activity
            android:name=".MyAudioActivity"
            android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" />
        <activity
            android:name=".MyImageActivity"
            android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" />
        <activity
            android:name=".MyVideoActivity"
            android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" />
        <activity
            android:name=".MyTextViewActivity"
            android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" />
        <activity
            android:name=".MyListViewActivity"
            android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" />



        <receiver
            android:name=".MainWidget"
            android:label="setOnClickEventWidget">

            <!-- AppWidget?먯꽌 ?섏떊??action???뺤쓽 (蹂??덉젣?먯꽌???ъ슜 ?덊븿) -->
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
                <action android:name="android.appwidget.action.APPWIDGET_ENABLED" />
                <action android:name="android.appwidget.action.APPWIDGET_DISABLED" />
                <action android:name="android.appwidget.action.APPWIDGET_DELETED" />
            </intent-filter>

            <meta-data
                android:name="android.appwidget.provider"
                android:resource="@xml/widget_info" />
        </receiver>

        <receiver android:name=".MainWidget2">
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
            </intent-filter>

            <meta-data
                android:name="android.appwidget.provider"
                android:resource="@xml/main_widget2_info" />
        </receiver>
    </application>

</manifest><!--
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.naver.blog.myhelloworld">

        <application
            android:allowBackup="true"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:supportsRtl="true"
            android:theme="@style/AppTheme">
            <activity android:name=".MyHelloWorld">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />

                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>

            <activity
                android:name="MyDrawActivity"
                android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
                android:screenOrientation="portrait"
                android:launchMode="singleTop">
            </activity>

            <activity
                android:name="MyAudioActivity"
                android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
                android:screenOrientation="portrait"
                android:launchMode="singleTop">
            </activity>

            <activity
                android:name="MyImageActivity"
                android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
                android:screenOrientation="portrait"
                android:launchMode="singleTop">
            </activity>

            <activity
                android:name="MyVideoActivity"
                android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
                android:screenOrientation="portrait"
                android:launchMode="singleTop">
            </activity>

            <activity
                android:name="MyTextViewActivity"
                android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
                android:screenOrientation="portrait"
                android:launchMode="singleTop">
            </activity>

            <activity
                android:name="MyListViewActivity"
                android:configChanges="keyboardHidden|orientation|screenSize|fontScale"
                android:screenOrientation="portrait"
                android:launchMode="singleTop">
            </activity>

        </application>

    </manifest>
-->

 */
