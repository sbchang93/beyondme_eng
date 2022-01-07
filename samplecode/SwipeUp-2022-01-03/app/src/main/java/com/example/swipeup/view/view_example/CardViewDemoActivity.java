package com.example.swipeup.view.view_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.Fade;
import androidx.transition.Slide;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.swipeup.R;
import com.example.swipeup.SwipeUpApplication;
import com.example.swipeup.constants.Constants;
import com.example.swipeup.model.DemoModel;

// Reference Homepage URL : https://github.com/writtmeyer/recyclerviewdemo

public class CardViewDemoActivity extends AppCompatActivity {
    private static final String TAG = "CardViewDemoActivity";

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_demo);

// *** Animation Effect is Not Working. Maybe, Old Methods ***
// *** Animation Effect is Not Working. Maybe, Old Methods ***
// *** Animation Effect is Not Working. Maybe, Old Methods ***
//        getWindow().setAllowEnterTransitionOverlap(true);
//       getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setSharedElementEnterTransition(new Transition() {
//            @Override
//            public void captureStartValues(TransitionValues transitionValues) {
//
//            }
//
//            @Override
//            public void captureEndValues(TransitionValues transitionValues) {
//
//            }
//        });  // import android.transition.ChangeTransform;


        // getWindow().setExitTransition
        // getWindow().setEnterTransition
        // getWindow().setReturnTransition

// *** Animation Effect is Not Working. Maybe, Old Methods ***
// *** Animation Effect is Not Working. Maybe, Old Methods ***
// *** Animation Effect is Not Working. Maybe, Old Methods ***

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(new Transition() {
            @Override
            public void captureStartValues(TransitionValues transitionValues) {

            }

            @Override
            public void captureEndValues(TransitionValues transitionValues) {

            }
        });

// *** Animation Effect is Not Working. Maybe, Old Methods ***
// *** Animation Effect is Not Working. Maybe, Old Methods ***
// *** Animation Effect is Not Working. Maybe, Old Methods ***
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(new Transition() {
            @Override
            public void captureStartValues(TransitionValues transitionValues) {

            }

            @Override
            public void captureEndValues(TransitionValues transitionValues) {

            }
        });

// Parameter should be received from other activities.
//        Bundle extras = getIntent().getExtras();
//        int id = extras.getInt(Constants.KEY_ID);
        int id = 1;  // temporary value.
        DemoModel model = SwipeUpApplication.findById(id);

        setContentView(R.layout.activity_card_view_demo);
        cardView = (CardView) findViewById(R.id.card_detail);
        View innerContainer = cardView.findViewById(R.id.container_inner_item);
        innerContainer.setTransitionName(Constants.NAME_INNER_CONTAINER);
        TextView label = (TextView) cardView.findViewById(R.id.txt_label_item);
        TextView dateTime = (TextView) cardView.findViewById(R.id.txt_date_time);
        label.setText(model.label);
        String dateStr = DateUtils.formatDateTime(
                this,
                model.dateTime.getTime(),
                DateUtils.FORMAT_ABBREV_ALL);
        dateTime.setText(dateStr);
    }
}
