package com.example.lkl.coordinatortest.calendarview.view.body;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.CalendarFragment;

/**
 * Created by LKL on 2016-8-1.
 */
public interface OnCalendarListener {

    //通过positon加载对应数据，并且加载到fragment上
    void setItemData(int position, CalendarFragment fragment);
    //移出fagment对应的数据
    void destroyItemData(ViewGroup container, int position, Object object);

    void onItemClick(AdapterView<?> parent, View view, int position, long id);

}
