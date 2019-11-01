package com.example.toronto.mystudyapp.model.data.local;
// Reference Link : https://github.com/googlesamples/android-architecture-components

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

//@Entity(tableName = "users")
//public class User {
//    @PrimaryKey
//    @NonNull
////    private String userid;
////    private String username;
//    public String userid;
//    public String username;
//
//    public User() {
//        userid = null;
//        username = null;
//    }
//
//    @Ignore
//    public User(String userName) {
//        userid = UUID.randomUUID().toString();
//        username = userName;
//    }
//
//    public User(String id, String userName) {
//        this.userid = id;
//        this.username = userName;
//    }
//
////    public String getId() {
////        return userid;
////    }
////
////    public String getUserName() {
////        return username;
////    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "userid='" + userid + '\'' +
//                ", username='" + username + '\'' +
//                '}';
//    }
//}


/**
 * Immutable model class for a User
 */
@Entity(tableName = "users")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userid")
    private String mId;

    @ColumnInfo(name = "username")
    private String mUserName;

    @Ignore
    public User(String userName) {
        mId = UUID.randomUUID().toString();
        mUserName = userName;
    }

    public User(String id, String userName) {
        this.mId = id;
        this.mUserName = userName;
    }

    public String getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + mId + '\'' +
                ", username='" + mUserName + '\'' +
                '}';
    }
}
