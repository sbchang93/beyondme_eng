package com.example.toronto.mystudyapp.view;

// Reference Homepage URL (참조 홈페이지 링크)
// http://webnautes.tistory.com/1094

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

public class Dialog4Activity  extends AppCompatActivity implements DialogFragmentExample.OnCompleteListener {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onInputedData(String id, String pass) {
        Toast.makeText(this, id+"/"+pass, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog4);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    void show()
    {
        DialogFragment newFragment = new DialogFragmentExample();
        newFragment.show(getFragmentManager(), "dialog"); //"dialog"라는 태그를 갖는 프래그먼트를 보여준다.
    }


}
