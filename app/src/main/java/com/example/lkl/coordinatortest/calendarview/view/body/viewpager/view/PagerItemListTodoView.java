package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view;

/**
 * Created by LKL on 2016-8-4.
 */

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.behavior.TodoListBehavior;

/**
 * 待办事列表
 */
@CoordinatorLayout.DefaultBehavior(TodoListBehavior.class)
public class PagerItemListTodoView extends NestedScrollView{
    private Context mContext;
    private TextView mLunarTitle;   //农历标题

    public PagerItemListTodoView(Context context)
    {
        this(context, null);
    }

    public PagerItemListTodoView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public PagerItemListTodoView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init()
    {
        if (null == mContext) return;
        initView();
        regEvent(true);
    }

    private void initView()
    {
        LinearLayout layout = new LinearLayout(mContext);
        if (null != layout)
        {
            layout.setOrientation(LinearLayout.VERTICAL);

            mLunarTitle = new TextView(mContext);
            if (null != mLunarTitle)
            {
                mLunarTitle.setText("这个用来显示农历");

                layout.addView(mLunarTitle, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }


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

    public void setLunarTitle(String lunarTitle)
    {
        if (null != mLunarTitle && null != lunarTitle)
        {
            mLunarTitle.setText(lunarTitle);
        }
    }

    public void onDesotry()
    {

    }

}
