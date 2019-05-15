package com.example.toronto.mystudyapp.model.data.local;

public class User01 {
    public final String mFirstName;
    public final String mLastName;
    public boolean mIsAdult=false;

    public User01(String firstName, String lastName) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }
}

/*
public class User01 {
    private final String mFirstName;
    private final String mLastName;

    public User01(String firstName, String lastName) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getlastName() {
        return this.mLastName;
    }

}
*/


