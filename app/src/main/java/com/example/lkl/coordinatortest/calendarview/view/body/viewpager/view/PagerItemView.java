package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lkl.coordinatortest.calendarview.model.CalendarBean;
import com.example.lkl.coordinatortest.calendarview.view.body.OnCalendarListener;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.CustomGridView;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.GridAdapter;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.todoview.PagerItemListTodoView;

import java.util.List;


/**
 * Created by LKL on 2016-7-27.
 */
public class PagerItemView extends CoordinatorLayout implements AdapterView.OnItemClickListener {
    private Context mContext;
    private CustomGridView mGridView;
    private GridAdapter mAdapter;
    private PagerItemListTodoView mPlanLayout;   //日程布局
    private OnCalendarListener mListener;

    public PagerItemView(Context context)
    {
        this(context, null);
    }

    public PagerItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public PagerItemView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init()
    {
        if (null == mContext) return;
        initView();
        regEvent(true);
    }

    private void initView()
    {
        LayoutParams lp = null;
        try
        {
            //日历部分
            mGridView = new CustomGridView(mContext);
            if (null != mGridView)
            {
                mGridView.setGravity(Gravity.CENTER);
                mGridView.setNumColumns(GridAdapter.NUM_COLUMS);    //列数
                mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
                mGridView.setBackgroundColor(Color.parseColor("#F5F5F5"));
                mAdapter = new GridAdapter(mContext);
                if (null != mAdapter)
                {
                    mGridView.setAdapter(mAdapter);
                }

                lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                if (null != lp)
                {
                    this.addView(mGridView, lp);
                }
            }

            //日程部分
            mPlanLayout = new PagerItemListTodoView(mContext);
            if (null != mPlanLayout)
            {
                mPlanLayout.setBackgroundColor(Color.WHITE);
                lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (null != lp)
                {
                    this.addView(mPlanLayout, lp);
                }
            }


        }
        catch (Exception e)
        {

        } finally
        {
            lp = null;
        }
    }

    private void regEvent(boolean b)
    {
        if (null != mGridView)
        {
            mGridView.setOnItemClickListener(b?this:null);
        }
    }

    public void onDestory()
    {
        if (null != mGridView)
        {
            mGridView.onDestory();
            mGridView = null;
        }
        if (null != mAdapter)
        {
            mAdapter.onDestory();
            mAdapter = null;
        }

        if (null != mPlanLayout)
        {
            mPlanLayout.onDesotry();
            mPlanLayout = null;
        }

        mListener = null;
    }

    public void setData(List<CalendarBean> data)
    {
        if (null != mAdapter)
        {
            mAdapter.setData(data);
        }

        if (null != mGridView)
        {
            mGridView.setCount((null != data ? data.size() : 0));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (null != mListener)
        {
            mListener.onItemClick(parent, view, position, id);
        }
    }

    public void setOnItemClickListener(OnCalendarListener listener)
    {
        mListener = listener;
    }


    public void setLunarTitle(String lunarTitle)
    {
        if (null != mPlanLayout)
        {
            mPlanLayout.setLunarTitle(lunarTitle);
        }
    }

}
