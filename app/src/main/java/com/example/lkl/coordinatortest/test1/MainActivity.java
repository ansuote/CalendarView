package com.example.lkl.coordinatortest.test1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CoordinatorLayout.MarginLayoutParams lp = null;

        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(this);
        if (null != coordinatorLayout)
        {
            coordinatorLayout.setFitsSystemWindows(true);

            TitleView titleView = new TitleView(this);
            if (null != titleView)
            {
                titleView.setBackgroundColor(Color.parseColor("#333333"));
                lp = new CoordinatorLayout.MarginLayoutParams(CoordinatorLayout.MarginLayoutParams.MATCH_PARENT,
                                                                CoordinatorLayout.MarginLayoutParams.WRAP_CONTENT);
                if (null != lp)
                {
                    coordinatorLayout.addView(titleView, lp);
                }
            }

            ContentView contentView = new ContentView(this);
            if (null != contentView)
            {
                contentView.setBackgroundColor(Color.YELLOW);

                lp = new CoordinatorLayout.MarginLayoutParams(CoordinatorLayout.MarginLayoutParams.MATCH_PARENT,
                                                              CoordinatorLayout.MarginLayoutParams.WRAP_CONTENT);
                if (null != lp)
                {
                    //lp.setMargins(0, 600, 0, 0);
                    coordinatorLayout.addView(contentView, lp);
                }
            }


            setContentView(coordinatorLayout);
        }

    }
}
