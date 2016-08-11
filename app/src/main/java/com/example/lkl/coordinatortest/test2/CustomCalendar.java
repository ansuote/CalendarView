package com.example.lkl.coordinatortest.test2;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by LKL on 2016-8-8.
 */
public class CustomCalendar extends AppBarLayout{
    private Context mContext;

    public CustomCalendar(Context context)
    {
        super(context);
        mContext = context;
        initView();
    }

    private void initView()
    {
        this.setOrientation(LinearLayout.VERTICAL);
        AppBarLayout.LayoutParams lp = null;

        TextView textView = new TextView(mContext);
        if (null != textView)
        {
            textView.setText("这是一个Title");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(50);
            textView.setMinHeight(100);
            lp = new AppBarLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            if (null != lp)
            {
                lp.setScrollFlags(LayoutParams.SCROLL_FLAG_SCROLL|LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                this.addView(textView, lp);
            }
        }
    }


}
