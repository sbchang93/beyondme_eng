package com.example.toronto.mystudyapp.model.local;
// Reference Link : https://github.com/googlesamples/android-architecture-components

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.toronto.mystudyapp.model.data.local.User;

/**
 * The Room database that contains the Users table
 */
@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    private static volatile UsersDatabase INSTANCE;

    //public abstract UserDao userDao();
    public abstract UserDao getUserDao();

    public static UsersDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UsersDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UsersDatabase.class, "MyUserSample.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}


//public abstract class AppDatabase extends RoomDatabase {
//
//    private static AppDatabase instance;
//
//    public static AppDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context, AppDatabase.class, "sample.db")
//                    .allowMainThreadQueries()
//                    //.fallbackToDestructiveMigration()
//                    .openHelperFactory(new SafeHelperFactory(   "char[]"   ))
//                    .build();
//        }
//        return instance;
//    }