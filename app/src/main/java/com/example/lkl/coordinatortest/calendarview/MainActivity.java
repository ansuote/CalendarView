package com.example.lkl.coordinatortest.calendarview;

import android.app.Activity;
import android.os.Bundle;

import com.example.lkl.coordinatortest.calendarview.view.CalendarView;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CalendarView calendarView = new CalendarView(this);
        if (null != calendarView)
        {
            setContentView(calendarView);
        }
    }

}
