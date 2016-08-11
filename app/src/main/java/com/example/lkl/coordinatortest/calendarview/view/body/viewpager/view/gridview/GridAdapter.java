package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.example.lkl.coordinatortest.calendarview.model.CalendarBean;
import com.example.lkl.coordinatortest.calendarview.view.widget.drawable.CircleDrawable;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<CalendarBean> mList;
    private Context mContext;
	public static int NUM_COLUMS = 7;
	private CircleDrawable mTodayDrawable;

    public GridAdapter(Context context)
    {
        mContext = context;
		mTodayDrawable = new CircleDrawable();
		if (null != mTodayDrawable)
		{
			mTodayDrawable.setColor(Color.RED);
			mTodayDrawable.setStyle(Paint.Style.STROKE);
		}

	}
    
    public void setData(List<CalendarBean> list)
    {
        mList = list;
    }
    
    @Override
	public int getCount() 
    {
    	if (null != mList)
    	{
    		return mList.size();
    	}
		return 0;
	}

	@Override
	public Object getItem(int position) 
	{
		if (null != mList)
		{
			return mList.get(position);
		}
		
		return null;
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		if (null == mContext)	return null;
		
		if (null == convertView)
		{
			convertView = new GridItemView(mContext);
		}

		setItemAdaterParentHeight(convertView, parent);

		if (convertView instanceof GridItemView)
		{			
			bind((GridItemView)convertView, position, parent);
		}
		
		
		return convertView;
	}

	/**
	 * 设置 item 自适应父控件高度
	 * @param convertView
	 * @param parent
     */
	private void setItemAdaterParentHeight(View convertView, ViewGroup parent)
	{
		try
		{
			if (null != convertView && null != parent)
			{
				int row = 0;

				if (null != mList)
				{
					row = mList.size() / NUM_COLUMS;
					int remainder = mList.size() % NUM_COLUMS;
					if (remainder != 0)		//如果余数不为零，则列数加一
					{
						row = row + 1;
					}

					AbsListView.LayoutParams param = new AbsListView.LayoutParams(
							ViewGroup.LayoutParams.MATCH_PARENT,
							parent.getHeight()/row);
					convertView.setLayoutParams(param);
				}
			}

		}
		catch (Exception e)
		{

		}
		finally
		{

		}
	}


	/**
	 * 绑定数据与界面
	 * @param convertView
	 * @param position
	 */
	private void bind(GridItemView convertView, int position, ViewGroup parent)
	{
		try 
		{
			if (null == convertView ||  null == mList || mList.size() <= 0) 	return;

			CalendarBean bean = mList.get(position);
			if (null != bean)
			{
				int national = bean.getNationalCalendar();
				if (0 != national)
				{
					convertView.setNationText(national+"");
				}
				else
				{
					convertView.setNationText("");
				}

				boolean isToday = bean.getIsToday();
				if (isToday)
				{
					convertView.setNationBackground(mTodayDrawable);
					//保存到parent ： CustomGridView ，用于 CalendarViewBehavior计算滑动范围
					if (null != parent && parent instanceof CustomGridView)
					{
						((CustomGridView)parent).setCurrentView(convertView);
					}

				}
				else
				{
					convertView.setNationBackground(null);
				}


				String lunar = bean.getLunarCanlendar();
				if (null != lunar)
				{
					convertView.setLunar(lunar);
				}
				else
				{
					convertView.setLunar("");
				}
			}
			
		} 
		catch (Exception e) 
		{
		}
	}

	public void onDestory()
	{
		mList = null;
		mTodayDrawable = null;
	}

}
