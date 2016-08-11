package com.example.lkl.coordinatortest.calendarview.presenter;


import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;

import com.example.lkl.coordinatortest.calendarview.CalendarContract;
import com.example.lkl.coordinatortest.calendarview.model.CalendarBean;
import com.example.lkl.coordinatortest.calendarview.model.DataContract;
import com.example.lkl.coordinatortest.calendarview.model.DataRespository;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.CalendarFragment;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview.CustomGridView;

import java.util.HashMap;
import java.util.List;

public class CalendarPresenter implements CalendarContract.IPresenter{
	private CalendarContract.IView mIView;
	private DataRespository mDataRespository;
	private HashMap<Integer, List<CalendarBean>> mHashMap;
	private int mCurrentItem = DataRespository.SELECT_ITEM;	//当前的item
	private View mLastView;	//记录上一次点击的位置，用于下一次点击的时候，清除上一次点击效果

	public CalendarPresenter(CalendarContract.IView iView) 
	{
		mDataRespository = new DataRespository();
		mHashMap = new HashMap<Integer, List<CalendarBean>>();
		mIView = iView;
	}
	
	@Override
	public void start() 
	{

	}

	@Override
	public void setItemData(final int position, final CalendarFragment fragment)
	{
		if (null != mDataRespository)
		{
			mDataRespository.loadDataByUIThread(position, new DataContract.LoadDataCallback() {
				@Override
				public void onLoadDataBegining()
				{

				}

				@Override
				public void onLoadDataFinish(List<CalendarBean> list)
				{
					if (null != fragment)
					{
						if (null != list)
						{
							fragment.setData(list);
							//加入到map
							if (null != mHashMap)
							{
								mHashMap.put(position, list);
							}

							//刷新标题
							if (mCurrentItem == position)
							{
								loadTitleData();
							}
						}
					}
				}

				@Override
				public void onLoadDataError()
				{

				}
			});
		}
	}

	@Override
	public void destroyItemData(int position)
	{
		if (null != mHashMap)
		{
			mHashMap.remove(position);
		}
	}

	@Override
	public void onDestory()
	{
		if (null != mHashMap)
		{
			mHashMap.clear();
			mHashMap = null;
		}
		if (null != mDataRespository)
		{
			mDataRespository.onDestory();
			mDataRespository = null;
		}
		if (null != mIView)
		{
			mIView.onDestory();
			mIView = null;
		}
	}

	/**
	 * 加载标题数据，并且显示标题
	 */
	public void loadTitleData()
	{
		try
		{
			if (null != mHashMap)
			{
				List<CalendarBean> bean = mHashMap.get(mCurrentItem);
				//6标识第七列数据，该列数据不可能为null (6-34都可以)
				if (null != bean && bean.size() > 0 && null != bean.get(6))
				{
					int year = bean.get(6).getYear();
					int month = bean.get(6).getMonth();

					if (null != mIView)
					{
						mIView.refershTitle(year + "年" + month + "月");
					}
				}

			}
		}
		catch (Exception e)
		{

		}
	}

	@Override
	public void clickItem(AdapterView<?> parent, View view, int position, long id)
	{
		if (null == mIView)	return;

		try
		{
			saveCurrentItemHeight(parent, view, position);

			refreshClickDrawable(parent, view, position);

		}
		catch (Exception e)
		{

		}
	}

	@Override
	public void clickLeft()
	{
		mCurrentItem = mCurrentItem -1;
		if (null != mIView)
		{
			mIView.setCurrentItem(mCurrentItem);
		}
	}

	@Override
	public void clickRight()
	{
		mCurrentItem = mCurrentItem +1;
		if (null != mIView)
		{
			mIView.setCurrentItem(mCurrentItem);
		}
	}

	/**
	 * 刷新点击的时候的效果图
	 * @param parent
	 * @param view
	 * @param position
     */
	private void refreshClickDrawable(AdapterView<?> parent, View view, int position)
	{
		Object obj = null;
		if (null != parent && null != parent.getAdapter())
		{
			obj = parent.getAdapter().getItem(position);
			if (null != obj && obj instanceof CalendarBean)
			{
				view.setTag(obj);//view绑定对应的数据

				CalendarBean bean = (CalendarBean)obj;
				//判断是否有政务安排
				if (bean.getIsHaveData())
				{

				}
				else
				{
					mIView.clickNormalDate(view);
				}

				//如果点击了不同的view
				if (mLastView != view && null != mLastView)
				{
					//清除上一次点击的背景效果
					mIView.clearBackground(mLastView);

					//判断上一个view是否为今天的，是则切换为今天的背景图
					Object lastObj = mLastView.getTag();
					if (null != lastObj && lastObj instanceof CalendarBean)
					{
						if (((CalendarBean)lastObj).getIsToday())
						{
							mIView.setTodayBackground(mLastView);
						}
					}

				}
				//重置view
				mLastView = view;
			}
		}

	}

	/**
	 * 保存当前item在gridView的高度
	 */
	private void saveCurrentItemHeight(AdapterView<?> parent, View view, int position)
	{
		ViewParent parentView =  view.getParent();
		if (null != parentView && parentView instanceof CustomGridView)
		{
			if (null != parent && null != parent.getAdapter())
			{
				int count = parent.getAdapter().getCount();	//总个数
				int row = count / 7;	//总行数
				int i = position / 7;	//当前所在的行
				int j = position % 7;
				if (j != 0)	//如果没有整除
				{
					i++;
				}

				CustomGridView gridView = (CustomGridView)parentView;
				/*int bottom = gridView.getHeight() * i / row;	//计算当前position所在高度
				gridView.setCurrentItemBottom(bottom);*/
				gridView.setCurrentView(view);
				/*Log.i("lkl", "saveCurrentItemHeight -- position = " + position
						+ "; count = " + count
						+ "; i = " + i
						+ "; bottom = " + bottom);*/
			}

		}
	}

	@Override
	public void resetCurrentItem(int position)
	{
		mCurrentItem = position;
	}
}
