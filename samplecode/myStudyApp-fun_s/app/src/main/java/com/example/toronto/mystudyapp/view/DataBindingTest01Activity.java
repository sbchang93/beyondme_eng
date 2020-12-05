package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.model.data.local.Task01;
import com.example.toronto.mystudyapp.model.data.local.User01;
import com.example.toronto.mystudyapp.databinding.ActivityDataBindingTest01Binding;
import com.example.toronto.mystudyapp.view.handler.MyHandlers;
import com.example.toronto.mystudyapp.view.presenter.Presenter;


// Reference Link :  https://developer.android.com/topic/libraries/data-binding/?hl=ko

public class DataBindingTest01Activity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    String mString;
    User01 mUser;
    ActivityDataBindingTest01Binding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test01);
        mUser = new User01("First Name", "Last Name");
        mBinding.setUser(mUser);

        MyHandlers hanlders = new MyHandlers();
        mBinding.setHandlers(hanlders);

        Task01 task = new Task01("task");
        mBinding.setTask(task);

        Presenter presenter = new Presenter();
        mBinding.setPresenter(presenter);

    }

    public void onTest (View v) {
        if(!mUser.mIsAdult) {
            mUser.mIsAdult = true;
            mBinding.textFirstName.setVisibility(View.VISIBLE);
        } else {
            mUser.mIsAdult = false;
            mBinding.textFirstName.setVisibility(View.INVISIBLE);
            mBinding.textFirstName.setVisibility(View.GONE);
        }
        mString = new String("DataBindingTest01Activity");
    }
}
