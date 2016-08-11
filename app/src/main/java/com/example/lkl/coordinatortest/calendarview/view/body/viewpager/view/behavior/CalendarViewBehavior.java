package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;

import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.PagerItemListTodoView;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.CustomGridView;

/**
 * Created by LKL on 2016-8-4.
 */
public class CalendarViewBehavior extends CoordinatorLayout.Behavior<CustomGridView>{
    private int mTop = 0;
    private int dependentViewTop = -1;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CustomGridView child, View dependency)
    {
        Log.i("lkl", "ContentViewBehavior --- layoutDependsOn");
        return dependency instanceof PagerItemListTodoView;
    }

    /**
     * 点击view的时候会触发该方法
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, CustomGridView child, int layoutDirection)
    {
        Log.i("lkl", "我已经进入onLayoutChild方法了 --- layoutDirection = " + layoutDirection);
        parent.onLayoutChild(child, layoutDirection);
        child.setY(mTop);
        //child.offsetTopAndBottom(mTop);
        return true;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, CustomGridView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed)
    {
        final int measuredHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec)/2;
        int childMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY);
        child.measure(parentWidthMeasureSpec, childMeasureSpec);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CustomGridView child, View dependency)
    {
        Log.i("lkl", "ContentViewBehavior --- onDependentViewChanged");

        if (-1 != dependentViewTop){
            int dy = dependency.getTop() - dependentViewTop;
            int top = child.getTop();

            //计算child上移的距离
            int topMovableDistance = 0;
            if (null != child)
            {
                View currentView = child.getCurrentView();
                if (null != currentView)
                {
                    topMovableDistance =  currentView.getTop();
                }
            }

            Log.i("lkl", "topMoveableDistance = " + topMovableDistance);

            if (dy > -top)
            {
                dy = -top;
            }

            //如果scrolling up 移动到选中的位置之后
            if (dy < -top - topMovableDistance)
            {
                dy = -top - topMovableDistance;
            }

            if (dy > 0)
            {
                if (dependency instanceof PagerItemListTodoView)
                {
                    if(((PagerItemListTodoView)dependency).getScrollY() != 0)   //没有滑动到顶部
                    {
                        Log.i("lkl", "没有滑动到顶部");
                        dy = 0;
                    }
                    else
                    {
                        Log.i("lkl", "滑动到顶部");
                    }
                }

            }
            child.offsetTopAndBottom(dy);
        }
        dependentViewTop = dependency.getTop();
        mTop = child.getTop();
        return true;

    }


}
