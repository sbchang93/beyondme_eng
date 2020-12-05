package com.example.toronto.mystudyapp;
// Reference Link : https://github.com/googlesamples/android-architecture-components

import android.content.Context;

import com.example.toronto.mystudyapp.model.data.local.LocalUserDataSource;
import com.example.toronto.mystudyapp.model.local.UsersDatabase;
import com.example.toronto.mystudyapp.viewmodel.ViewModelFactory;

/**
 * Enables injection of data sources.
 */
public class Injection {

    public static UserDataSource provideUserDataSource(Context context) {
        UsersDatabase database = UsersDatabase.getInstance(context);
        return new LocalUserDataSource(database.getUserDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
