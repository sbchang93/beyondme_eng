package com.example.bottomsheetbehaviorexample.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.bottomsheetbehaviorexample.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

// Reference URL : https://code.tutsplus.com/articles/how-to-use-bottom-sheets-with-the-design-support-library--cms-26031
// Reference GitHub : https://github.com/tutsplus/Android-BottomSheets

public class TutsPlusBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private CoordinatorLayout.Behavior mBehavior;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();

//        CoordinatorLayout.Behavior behavior = params.getBehavior();
//        if (behavior != null && behavior instanceof BottomSheetBehavior) {
//            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
//        }

        // 여기서 Parent의 Bottom Sheet Behaviro 정보를 얻어옮.
        mBehavior = params.getBehavior();
        if (mBehavior != null && mBehavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) mBehavior).addBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BottomSheetBehavior) mBehavior).removeBottomSheetCallback(mBottomSheetBehaviorCallback);
    }
}