package com.example.lkl.coordinatortest.test1;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.test1.behavior.EventListBehavior;

/**
 * Created by LKL on 2016-8-4.
 */
@CoordinatorLayout.DefaultBehavior(EventListBehavior.class)
public class ContentView extends NestedScrollView{
    private Context mContext;

    public ContentView(Context context)
    {
        super(context);
        mContext = context;
        init();
    }

    private void init()
    {
        if (null == mContext)   return;;
        initView();
        regEvent(true);
    }

    private void initView()
    {
        LinearLayout layout = new LinearLayout(mContext);
        if (null != layout)
        {
            layout.setOrientation(LinearLayout.VERTICAL);

            for (int i = 0 ; i < 50; i ++)
            {
                TextView tv = new TextView(mContext);
                if (null != tv)
                {
                    tv.setText(i+" -------------- ");
                    tv.setTextSize(30);
                    layout.addView(tv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
            }

            this.addView(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }

    }

    private void regEvent(boolean b)
    {

    }

}
