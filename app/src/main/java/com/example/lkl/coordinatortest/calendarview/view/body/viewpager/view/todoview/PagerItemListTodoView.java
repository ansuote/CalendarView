package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.todoview;

/**
 * Created by LKL on 2016-8-4.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;
import com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.behavior.TodoListBehavior;

/**
 * 待办事列表
 */
@CoordinatorLayout.DefaultBehavior(TodoListBehavior.class)
public class PagerItemListTodoView extends NestedScrollView{
    private Context mContext;
    private TextView mLunarTitle;   //农历标题
    private int mSecondColor = Color.parseColor("#888888");
    private int mMainColor = Color.BLACK;

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
        int padding10 = DataRespository.dip2px(mContext, 10);
        int padding50 = DataRespository.dip2px(mContext, 40);

        LinearLayout layout = new LinearLayout(mContext);
        if (null != layout)
        {
            layout.setOrientation(LinearLayout.VERTICAL);

            //分隔线
            DataRespository.addLineView(mContext, layout, LinearLayout.HORIZONTAL);

            //农历标题
            mLunarTitle = new TextView(mContext);
            if (null != mLunarTitle)
            {
                mLunarTitle.setPadding(padding50, padding10, 0, padding10);
                mLunarTitle.setTextColor(mSecondColor);

                layout.addView(mLunarTitle, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }

            //分隔线
            DataRespository.addLineView(mContext, layout, LinearLayout.HORIZONTAL);

            for(int i = 0; i < 10; i++)
            {
                ItemView itemView = new ItemView(mContext);
                if (null != itemView)
                {
                    itemView.setIsLastItem(i==10-1);
                    itemView.init();
                    layout.addView(itemView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
            }

            DataRespository.addLineView(mContext, layout, LinearLayout.HORIZONTAL);
            /*for (int i = 0 ; i < 50; i ++)
            {
                TextView tv = new TextView(mContext);
                if (null != tv)
                {
                    tv.setText(i+" -------------- ");
                    tv.setTextSize(30);
                    layout.addView(tv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
            }*/

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
