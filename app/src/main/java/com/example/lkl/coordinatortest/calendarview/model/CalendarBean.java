package com.example.lkl.coordinatortest.calendarview.model;


/**
 * Created by LKL on 2016-7-27.
 */
public class CalendarBean {
	private String mLunarCanlendar;		//农历
	private int mYear;	//所在年
	private int mMonth;	//所在月
	private int mNationalCalendar;	//国历
	private boolean mIsToday;	//是否是今天
	private boolean mIsHaveData;	//是否包含数据

	public int getNationalCalendar() {
		return mNationalCalendar;
	}
	public void setNationalCalendar(int nationalCalendar) {
		mNationalCalendar = nationalCalendar;
	}
	public String getLunarCanlendar() {
		return mLunarCanlendar;
	}
	public void setLunarCanlendar(String lunarCanlendar) {
		mLunarCanlendar = lunarCanlendar;
	}

	public int getMonth()
	{
		return mMonth;
	}

	public void setMonth(int month)
	{
		mMonth = month;
	}

	public int getYear()
	{
		return mYear;
	}

	public void setYear(int year)
	{
		mYear = year;
	}

	public void setIsToday(boolean isToday)
	{
		mIsToday = isToday;
	}

	public boolean getIsToday()
	{
		return mIsToday;
	}

	public void setIsHaveData(boolean isHaveData)
	{
		mIsHaveData = isHaveData;
	}

	public boolean getIsHaveData()
	{
		return mIsHaveData;
	}
}
