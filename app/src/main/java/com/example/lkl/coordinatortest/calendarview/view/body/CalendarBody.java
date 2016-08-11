package com.example.lkl.coordinatortest.calendarview.view.body;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.CalendarFragment;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.CalendarStateAdpater;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.transformer.CurveTransformer;


/**
 * 可翻页日历部分
 * Created by LKL on 2016-7-27.
 */
public class CalendarBody extends LinearLayout{
    private Context mContext;
    private ViewPager mViewPager;
    private CalendarStateAdpater mPagerAdapter;
    private OnCalendarListener mListener;
    private OnCalendarPageChangeListener mPageCangeLisener;

    public CalendarBody(Context context)
    {
        this(context, null);
    }

    public CalendarBody(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CalendarBody(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }


    private void init()
    {
        if (null == mContext)   return;
        initView();
        regEvent(true);
    }

    private void initView()
    {
        LayoutParams lp = null;
        try
        {
            //viewpager
            mViewPager = new ViewPager(mContext);
            if (null != mViewPager)
            {
                mViewPager.setClipChildren(false);
                mViewPager.setPageMargin(10);     //设置Page间间距
                mViewPager.setOffscreenPageLimit(DataRespository.LIMIT_COUNT);
                //动态布局必须手动设置id，解决： android.content.res.Resources$NotFoundException: Unable to find resource ID #0xffffffff
                mViewPager.setId(0x1000);

                if (mContext instanceof Activity)
                {
                	FragmentManager fragmentManager = ((Activity)mContext).getFragmentManager();

                    mPagerAdapter = new CalendarStateAdpater(fragmentManager);
                    if (null != mPagerAdapter)
                    {
                        mViewPager.setAdapter(mPagerAdapter);
                        mViewPager.setPageTransformer(true, new CurveTransformer());
                    }
                }

                mViewPager.setCurrentItem(DataRespository.SELECT_ITEM);	//初始化定位为1000，初始化在一半区域

                lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                if (null != lp)
                {
                    lp.gravity = Gravity.CENTER;
                    this.addView(mViewPager, lp);
                }
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {

        }
    }

    private void regEvent(boolean b)
    {
    	if(null != mViewPager)
    	{
    		if (b)
    		{
    			mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    		}
    		else
    		{
    			mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
    		}
    	}

        mPagerAdapter.setOnCalendarListener(new OnCalendarListener() {
            @Override
            public void setItemData(int position, CalendarFragment fragment)
            {
                if (null != mListener)
                {
                    mListener.setItemData(position, fragment);
                }
            }

            @Override
            public void destroyItemData(ViewGroup container, int position, Object object)
            {
                if (null != mListener)
                {
                    mListener.destroyItemData(container, position, object);
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
        });
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
            if (null != mPageCangeLisener)
            {
                mPageCangeLisener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position)
        {
            if (null != mPageCangeLisener)
            {
                mPageCangeLisener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {
            if (null != mPageCangeLisener)
            {
                mPageCangeLisener.onPageScrollStateChanged(state);
            }
        }
    };

    public void setOnCalendarListenerListener(OnCalendarListener listener)
    {
        mListener = listener;
    }

    public void setOnPageChangeListener(OnCalendarPageChangeListener listener)
    {
        mPageCangeLisener = listener;
    }

    public void onDestory()
    {
        mViewPager = null;
        if (null != mPagerAdapter)
        {
            mPagerAdapter.onDestory();
            mPagerAdapter = null;
        }
        mListener = null;
    }

    public void setCurrentItem(int position)
    {
        if (null != mViewPager)
        {
            mViewPager.setCurrentItem(position);
        }
    }
}
