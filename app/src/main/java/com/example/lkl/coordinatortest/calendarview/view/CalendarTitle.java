package com.example.lkl.coordinatortest.calendarview.view;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;
import com.example.lkl.coordinatortest.calendarview.view.widget.LButtonBg;
import com.example.lkl.coordinatortest.calendarview.view.widget.drawable.LeftOrRightDrawable;

/**
 * Title 部分
 * Created by LKL on 2016-7-27.
 */
public class CalendarTitle extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    private LinearLayout mButtonLayout;
    private TextView mTitle;
    private LButtonBg mLeftBtn;
    private LButtonBg mRightBtn;
    private OnTitleBtnClickListener mListener;

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
            int lineLength = DataRespository.dip2px(mContext, 1);
            int padding5 = DataRespository.dip2px(mContext, 5);
            int padding10 = DataRespository.dip2px(mContext, 10);
            int length15 = DataRespository.dip2px(mContext, 15);

            this.setOrientation(LinearLayout.VERTICAL);

            //日期，按钮部分布局
            mButtonLayout = new LinearLayout(mContext);
            if (null != mButtonLayout)
            {
                mButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
                mButtonLayout.setGravity(Gravity.CENTER);
                mButtonLayout.setPadding(padding5, padding5, padding5, padding5);

                //左边按钮
                mLeftBtn = new LButtonBg(mContext);
                if (null != mLeftBtn)
                {
                    setPageBtnParams(mLeftBtn, false, length15, padding10);
                    mButtonLayout.addView(mLeftBtn);
                }

                //日期   2016年7月
                mTitle = new TextView(mContext);
                if (null != mTitle)
                {
                    mTitle.setGravity(Gravity.CENTER_VERTICAL);
                    mTitle.setTextSize(16);
                    mButtonLayout.addView(mTitle, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                }

                //右边按钮
                mRightBtn = new LButtonBg(mContext);
                if (null != mRightBtn)
                {
                    setPageBtnParams(mRightBtn, true, length15, padding10);
                    mButtonLayout.addView(mRightBtn);
                }


                //其他按钮
                /**
                 *
                 */


                this.addView(mButtonLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            }


            addLineView(lineLength);

            //标题部分： 周日  周一 .... 周六
            LinearLayout weekTitle = new LinearLayout(mContext);
            if (null != weekTitle && null != TITLES)
            {
                weekTitle.setOrientation(LinearLayout.HORIZONTAL);
                weekTitle.setGravity(Gravity.CENTER_VERTICAL);
                weekTitle.setBackgroundColor(Color.parseColor("#F5F5F5"));
                weekTitle.setPadding(0, padding10, 0, padding10);

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

            //分割线
            addLineView(lineLength);

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
        if (null != mLeftBtn)
        {
            mLeftBtn.setOnClickListener(b ? this : null);
        }
        if (null != mRightBtn)
        {
            mRightBtn.setOnClickListener(b ? this : null);
        }
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

    /**
     * 增加分隔线
     * @param lineLength
     * @return
     */
    public void addLineView(int lineLength)
    {
        View lineView = new View(mContext);
        if (null != lineView)
        {
            lineView.setBackgroundColor(Color.parseColor("#E0E0E0"));
            this.addView(lineView, LayoutParams.MATCH_PARENT, lineLength);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (null == mListener)  return;

        if (v == mLeftBtn)
        {
            mListener.onLeftBtnClick(v);
        }
        else if (v == mRightBtn)
        {
            mListener.onRightBtnClick(v);
        }
    }

    /**
     * 设置上一页，下一页按钮的属性
     * @param isRight
     */
    private void setPageBtnParams(LButtonBg btn, boolean isRight, int length, int margin)
    {
        if (null != btn)
        {
            LeftOrRightDrawable drawableNormal = new LeftOrRightDrawable(mContext);
            LeftOrRightDrawable drawablePress = new LeftOrRightDrawable(mContext);
            if (null != drawableNormal && null != drawablePress)
            {
                drawableNormal.setIsRight(isRight);
                drawableNormal.setColor(Color.parseColor("#B0B0B0"));
                drawablePress.setIsRight(isRight);
                drawablePress.setColor(Color.parseColor("#303030"));
                btn.setBackgroundByDrawable(drawableNormal,drawablePress, drawablePress, drawablePress, drawablePress);
            }
            LayoutParams lp = new LayoutParams(length, length);
            if (null != lp)
            {
                lp.setMargins(margin, margin, margin , margin);
                btn.setLayoutParams(lp);
            }
        }
    }

    public interface OnTitleBtnClickListener
    {
        void onLeftBtnClick(View view);
        void onRightBtnClick(View view);

        /**
         *
         */
    }

    public void setOnTitleClickListener(OnTitleBtnClickListener listener)
    {
        mListener = listener;
    }

}
