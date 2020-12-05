package com.example.toronto.mystudyapp;
// Reference Link : https://github.com/googlesamples/android-architecture-components

import com.example.toronto.mystudyapp.model.data.local.User;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Access point for managing user data.
 */
public interface UserDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<User> getUser();

    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param user the user to be inserted or updated.
     */
    //Completable insertOrUpdateUser(User user);
    //void insertOrUpdateUser(User user);
    void insertUser(User user);

    /**
     * Deletes all users from the data source.
     */
    void deleteAllUsers();
}
