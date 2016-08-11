package com.example.lkl.coordinatortest.calendarview.model;

import android.content.Context;

import com.example.lkl.coordinatortest.calendarview.model.util.LunarCalendar;
import com.example.lkl.coordinatortest.calendarview.model.util.SpecialCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataRespository implements DataContract{
    public static int MAX_COUNT = 1000;     //fragement数量,模拟无限点击
    public static int SELECT_ITEM = MAX_COUNT/2;   //viewPage所在Item
    public static int LIMIT_COUNT = 1;  //设置缓存页面数量 (如果为1 则 : 499, 500, 501)

    // 系统当前时间
    private String currentDate;
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;

    private SpecialCalendar sc = null;
    private LunarCalendar lc = null;

    public DataRespository()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        if (null != sdf)
        {
            currentDate = sdf.format(date); // 当期日期
            year_c = Integer.parseInt(currentDate.split("-")[0]);
            month_c = Integer.parseInt(currentDate.split("-")[1]);
            day_c = Integer.parseInt(currentDate.split("-")[2]);
        }

        sc = new SpecialCalendar();
        lc = new LunarCalendar();
    }

    /**
     * 获取该月份数据
     * @param year
     * @param month
     * @param day
     * @return
     */
    public List<CalendarBean> getMonthsData(int year, int month, int day)
    {
        if (null == sc) return null;

        boolean isLeapyear = sc.isLeapYear(year); // 是否为闰年
        int daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
        int dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几

        String lunarDay = "";

        // 得到当前月的所有日程日期(这些日期需要标记)

        ArrayList<CalendarBean> list = new ArrayList<CalendarBean>();
        if (null != list)
        {
            if (dayOfWeek != 7) //如果第一天是星期日,则补全上个月数据
            {
                for(int i = 0; i < dayOfWeek; i++)
                {
                    list.add(null);
                }
            }

            for (int i = 0; i < daysOfMonth; i++)
            {
                CalendarBean bean = new CalendarBean();
                if (null != bean)
                {
                    lunarDay = lc.getLunarDate(year, month, i - dayOfWeek + 1, false);
                    bean.setLunarCanlendar(lunarDay);
                    bean.setYear(year);
                    bean.setMonth(month);
                    bean.setNationalCalendar(i + 1);
                    bean.setIsToday(i+1 == day_c);
                }
                list.add(bean);
            }


            int size = list.size();
            //下个月补全
            if (size < 35)
            {
                for (int i = 0; i< (35-size); i++)
                {
                    list.add(null);
                }
            }
            else if (list.size() >35 && list.size() < 42)
            {
                for (int i = 0; i< (42-size); i++)
                {
                    list.add(null);
                }
            }
        }
        return list;
    }


    @Override
    public void loadDataByUIThread(int position, LoadDataCallback callback)
    {
        try
        {
            if (null != callback)
            {
                callback.onLoadDataBegining();
            }

            //获取偏移量
            int offSet = position - SELECT_ITEM;

            //倍数，1 表示向左滑动了一年
            int yearOffSet = offSet / 12;
            //余数
            int monthOffSet = offSet % 12;

            //对应年月份
            int month = month_c + monthOffSet;
            int year = year_c + yearOffSet;
            int day = day_c;

            //判断是否超过本年月份范围
            if (month < 1)
            {
                month = month + 12;
                year = year - 1;
            }
            else if (month > 12)
            {
                month = month - 12;
                year = year + 1;
            }

            List<CalendarBean> list = getMonthsData(year, month, day);
            if (null != callback)
            {
                callback.onLoadDataFinish(list);
            }
        }
        catch (Exception e)
        {
            if (null != callback)
            {
                callback.onLoadDataError();
            }
        }

    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void onDestory()
    {
        sc = null;
        lc = null;
    }
}
