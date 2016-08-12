package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.GridView;

import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.behavior.CalendarViewBehavior;

/**
 * Created by LKL on 2016-8-9.
 */
@CoordinatorLayout.DefaultBehavior(CalendarViewBehavior.class)
public class CustomGridView extends GridView {
    private View mCurrentView;      //当前点击的itemView
    private int mCount;

    public CustomGridView(Context context)
    {
        super(context);
    }

    public void setCurrentView(View view)
    {
        mCurrentView = view;
    }

    public View getCurrentView()
    {
        return mCurrentView;
    }

    public void setCount(int count)
    {
        mCount = count;
    }

    public int getCount()
    {
        return mCount;
    }

    public void onDestory()
    {

    }
}
