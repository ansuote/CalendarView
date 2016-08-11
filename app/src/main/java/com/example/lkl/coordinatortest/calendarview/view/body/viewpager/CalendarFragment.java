package com.example.lkl.coordinatortest.calendarview.view.body.viewpager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lkl.coordinatortest.calendarview.model.CalendarBean;
import com.example.lkl.coordinatortest.calendarview.view.body.OnCalendarListener;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.PagerItemView;

import java.util.List;


public class CalendarFragment extends Fragment {
	private int mPosition;
	private static final String PAGE_NUM = "page_num";
	private PagerItemView mView ;
	private OnCalendarListener mListener;

	public CalendarFragment()
	{
	}

	public static CalendarFragment create(int position)
	{
		CalendarFragment calendarFragment = new CalendarFragment();
		if (null != calendarFragment)
		{
			Bundle bundle = new Bundle();
			if (null != bundle)
			{
				bundle.putInt(PAGE_NUM, position);
				calendarFragment.setArguments(bundle);
			}
		}
		return  calendarFragment;
	}


	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
		if (null != bundle)
		{
			mPosition = bundle.getInt(PAGE_NUM);
		}
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		mView = new PagerItemView(inflater.getContext());
		if (null != mView)
		{
			mView.setOnItemClickListener(mListener);
		}
		return mView;
	}

	@Override
	public void onResume()
	{
		if (null != mListener)
		{
			mListener.setItemData(mPosition, this);
		}
		super.onResume();
	}

	public void setData(List<CalendarBean> data)
	{
		if (null != mView)
		{
			mView.setData(data);
		}
	}

	public void setOnCalendarListener(OnCalendarListener listener)
	{
		mListener = listener;
	}
}
