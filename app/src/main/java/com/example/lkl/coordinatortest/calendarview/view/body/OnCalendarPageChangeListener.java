package com.example.lkl.coordinatortest.calendarview.view.body;

/**
 * Created by LKL on 2016-8-2.
 */
public interface OnCalendarPageChangeListener {
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    public void onPageSelected(int position);
    public void onPageScrollStateChanged(int state);
}
