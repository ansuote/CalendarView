package com.example.lkl.coordinatortest.calendarview.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.lkl.coordinatortest.calendarview.CalendarContract;
import com.example.lkl.coordinatortest.calendarview.presenter.CalendarPresenter;
import com.example.lkl.coordinatortest.calendarview.view.body.CalendarBody;
import com.example.lkl.coordinatortest.calendarview.view.body.OnCalendarListener;
import com.example.lkl.coordinatortest.calendarview.view.body.OnCalendarPageChangeListener;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.CalendarFragment;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.GridItemView;
import com.example.lkl.coordinatortest.calendarview.view.widget.drawable.CircleDrawable;


/**
 * 日历控件
 * Created by LKL on 2016-7-27.
 */
public class CalendarView extends LinearLayout implements CalendarContract.IView,
                                                            OnCalendarListener,
                                                            OnCalendarPageChangeListener,
                                                            CalendarTitle.OnTitleBtnClickListener
{
    private Context mContext;
    private CalendarTitle mTitleLayout;
    private CalendarBody mBodyLayout;
    private CalendarPresenter mPresenter;
    private CircleDrawable mNormalSelectDrawable;
    private CircleDrawable mTodayDrawable;

    public CalendarView(Context context)
    {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void initPresenter()
    {
        mPresenter = new CalendarPresenter(this);
        if (null != mPresenter)
        {
            mPresenter.start();
        }
    }

    private void init()
    {
        if (null == mContext) return;
        initView();
        initDarawable();
        regEvent(true);
        initPresenter();
    }

    private void initDarawable()
    {
        mNormalSelectDrawable = new CircleDrawable();
        if (null != mNormalSelectDrawable)
        {
            mNormalSelectDrawable.setStyle(Paint.Style.FILL);
            mNormalSelectDrawable.setColor(Color.RED);
        }

        mTodayDrawable = new CircleDrawable();
        if (null != mTodayDrawable)
        {
            mTodayDrawable.setStyle(Paint.Style.STROKE);
            mTodayDrawable.setColor(Color.RED);
        }
    }

    private void initView()
    {
        try
        {
            this.setOrientation(LinearLayout.VERTICAL);



            //titleLayout
            mTitleLayout = new CalendarTitle(mContext);
            if (null != mTitleLayout)
            {

                this.addView(mTitleLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            }

            //bodyLayout
            mBodyLayout = new CalendarBody(mContext);
            if (null != mBodyLayout)
            {

                this.addView(mBodyLayout, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            }

        } catch (Exception e)
        {

        }

    }

    private void regEvent(boolean b)
    {
        if (null != mBodyLayout)
        {
            mBodyLayout.setOnCalendarListenerListener(b ? this : null);
            mBodyLayout.setOnPageChangeListener(b ? this : null);
        }

        if (null != mTitleLayout)
        {
            mTitleLayout.setOnTitleClickListener(b ? this : null);
        }
    }

    @Override
    public void refershTitle(String title)
    {
        if (null != mTitleLayout && null != title)
        {
            mTitleLayout.setTitle(title);
        }
    }

    @Override
    public void clickNormalDate(View view)
    {
        if (null != view && null != mNormalSelectDrawable)
        {
            if (view instanceof GridItemView)
            {
                ((GridItemView)view).setNationBackground(mNormalSelectDrawable);
                ((GridItemView)view).setNationTextColor(Color.WHITE);
            }
        }
    }

    @Override
    public void clickScheduleDate(View view)
    {

    }

    @Override
    public void clearBackground(View view)
    {
        if (null != view && view instanceof GridItemView)
        {
            ((GridItemView)view).setNationBackground(null);
            ((GridItemView)view).setNationTextColor(Color.BLACK);
        }
    }

    @Override
    public void setTodayBackground(View view)
    {
        if (null != view && null != mTodayDrawable)
        {
            if (null != view && view instanceof GridItemView)
            {
                ((GridItemView)view).setNationBackground(mTodayDrawable);
                ((GridItemView)view).setNationTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public void setCurrentItem(int position)
    {
        if (null != mBodyLayout)
        {
            mBodyLayout.setCurrentItem(position);
        }
    }

    @Override
    public void onDestory()
    {
        if (null != mTitleLayout)
        {
            mTitleLayout.onDestory();
            mTitleLayout = null;
        }

        if (null != mBodyLayout)
        {
            mBodyLayout.onDestory();
            mBodyLayout = null;
        }

        if (null != mPresenter)
        {
            mPresenter.onDestory();
            mPresenter = null;
        }
    }


    @Override
    public void setItemData(int position, CalendarFragment fragment)
    {
        if (null != mPresenter)
        {
            mPresenter.setItemData(position, fragment);
        }
    }

    @Override
    public void destroyItemData(ViewGroup container, int position, Object object)
    {
        if (null != mPresenter)
        {
            mPresenter.destroyItemData(position);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (null != mPresenter)
        {
            mPresenter.clickItem(parent, view, position, id);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        if (null != mPresenter)
        {
            mPresenter.resetCurrentItem(position);
            mPresenter.loadTitleData(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public void onLeftBtnClick(View view)
    {
        if (null != mPresenter)
        {
            mPresenter.clickLeft();
        }
    }

    @Override
    public void onRightBtnClick(View view)
    {
        if (null != mPresenter)
        {
            mPresenter.clickRight();
        }
    }

    public void setLunarTitle(String lunarTitle)
    {
        if (null != mBodyLayout)
        {
            mBodyLayout.setLunarTitle(lunarTitle);
        }
    }
}
