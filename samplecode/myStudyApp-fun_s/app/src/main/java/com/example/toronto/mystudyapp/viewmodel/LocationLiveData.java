package com.example.toronto.mystudyapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

// 소스 : http://hyuncb.tistory.com/4

public class LocationLiveData extends LiveData<Location> {
    private LocationManager locationManager;

//    private SimpleLocationListener listener = new SimpleLocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            setValue(location);
//        }
//    };

//    public LocationLiveData(Context context) {
//        locationManager = (LocationManager) context.getSystemService(
//                Context.LOCATION_SERVICE);
//    }
//
//    @Override
//    protected void onActive() {
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
//    }
//
//    @Override
//    protected void onInactive() {
//        locationManager.removeUpdates(listener);
//    }
}