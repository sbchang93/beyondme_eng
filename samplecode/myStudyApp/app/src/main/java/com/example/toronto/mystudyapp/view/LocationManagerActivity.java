package com.example.toronto.mystudyapp.view;

// Reference Homepage URL (참조 홈페이지 링크)
// http://biig.tistory.com/74?category=562387
// http://limkydev.tistory.com/65
// <permission android:name="android.permission.ACCESS_FINE_LOCATION" />
// <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.constants.LocationConstants;
import com.example.toronto.mystudyapp.util.Logger;

public class LocationManagerActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    LocationManager mLM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_manager);

        mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION => PackageManager.PERMISSION_GRANTED ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No - Permission ", Toast.LENGTH_LONG).show();
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        registerLocationUpdates();

    }



    private void registerLocationUpdates() {
        try {
            mLM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mLocationListener);
            mLM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListener);
        } catch(SecurityException e) {
        }

        //1000은 1초마다, 1은 1미터마다 해당 값을 갱신한다는 뜻으로, 딜레이마다 호출하기도 하지만
        //위치값을 판별하여 일정 미터단위 움직임이 발생 했을 때에도 리스너를 호출 할 수 있다.
    }
    public static class Control {
        public static final int RESP_GPS_NETWORK_CHANGED = 1;
        public static final int RESP_NETWORK_CHANGED = 2;

        static String toString(int control) {
            String ret;
            switch (control) {
                case RESP_GPS_NETWORK_CHANGED:
                    ret = "RESP_GPS_NETWORK_CHANGED";
                    break;
                case RESP_NETWORK_CHANGED:
                    ret = "RESP_NETWORK_CHANGED";
                    break;
                default:
                    ret = "Unknown control, c=" + control;
                    break;
            }
            return ret;
        }
    }

    Handler locationHandler = new Handler() {
        public void handleMessage(Message msg) {

            int control = msg.what;
            Logger.d(TAG, "process " + Control.toString(control));

            if (control == Control.RESP_GPS_NETWORK_CHANGED) {
                Toast.makeText(LocationManagerActivity.this, "control == Control.RESP_GPS_NETWORK_CHANGED", Toast.LENGTH_LONG).show();
            }
        }
    };

    private final LocationListener mLocationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Logger.d(TAG, "onLocationChanged : " + location.getProvider());
            Toast.makeText(LocationManagerActivity.this, "onLocationChanged : " + location.getProvider(), Toast.LENGTH_SHORT).show();

            if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
                //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.

                double longitude = location.getLongitude();    //경도
                double latitude = location.getLatitude();      //위도
                float accuracy = location.getAccuracy();       //신뢰도

                Logger.d(TAG, "GPS Network onLocationChanged, lon=" + longitude + " lat=" + latitude + " acc=" + accuracy);

                Message msg = locationHandler.obtainMessage(Control.RESP_GPS_NETWORK_CHANGED);
                Bundle data = msg.getData();
                data.putInt(LocationConstants.EXTRA_GPS_ACCURACY, (int) accuracy);
                data.putDouble(LocationConstants.EXTRA_GPS_LONGITUDE, longitude);
                data.putDouble(LocationConstants.EXTRA_GPS_LATITUDE, latitude);

                locationHandler.sendMessage(msg);

                Toast.makeText(LocationManagerActivity.this, "location.getProvider().equals(LocationManager.GPS_PROVIDER))", Toast.LENGTH_SHORT).show();
            } else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
                //Network 위치제공자에 의한 위치변화
                //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
                Toast.makeText(LocationManagerActivity.this, location.getProvider(), Toast.LENGTH_SHORT).show();
            } else {

            }

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Logger.d(TAG, "GPS Network onStatusChanged");
            Toast.makeText(LocationManagerActivity.this, "GPS Network onStatusChanged", Toast.LENGTH_SHORT).show();
        }

        public void onProviderEnabled(String provider) {
            Logger.d(TAG, "GPS Network onProviderEnabled");
            Toast.makeText(LocationManagerActivity.this, "GPS Network onStatusChanged", Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String provider) {
            Logger.d(TAG, "GPS Network onProviderDisabled");
            Toast.makeText(LocationManagerActivity.this, "GPS Network onStatusChanged", Toast.LENGTH_SHORT).show();
        }
    };



}
