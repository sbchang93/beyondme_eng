package com.example.toronto.mystudyapp.view.presenter;

import android.view.View;

import com.example.toronto.mystudyapp.model.data.local.Task01;

public class Presenter {
    String mString;

    public void onSaveClick(Task01 task) {
        mString = new String("Presenter");
    }

    public void onSaveClick(View view, Task01 task) {
        mString = new String("Presenter");
    }

    public void onLongClick(View view, Task01 task) {
        mString = new String("Presenter");
    }

    public void onCompletedChanged(Task01 task, boolean completed) {
        mString = new String("Presenter");
    }
}


