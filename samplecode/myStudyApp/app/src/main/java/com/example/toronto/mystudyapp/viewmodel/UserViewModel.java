package com.example.toronto.mystudyapp.viewmodel;
// Reference Link : https://github.com/googlesamples/android-architecture-components


import android.arch.lifecycle.ViewModel;

import com.example.toronto.mystudyapp.UserDataSource;
import com.example.toronto.mystudyapp.model.data.local.User;
import com.example.toronto.mystudyapp.view.UserActivity;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * View Model for the {@link UserActivity}
 */
public class UserViewModel extends ViewModel {

    private final UserDataSource mDataSource;

    private User mUser;

    public UserViewModel(UserDataSource dataSource) {
        mDataSource = dataSource;
    }

    /**
     * Get the user name of the user.
     *
     * @return a {@link Flowable} that will emit every time the user name has been updated.
     */
    public Flowable<String> getUserName() {
        return mDataSource.getUser()
                // for every emission of the user, get the user name
                .map(user -> {
                    mUser = user;
                    return user.getUserName();
                });

    }

    /**
     * Update the user name.
     *
     * @param userName the new user name
     * @return a {@link Completable} that completes when the user name is updated
     */
    public void updateUserName(final String userName) {
        // if there's no user, create a new user.
        // if we already have a user, then, since the user object is immutable,
        // create a new user, with the id of the previous user and the updated user name.
        mUser = mUser == null
                ? new User(userName)
                : new User(mUser.getId(), userName);
        //mDataSource.insertOrUpdateUser(mUser);
        mDataSource.insertUser(mUser);
    }
//    public Completable updateUserName(final String userName) {
//        // if there's no user, create a new user.
//        // if we already have a user, then, since the user object is immutable,
//        // create a new user, with the id of the previous user and the updated user name.
//        mUser = mUser == null
//                ? new User(userName)
//                : new User(mUser.getId(), userName);
//        return mDataSource.insertOrUpdateUser(mUser);
//    }
}