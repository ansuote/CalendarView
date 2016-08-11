package com.example.lkl.coordinatortest.test3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lkl.coordinatortest.calendarview.view.CalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CalendarView calendarView = new CalendarView(this);
        setContentView(calendarView);

        Log.e("gpj", "HaHaHa...");
    }
}
