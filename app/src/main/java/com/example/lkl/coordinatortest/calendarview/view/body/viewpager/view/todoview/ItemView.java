package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.todoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;
import com.example.lkl.coordinatortest.calendarview.view.widget.drawable.CircleDrawable;

/**
 * Created by LKL on 2016-8-15.
 */
public class ItemView extends LinearLayout{
    private Context mContext;
    private TextView mTime;     //时间
    private TextView mContent;  //内容
    private int mSecondColor = Color.parseColor("#888888");
    private int mMainColor = Color.BLACK;
    private int mCicleColor = Color.parseColor("#E1E1E1");
    private float mTextSize = 16;
    private boolean mIsLastItem;

    public ItemView(Context context)
    {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void init()
    {
        if (null == mContext)   return;
        initView();
        regEvent(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView()
    {
        LayoutParams lp = null;
        try
        {
            this.setOrientation(LinearLayout.HORIZONTAL);

            int length4 = DataRespository.dip2px(mContext, 4);
            int padding8 = DataRespository.dip2px(mContext, 8);
            int padding16 = DataRespository.dip2px(mContext, 16);

            //左边布局
            LinearLayout leftLayout = new LinearLayout(mContext);
            if (null != leftLayout)
            {
                leftLayout.setOrientation(LinearLayout.HORIZONTAL);
                leftLayout.setGravity(Gravity.CENTER_VERTICAL);

                //时间
                mTime = new TextView(mContext);
                if (null != mTime)
                {
                    mTime.setText("时间");
                    mTime.setTextColor(mSecondColor);
                    mTime.setTextSize(mTextSize);
                    mTime.setPadding(padding8, padding16, padding8, padding16);
                    mTime.setGravity(Gravity.CENTER);

                    lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
                    if (null != lp)
                    {
                        lp.weight = 1;
                        leftLayout.addView(mTime, lp);
                    }
                }

                RelativeLayout divideLayout = new RelativeLayout(mContext);
                if (null != divideLayout)
                {
                    lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                    if (null != lp)
                    {
                        LinearLayout lineLayout = new LinearLayout(mContext);
                        if (null != lineLayout)
                        {
                            DataRespository.addLineView(mContext, lineLayout, LinearLayout.VERTICAL);

                            RelativeLayout.LayoutParams relativeLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                                                     RelativeLayout.LayoutParams.MATCH_PARENT);
                            if (null != relativeLp)
                            {
                                relativeLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                                divideLayout.addView(lineLayout, relativeLp);
                            }
                        }

                        //小圆点
                        TextView circle = new TextView(mContext);
                        if (null != circle)
                        {
                            CircleDrawable circleDrawable = new CircleDrawable();
                            if (null != circleDrawable)
                            {
                                circleDrawable.setColor(mCicleColor);
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                                {
                                    circle.setBackground(circleDrawable);
                                }
                            }

                            RelativeLayout.LayoutParams circleLp = new RelativeLayout.LayoutParams(length4, length4);
                            if (null != circle)
                            {
                                circleLp.addRule(RelativeLayout.CENTER_IN_PARENT);
                                divideLayout.addView(circle, circleLp);
                            }

                        }

                        leftLayout.addView(divideLayout, lp);
                    }
                }

                lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
                if (null != lp)
                {
                    lp.weight = 1;
                    this.addView(leftLayout, lp);
                }
            }

            //右边布局
            LinearLayout rightLayout = new LinearLayout(mContext);
            if (null != rightLayout)
            {
                rightLayout.setOrientation(LinearLayout.VERTICAL);
                rightLayout.setPadding(padding16, 0, 0, 0);

                //内容
                mContent = new TextView(mContext);
                if (null != mContent)
                {
                    mContent.setText("这个是模拟内容");
                    mContent.setTextColor(mMainColor);
                    mContent.setTextSize(mTextSize);
                    mContent.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                    mContent.setPadding(padding8, padding16, padding16, padding16);
                    rightLayout.addView(mContent, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                }

                //不是最后一个Item才增加分隔线
                if (!mIsLastItem)
                {
                    DataRespository.addLineView(mContext, rightLayout, LinearLayout.HORIZONTAL);
                }

                lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
                if (null != lp)
                {
                    lp.weight = 6;
                    this.addView(rightLayout, lp);
                }
            }
        }
        catch (Exception e)
        {

        }
    }

    private void regEvent(boolean b)
    {

    }

    /**
     * 是否是最后一个item
     * @param isLastItem
     */
    public void setIsLastItem(boolean isLastItem)
    {
        mIsLastItem = isLastItem;
    }

    public boolean getIsLastItem()
    {
        return  mIsLastItem;
    }

    public void onDestory()
    {
        regEvent(false);


    }

}
