package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

import java.util.ArrayList;
import java.util.List;

//Reference Homepage Link : https://itpangpang.xyz/31?category=555744

public class RecyclerView2Activity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    RecyclerView rv;
    LinearLayoutManager llm;
    List<Integer> count = null;
    Button btn;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);

        rv = (RecyclerView)findViewById(R.id.rv);
        btn = (Button)findViewById(R.id.btn);

        /* LayoutManager는 총 3가지가 있는데 ListView를 쓰기위해
        LinearLayoutManager를 사용한다*/
        llm = new LinearLayoutManager(this);

        /* 이건 뭐 대충 찾아보니 RecyclerView의 사이즈를 고정시키는것 같다
        일단 기초만 할것이니 초반에는 다 true로 놓는다*/
        rv.setHasFixedSize(true);

        /* 이건 뭐 RecyclerView에 붙이는 것이다 ListView로 사용한다는 의미*/
        rv.setLayoutManager(llm);

        count = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                count.add(i);
                Logger.d(TAG, "Button Click Listener - i : " + i + ", " + "count :" + count.toString());
                rv.setAdapter(new RecyclerViewCounterAdapter(getApplication(), count, i));
            }
        });
    }
}
