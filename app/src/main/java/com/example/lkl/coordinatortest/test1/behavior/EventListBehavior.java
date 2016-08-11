package com.example.lkl.coordinatortest.test1.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.lkl.coordinatortest.test1.ContentView;
import com.example.lkl.coordinatortest.test1.TitleView;

/**
 * Created by YCJ on 2015/11/27.
 */
public class EventListBehavior extends CoordinatorLayout.Behavior<ContentView> {

    private int mInitialOffset = -1;
    private int mMinOffset = -1;
    private int mTop;


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, ContentView child, int layoutDirection) {

        Log.i("lkl", "EventListBehavior --- onLayoutChild");

        parent.onLayoutChild(child, layoutDirection);

        TitleView titleView = (TitleView) parent.getChildAt(0);
        TextView textView2 = (TextView)titleView.getChildAt(1);

        if (titleView.getBottom() > 0 && mInitialOffset == -1)
        {
            mInitialOffset = titleView.getBottom();
            mMinOffset = mInitialOffset - textView2.getBottom();
            child.offsetTopAndBottom(mInitialOffset);
            saveTop(mInitialOffset);
        }
        else if (mInitialOffset != -1)
        {
            child.offsetTopAndBottom(mTop);
        }

        return true;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, ContentView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        Log.i("lkl", "EventListBehavior --- onMeasureChild");
        TitleView titleView = (TitleView) parent.getChildAt(0);
        TextView textView2 = (TextView)titleView.getChildAt(1);
        //final int measuredHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec) - heightUsed - titleView.getHeight() / 6;
        final int measuredHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec) - heightUsed - titleView.getHeight() + textView2.getBottom();
        int childMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY);
        child.measure(parentWidthMeasureSpec, childMeasureSpec);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, ContentView child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i("lkl", "EventListBehavior --- onStartNestedScroll");
        //We have to declare interest in the scroll to receive further events
        boolean isVertical = (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        //Only capture on the view currently being scrolled
        return isVertical && child == directTargetChild;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, ContentView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("lkl", "EventListBehavior --- onNestedScroll");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, ContentView child, View target, int dx, int dy, int[] consumed) {
        Log.i("lkl", "EventListBehavior --- onNestedPreScroll"
            + "; child.getTop() = " + child.getTop()
            + "; mInitialOffset = " + mInitialOffset
            + "; mMinOffset = " + mMinOffset);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        //When not at the top, consume all scrolling for the card
        if (child.getTop() <= mInitialOffset && child.getTop() >= mMinOffset) {
            //Tell the parent what we've consumed
            consumed[1] = MoveUtil.move(child, dy, mMinOffset, mInitialOffset);
            saveTop(child.getTop());
        }
    }

    @Override
    public void onStopNestedScroll(final CoordinatorLayout parent, final ContentView child, View target) {
        Log.i("lkl", "EventListBehavior --- onStopNestedScroll");
        super.onStopNestedScroll(parent, child, target);


        if (isGoingUp) {
            if (mInitialOffset - mTop > 60){
                scrollToYCoordinate(parent, child, mMinOffset, 200);
            } else {
                scrollToYCoordinate(parent, child, mInitialOffset, 80);
            }
        } else {
            if (mTop - mMinOffset > 60){
                scrollToYCoordinate(parent, child, mInitialOffset, 200);
            } else {
                scrollToYCoordinate(parent, child, mMinOffset, 80);
            }
        }
    }

    private void scrollToYCoordinate(final CoordinatorLayout parent, final ContentView child, final int y, int duration){
        final Scroller scroller = new Scroller(parent.getContext());
        scroller.startScroll(0, mTop, 0, y - mTop, duration);

        ViewCompat.postOnAnimation(child, new Runnable() {
            @Override
            public void run() {
                if (scroller.computeScrollOffset()) {
                    int delta = scroller.getCurrY() - child.getTop();
                    child.offsetTopAndBottom(delta);

                    saveTop(child.getTop());
                    parent.dispatchDependentViewsChanged(child);

                    // Post ourselves so that we run on the next animation
                    ViewCompat.postOnAnimation(child, this);
                }
            }
        });
    }



    private boolean isGoingUp;

    private void saveTop(int top){
        this.mTop = top;
        Log.i("lkl", "saveTop --- top = " + top);
        if (mTop == mInitialOffset){
            isGoingUp = true;
        } else if (mTop == mMinOffset){
            isGoingUp = false;
        }
    }


}
