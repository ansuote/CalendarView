package com.example.lkl.coordinatortest.test2;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test2_layout);

        CoordinatorLayout.LayoutParams lp = null;

        CoordinatorLayout layout = new CoordinatorLayout(this);
        if (null != layout)
        {
            CustomCalendar customCalendar = new CustomCalendar(this);
            if (null != customCalendar)
            {
                lp = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,
                        CoordinatorLayout.LayoutParams.WRAP_CONTENT);
                if (null != lp)
                {
                    layout.addView(customCalendar, lp);
                }
            }

            TodoView todoView = new TodoView(this);
            if (null != todoView)
            {
                lp = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,
                        CoordinatorLayout.LayoutParams.MATCH_PARENT);
                if (null != lp)
                {
                    layout.addView(todoView, lp);
                }
            }

            setContentView(layout);
        }


    }
}
