package com.example.swipeupexampleonly.view_utils.swipeuppanel;

import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.recyclerview.widget.RecyclerView;

// Git Hub Source => https://github.com/umano/AndroidSlidingUpPanel
//. Build Error Patch
//https://github.com/umano/AndroidSlidingUpPanel/pull/922/commits/7af57e82bfdac401ea1a0b6a105442d427e9c5e7
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/SlidingUpPanelLayout.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/AndroidPCanvasSaveProxy.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/CanvasSaveProxy.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/CanvasSaveProxyFactory.java
//https://github.com/umano/AndroidSlidingUpPanel/blob/7af57e82bfdac401ea1a0b6a105442d427e9c5e7/library/src/main/java/com/sothree/slidinguppanel/canvassaveproxy/LegacyCanvasSaveProxy.java


public class SwipeScrollableViewHelper {

    public int getScrollableViewScrollPosition(View scrollableView, boolean isSlidingUp) {
        if (scrollableView == null) return 0;
        if (scrollableView instanceof ScrollView) {
            if (isSlidingUp) {
                return scrollableView.getScrollY();
            } else {
                ScrollView sv = ((ScrollView) scrollableView);
                View child = sv.getChildAt(0);
                return (child.getBottom() - (sv.getHeight() + sv.getScrollY()));
            }
        } else if (scrollableView instanceof ListView && ((ListView) scrollableView).getChildCount() > 0) {
            ListView lv = ((ListView) scrollableView);
            if (lv.getAdapter() == null) return 0;
            if (isSlidingUp) {
                View firstChild = lv.getChildAt(0);
                // Approximate the scroll position based on the top child and the first visible item
                return lv.getFirstVisiblePosition() * firstChild.getHeight() - firstChild.getTop();
            } else {
                View lastChild = lv.getChildAt(lv.getChildCount() - 1);
                // Approximate the scroll position based on the bottom child and the last visible item
                return (lv.getAdapter().getCount() - lv.getLastVisiblePosition() - 1) * lastChild.getHeight() + lastChild.getBottom() - lv.getBottom();
            }
        } else if (scrollableView instanceof RecyclerView && ((RecyclerView) scrollableView).getChildCount() > 0) {
            RecyclerView rv = ((RecyclerView) scrollableView);
            RecyclerView.LayoutManager lm = rv.getLayoutManager();
            if (rv.getAdapter() == null) return 0;
            if (isSlidingUp) {
                View firstChild = rv.getChildAt(0);
                // Approximate the scroll position based on the top child and the first visible item
                return rv.getChildLayoutPosition(firstChild) * lm.getDecoratedMeasuredHeight(firstChild) - lm.getDecoratedTop(firstChild);
            } else {
                View lastChild = rv.getChildAt(rv.getChildCount() - 1);
                // Approximate the scroll position based on the bottom child and the last visible item
                return (rv.getAdapter().getItemCount() - 1) * lm.getDecoratedMeasuredHeight(lastChild) + lm.getDecoratedBottom(lastChild) - rv.getBottom();
            }
        } else {
            return 0;
        }
    }
}
