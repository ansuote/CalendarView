package com.example.lkl.coordinatortest.calendarview.view;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;

/**
 * Title 部分
 * Created by LKL on 2016-7-27.
 */
public class CalendarTitle extends LinearLayout{
    private Context mContext;
    private LinearLayout mButtonLayout;
    private TextView mTitle;

    private static String[] TITLES = {"日", "一", "二", "三", "四", "五", "六"};

    public CalendarTitle(Context context)
    {
        this(context, null);
    }

    public CalendarTitle(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CalendarTitle(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init()
    {
        if (null == mContext)   return;
        initView();
        regEvent(true);
    }

    private void initView()
    {
        LayoutParams lp = null;
        try
        {
            int padding10 = DataRespository.dip2px(mContext, 10);
            int padding20 = DataRespository.dip2px(mContext, 20);

            this.setOrientation(LinearLayout.VERTICAL);

            //日期，按钮部分布局
            mButtonLayout = new LinearLayout(mContext);
            if (null != mButtonLayout)
            {
                mButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
                mButtonLayout.setGravity(Gravity.CENTER);
                mButtonLayout.setPadding(padding10, padding20, padding10, padding20);

                //日期   2016年7月
                mTitle = new TextView(mContext);
                if (null != mTitle)
                {
                    mTitle.setGravity(Gravity.CENTER_VERTICAL);
                    mTitle.setTextSize(16);
                    mButtonLayout.addView(mTitle, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                }

                //其他按钮
                /**
                 *
                 */


                this.addView(mButtonLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            }


            //标题部分： 周日  周一 .... 周六
            LinearLayout weekTitle = new LinearLayout(mContext);
            if (null != weekTitle && null != TITLES)
            {
                weekTitle.setOrientation(LinearLayout.HORIZONTAL);
                weekTitle.setGravity(Gravity.CENTER_VERTICAL);

                lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
                if (null != lp)
                {
                    lp.weight = 1;

                    for (int i = 0; i < TITLES.length ; i++)
                    {

                        TextView tv = new TextView(mContext);
                        if (null != tv)
                        {
                            tv.setGravity(Gravity.CENTER);
                        	String text = TITLES[i];
                        	if (null != text)
                        	{
                                tv.setText(text);
                                //设置颜色
                                if ("日".equals(text) || "六".equals(text))
                                {
                                	tv.setTextColor(Color.parseColor("#888888"));
                                }
                                else
                                {
                                	tv.setTextColor(Color.BLACK);
                                }
                        	}

                            weekTitle.addView(tv, lp);
                        }
                    }
                }

                this.addView(weekTitle, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            }



        }
        catch (Exception e)
        {

        }
        finally
        {
            lp = null;
        }
    }

    private void regEvent(boolean b)
    {

    }

    public void onDestory()
    {

    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title)
    {
        if (null != mTitle && null != title)
        {
            mTitle.setText(title);
        }
    }

}
