package com.example.toronto.mystudyapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.viewmodel.NameViewModel;


// Reference Link : https://developer.android.com/topic/libraries/architecture/livedata?hl=ko#java

public class NameActivityForViewModel extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private NameViewModel mModel;
    TextView mNameTextView;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_for_view_model);

        mNameTextView = (TextView)findViewById(R.id.text01);

        Button button01 = (Button)findViewById(R.id.button01);
        button01.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                i++;
                String anotherName = "John Doe " + i;
                mModel.getCurrentName().setValue(anotherName);
            }
        });

        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        mModel.getCurrentName().observe(this, nameObserver);
    }

    final Observer<String> nameObserver = new Observer<String>() {
        public void onChanged(@Nullable final String newName) {
            mNameTextView.setText(newName);
        }
    };
}
