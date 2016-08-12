package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.PagerItemListTodoView;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.CustomGridView;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.GridAdapter;

/**
 * Created by YCJ on 2015/11/27.
 */
public class TodoListBehavior extends CoordinatorLayout.Behavior<PagerItemListTodoView> {

    private int mInitialOffset = -1;
    private int mMinOffset = -1;
    private int mTop;


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, PagerItemListTodoView child, int layoutDirection) {

        Log.i("lkl", "TodoListBehavior --- onLayoutChild");

        parent.onLayoutChild(child, layoutDirection);

        if (mInitialOffset == -1)
        {
            mInitialOffset = parent.getHeight()/2;
            child.offsetTopAndBottom(mInitialOffset);
            saveTop(mInitialOffset);
        }
        else
        {
            child.offsetTopAndBottom(mTop);
        }

        return true;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, PagerItemListTodoView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        Log.i("lkl", "TodoListBehavior --- onMeasureChild");
        CustomGridView customGridView = (CustomGridView) parent.getChildAt(0);
        if (null != customGridView)
        {
            int parentHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
            int height = customGridView.getHeight();
            int row = customGridView.getCount() / GridAdapter.NUM_COLUMS;
            if (row != 0)
            {
                //如果child还没有绘制好，高度仍然为0，则设置默认高度，即 1/2 parent高度
                if (height == 0)
                {
                    height = parentHeight / 2;
                }
                Log.i("lkl", "height = " + height);
                mMinOffset = height / row;
            }

            final int measuredHeight = parentHeight - heightUsed - mMinOffset;
            int childMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY);
            child.measure(parentWidthMeasureSpec, childMeasureSpec);
        }
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, PagerItemListTodoView child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i("lkl", "TodoListBehavior --- onStartNestedScroll");
        //We have to declare interest in the scroll to receive further events
        boolean isVertical = (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        //Only capture on the view currently being scrolled
        return isVertical && child == directTargetChild;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, PagerItemListTodoView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("lkl", "TodoListBehavior --- onNestedScroll");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, PagerItemListTodoView child, View target, int dx, int dy, int[] consumed) {
        /*Log.i("lkl", "TodoListBehavior --- onNestedPreScroll"
            + "; child.getTop() = " + child.getTop()
            + "; mInitialOffset = " + mInitialOffset
            + "; mMinOffset = " + mMinOffset);*/
        Log.i("lkl", "onNestedPreScroll -- dy = " + dy);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);


        boolean isScrollToTop = (child.getScrollY() == 0);


        //When not at the top, consume all scrolling for the card
        if (child.getTop() <= mInitialOffset && child.getTop() >= mMinOffset) {
            //Tell the parent what we've consumed
            //测试
            if (dy > 0 || isScrollToTop)
            {
                consumed[1] = MoveUtil.move(child, dy, mMinOffset, mInitialOffset);
                saveTop(child.getTop());
            }

        }
    }

    @Override
    public void onStopNestedScroll(final CoordinatorLayout parent, final PagerItemListTodoView child, View target) {
        Log.i("lkl", "TodoListBehavior --- onStopNestedScroll");
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

    private void scrollToYCoordinate(final CoordinatorLayout parent, final PagerItemListTodoView child, final int y, int duration){
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
