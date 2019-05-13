package com.example.toronto.mystudyapp.view.presenter;

import android.view.View;

import com.example.toronto.mystudyapp.model.data.local.Task;

public class Presenter {
    String mString;

    public void onSaveClick(Task task) {
        mString = new String("Presenter");
    }

    public void onSaveClick(View view, Task task) {
        mString = new String("Presenter");
    }

    public void onLongClick(View view, Task task) {
        mString = new String("Presenter");
    }

    public void onCompletedChanged(Task task, boolean completed) {
        mString = new String("Presenter");
    }
}


