package com.example.toronto.mystudyapp.view;
// Reference Link : https://github.com/googlesamples/android-architecture-components

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toronto.mystudyapp.Injection;
import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;
import com.example.toronto.mystudyapp.viewmodel.UserViewModel;
import com.example.toronto.mystudyapp.viewmodel.ViewModelFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = UserActivity.class.getSimpleName();

    private TextView mUserName;
    private EditText mUserNameInput;
    private Button mUpdateButton;
    private ViewModelFactory mViewModelFactory;
    private UserViewModel mViewModel;

    // To manage the memories of Observers when this app is closed or it is laid in other exceptional state.
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mUserName = findViewById(R.id.user_name);
        mUserNameInput = findViewById(R.id.user_name_input);
        mUpdateButton = findViewById(R.id.update_user);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel.class);
        mUpdateButton.setOnClickListener(v -> updateUserName());
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Subscribe to the emissions of the user name from the view model.
        // Update the user name text view, at every onNext emission.
        // In case of error, log the exception.
        mDisposable.add(mViewModel.getUserName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userName -> mUserName.setText(userName),
                        throwable -> Log.e(TAG, "Unable to update username", throwable)));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // clear all the subscriptions
        mDisposable.clear();
    }

    private void updateUserName() {
        String userName = mUserNameInput.getText().toString();
        // Disable the update button until the user name update has been done
        mUpdateButton.setEnabled(false);

        mDisposable.add(
            Observable.just("update")
                    .subscribeOn(Schedulers.io())  // to access Room DB
                    //.observeOn(AndroidSchedulers.mainThread()) // If it is opened, it will be dead because of accessing Main Thread.
                    .doOnNext(ret->mViewModel.updateUserName(userName))
                    .subscribe(s -> {
                        mUpdateButton.setEnabled(true);
                    }, t -> {
                        Logger.d(TAG, "error : " + t.getMessage());
                        t.printStackTrace();
                    })
        );

        // Subscribe to updating the user name.
        // Re-enable the button once the user name has been updated

//        mDisposable.add(mViewModel.updateUserName(userName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(() -> mUpdateButton.setEnabled(true),
//                        throwable -> Log.e(TAG, "Unable to update username", throwable)));
    }
}
