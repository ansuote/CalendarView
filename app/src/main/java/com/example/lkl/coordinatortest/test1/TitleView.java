package com.example.lkl.coordinatortest.test1;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.test1.behavior.ContentViewBehavior;

/**
 * Created by LKL on 2016-8-4.
 */
@CoordinatorLayout.DefaultBehavior(ContentViewBehavior.class)
public class TitleView extends LinearLayout {
    private Context mContext;
    private TextView textView2;

    public TitleView(Context context)
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
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER_VERTICAL);
        TextView textView = new TextView(mContext);
        if (null != textView)
        {
            textView.setText("这是个模拟日历1");
            textView.setTextSize(50);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);

            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (null != lp)
            {
                lp.weight = 1;
                this.addView(textView, lp);
            }
        }

        textView2 = new TextView(mContext);
        if (null != textView2)
        {
            textView2.setText("这是个模拟日历2");
            textView2.setTextSize(50);
            textView2.setGravity(Gravity.CENTER);
            textView2.setTextColor(Color.WHITE);

            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (null != lp)
            {
                lp.weight = 1;
                this.addView(textView2, lp);
            }
        }

        TextView textView3 = new TextView(mContext);
        if (null != textView3)
        {
            textView3.setText("这是个模拟日历3");
            textView3.setTextSize(50);
            textView3.setGravity(Gravity.CENTER);
            textView3.setTextColor(Color.WHITE);

            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (null != lp)
            {
                lp.weight = 1;
                this.addView(textView3, lp);
            }
        }
    }

    private void regEvent(boolean b)
    {

    }

    private TextView getMiddleTv()
    {
        return textView2;
    }
}
