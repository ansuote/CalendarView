package com.example.lkl.coordinatortest.calendarview.view.body.viewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;
import com.example.lkl.coordinatortest.calendarview.view.body.OnCalendarListener;

public class CalendarStateAdpater extends FragmentStatePagerAdapter {
	private OnCalendarListener mListener;

	public CalendarStateAdpater(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int position)
	{
		CalendarFragment fragment = CalendarFragment.create(position);
		if (null != fragment)
		{
			fragment.setOnCalendarListener(mListener);
		}
		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		if (null != mListener)
		{
			mListener.destroyItemData(container, position, object);
		}
		super.destroyItem(container, position, object);
	}

	@Override
	public int getCount()
	{
		return DataRespository.MAX_COUNT;	//模拟无限滑动
	}

	public void setOnCalendarListener(OnCalendarListener listener)
	{
		mListener = listener;
	}

	public void onDestory()
	{
		mListener = null;
	}

}
