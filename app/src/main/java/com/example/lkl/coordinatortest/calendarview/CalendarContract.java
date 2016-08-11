package com.example.lkl.coordinatortest.calendarview;


import android.view.View;
import android.widget.AdapterView;

import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.CalendarFragment;

public interface CalendarContract {

	/**
	 * 整个view的接口
	 */
	interface IView
	{
		void refershTitle(String title);
		void clickNormalDate(View view);	//点击普通的一天
		void clickScheduleDate(View view);	//点击有日程的一天
		void clearBackground(View view);	//清除背景颜色
		void setTodayBackground(View view);	//设置今天的图标
		void setCurrentItem(int position);	//设置当前的viewpager
		void onDestory();
	}
	
	
	/**
	 * Presenter的接口
	 */
	interface IPresenter
	{
		void start();		//加载数据时显示进度条等操作
		void setItemData(int position, CalendarFragment fragment);
		void destroyItemData(int position);
		void resetCurrentItem(int position);	//重置当前的position
		void loadTitleData();
		void clickItem(AdapterView<?> parent, View view, int position, long id);	//点击某一天执行的动作
		void clickLeft();
		void clickRight();
		void onDestory();
	}	
	
}
