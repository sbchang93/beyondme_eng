package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class LiveDataActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    MutableLiveData<String> myString = new MutableLiveData<>();

    // LiveData는 Read-Only에만 사용함.
    // public LiveData<String> myLivedataString;

    // List<String> stringList;
    //MutableLiveData<List<String>> liveData = new MutableLiveData<>();
    //liveData.postValue(tokenList);

    //SingleLiveData<TransactionReceipt> liveData = new SingleLiveData<>();
    //liveData.setValue(v);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_data);

        myString.setValue(null);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String str) {

                if (str != null) {
                    Log.i(TAG, "my String " );
                    Toast.makeText(LiveDataActivity.this, "my String :" + str , Toast.LENGTH_SHORT).show();
                    myString.removeObserver(this);
                } else {
                    Log.i(TAG, "null String " );
                    Toast.makeText(LiveDataActivity.this, "Null String" , Toast.LENGTH_SHORT).show();
                }
            }
        };

        Log.i(TAG, "myString.observeForever(observer) " );
        Toast.makeText(LiveDataActivity.this, "myString.observeForever(observer)" , Toast.LENGTH_SHORT).show();
        myString.observeForever(observer);

        myString.setValue("Hello");

        // 이것은 removeObserver 호출되었기 때문에 동작하지 않는다.
        myString.setValue("Again! Hello");

    }
}
