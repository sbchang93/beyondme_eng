package com.example.toronto.mystudyapp.model.data.local;
// Reference Link : https://github.com/googlesamples/android-architecture-components

import com.example.toronto.mystudyapp.UserDataSource;
import com.example.toronto.mystudyapp.model.local.UserDao;

import io.reactivex.Flowable;

/**
 * Using the Room database as a data source.
 */
public class LocalUserDataSource implements UserDataSource {

    private final UserDao mUserDao;

    public LocalUserDataSource(UserDao userDao) {
        mUserDao = userDao;
    }

    @Override
    public Flowable<User> getUser() {
        return mUserDao.getUser();
    }

    @Override
    public void insertUser(User user) {
        mUserDao.insertUser(user);
    }
//    public void insertOrUpdateUser(User user) {
//        mUserDao.insertUser(user);
//    }
//    public Completable insertOrUpdateUser(User user) {
//        return mUserDao.insertUser(user);
//    }


    @Override
    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }
}
