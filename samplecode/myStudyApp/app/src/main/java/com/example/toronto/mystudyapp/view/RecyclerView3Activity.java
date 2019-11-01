package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

import java.util.ArrayList;
import java.util.List;
//Reference Homepage Link : https://itpangpang.xyz/31?category=555744

public class RecyclerView3Activity extends AppCompatActivity  {
    private final String TAG = this.getClass().getSimpleName();

    RecyclerView rv;
    LinearLayoutManager llm;
    List<String> count = null;
    Button btn;
    EditText et = null;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Logger.d(TAG, "onCreate ... ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view3);

        et = (EditText)findViewById(R.id.et);
        rv = (RecyclerView)findViewById(R.id.rv);
        btn = (Button)findViewById(R.id.btn);
        llm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        count = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                text = et.getText().toString();
                count.add(text);
                rv.setAdapter(new RecyclerViewCounterAdapter3(getApplication(), count, text));
            }
        });
    }
}

