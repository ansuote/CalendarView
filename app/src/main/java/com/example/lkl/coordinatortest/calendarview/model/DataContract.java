package com.example.lkl.coordinatortest.calendarview.model;

import java.util.List;

/**
 * Created by lkl on 16/7/31.
 */
public interface DataContract
{
    interface LoadDataCallback
    {
        void onLoadDataBegining();  //加载数据前
        void onLoadDataFinish(List<CalendarBean> list); //加载数据后
        void onLoadDataError();     //加载数据异常
    }

    void loadDataByUIThread(int position, LoadDataCallback callback);   //在主线程加载position对应的数据
//    void loadDataByBackgroundThread(int position, LoadDataCallback callback);   //在后台线程加载position对应的数据
}
