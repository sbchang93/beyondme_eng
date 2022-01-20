package com.example.swipeup.view.swipe_up_panel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.swipeup.R;
import com.example.swipeup.view.common_activity.BaseUIActivity;
import com.example.swipeup.view_utils.swipeuppanel.SwipeUpPanelLayout;
import com.example.swipeup.view_utils.swipeuppanel.SwipeUpPanelLayout.SwipePanelState;
import java.util.Arrays;
import java.util.List;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel

public class SwipeUpActivity  extends BaseUIActivity {
    private static final String TAG = "SwipeUpActivity";

    private SwipeUpPanelLayout mSwipeUpPanelLayout;

    List<String> listData = Arrays.asList(
            "Start Test data1", "data2 ...", "data3 ***", "data4 ###", "data5", "data6", "data7", "data8 EEEEEE", "data9", "data10",
            "data11", "data12 GGGGG", "data13", "data14", "data15 AAA", "data16", "data17", "data18 BBB", "data19", "data20",
            "data21 CCC", "data22", "data23 DDD", "data24", "data25", "data26", "data27", "data28", "data29", "data30 End ...",
            "data31 CCC", "data32", "data33 DDD", "data34", "data35", "data36", "data37", "data38", "data39", "data40 End ...",
            "data41 CCC", "data42", "data43 DDD", "data44", "data45", "data46", "data47", "data48", "data49", "data50 End ..."
    );


    // id name in activity_swipe_up.xml
    // @+id/swipe_up_layout
    // @+id/main_textview1
    // @+id/swipe_dragView
    // @+id/swipe_list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_up);

        setTitle("SwipeUpActivity");

        ListView listView = findViewById(R.id.swipe_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SwipeUpActivity.this, "onItemClick - Item is clicked!!!", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listData);

        listView.setAdapter(arrayAdapter);

        mSwipeUpPanelLayout = (SwipeUpPanelLayout) findViewById(R.id.swipe_up_layout);
        mSwipeUpPanelLayout.addPanelSwipeListener(new SwipeUpPanelLayout.SwipePanelListener() {
            @Override
            public void onPanelSwipe(View panel, float swipeOffset) {
                Log.i(TAG, "onPanelSwipe, offset " + swipeOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SwipePanelState previousState, SwipePanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });

        mSwipeUpPanelLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeUpPanelLayout.setPanelState(SwipePanelState.COLLAPSED);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}