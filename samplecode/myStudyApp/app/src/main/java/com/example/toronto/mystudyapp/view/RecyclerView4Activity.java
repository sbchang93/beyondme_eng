package com.example.toronto.mystudyapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.util.Logger;

import java.util.ArrayList;
import java.util.List;

// Reference Homepage Link :  https://itpangpang.xyz/44?category=555744
public class RecyclerView4Activity extends AppCompatActivity  {
    private final String TAG = this.getClass().getSimpleName();

    RecyclerView rv;
    LinearLayoutManager llm;
    List<String> count = null;
    Button btn;
    EditText et = null;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate ... ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view4);

        et = (EditText) findViewById(R.id.et);
        rv = (RecyclerView) findViewById(R.id.rv);
        btn = (Button) findViewById(R.id.btn);
        llm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);


        rv.setItemAnimator(new DefaultItemAnimator());


        count = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = et.getText().toString();
                count.add(text);
                rv.setAdapter(new RecyclerViewCounterAdapter4(getApplication(), count, text));
            }
        });

        final GestureDetector gestureDetector = new GestureDetector(RecyclerView4Activity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

//                Logger.d(TAG, "onInterceptTouchEvent");
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                Logger.d(TAG, "e.getX==>"+e.getX());
//                Logger.d(TAG, "e.getY==>"+e.getY());
//                Logger.d(TAG, "child==>"+child);


//                // Touch Event occur repeatly when you press one item in Adapter.
//                Logger.d(TAG,"onInterceptTouchEvent");
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if(child!=null) {
//                    Logger.d(TAG, "getChildAdapterPosition=>" + rv.getChildAdapterPosition(child));
//                    Logger.d(TAG,"getChildLayoutPosition=>"+rv.getChildLayoutPosition(child));
//                    Logger.d(TAG,"getChildViewHolder=>" + rv.getChildViewHolder(child));
//                }

//                // Touch Event occur repeatly when you press one item in Adapter.
//                Logger.d(TAG,"onInterceptTouchEvent");
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if(child!=null && gestureDetector.onTouchEvent(e)) {
//                    Logger.d(TAG, "getChildAdapterPosition=>" + rv.getChildAdapterPosition(child));
//                    Logger.d(TAG,"getChildLayoutPosition=>"+rv.getChildLayoutPosition(child));
//                    Logger.d(TAG,"getChildViewHolder=>" + rv.getChildViewHolder(child));
//                }

//                // Show Toast Popup
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if (child != null && gestureDetector.onTouchEvent(e)) {
//                    Toast.makeText(getApplication(), count.get(rv.getChildAdapterPosition(child)).toString(), Toast.LENGTH_SHORT).show();
//                }

                // This is complexed log information.
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    Logger.d(TAG, "AdapterPosition=>" + rv.findViewHolderForAdapterPosition(rv.getChildLayoutPosition(child)));
                    Logger.d(TAG, "LayoutPosition=>" + rv.findViewHolderForLayoutPosition(rv.getChildLayoutPosition(child)));
                    Logger.d(TAG, "getChildViewHolder=>" + rv.getChildViewHolder(child));
                    //Logger.d(TAG, "getChildViewHolder=>" + rv.getChildViewHolder(child).itemView);

                    //TextView tv = (TextView) rv.findViewHolderForAdapterPosition(rv.getChildLayoutPosition(child)).itemView.findViewById(R.id.tv);
                    //TextView tv = (TextView) rv.findViewHolderForLayoutPosition(rv.getChildLayoutPosition(child)).itemView.findViewById(R.id.tv);
                    TextView tv = (TextView) rv.getChildViewHolder(child).itemView.findViewById(R.id.tv);
                    Toast.makeText(getApplication(), tv.getText().toString(), Toast.LENGTH_SHORT).show();
                }


                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Logger.d(TAG, "onTouchEvent");
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Logger.d(TAG, "onRequestDisallowInterceptTouchEvent");
            }
        });
    }
}



