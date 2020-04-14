package com.happybom.testmodule003;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class MyTest{
    public void showMyToast(Context context, Resources res){
        //Toast.makeText(context, res.getString(R.string.warning), Toast.LENGTH_SHORT);
        Toast.makeText(context, "Hello, my Module 003", Toast.LENGTH_LONG);
    }
}
