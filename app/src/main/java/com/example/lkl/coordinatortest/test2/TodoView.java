package com.example.lkl.coordinatortest.test2;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by LKL on 2016-8-8.
 */
@CoordinatorLayout.DefaultBehavior(AppBarLayout.ScrollingViewBehavior.class)
public class TodoView extends NestedScrollView{
    private Context mContext;

    public TodoView(Context context)
    {
        super(context);
        mContext = context;
        initView();

    }

    private void initView()
    {
        LinearLayout layout = new LinearLayout(mContext);
        if (null != layout)
        {
            layout.setOrientation(LinearLayout.VERTICAL);
            for(int i = 0; i < 50; i++)
            {
                TextView textView = new TextView(mContext);
                if (null != textView)
                {
                    textView.setText("------ " + i);
                    textView.setTextSize(20);
                    layout.addView(textView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
            }

            this.addView(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }
}
