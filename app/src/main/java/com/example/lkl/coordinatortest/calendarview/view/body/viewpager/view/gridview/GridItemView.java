package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.gridview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkl.coordinatortest.calendarview.model.DataRespository;

public class GridItemView extends LinearLayout {
    private Context mContext;
	private TextView mNation;	//国历
    private TextView mLunar;	//农历

	public GridItemView(Context context)
    {
        this(context, null);
    }

    public GridItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public GridItemView(Context context, AttributeSet attrs, int defStyle)
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
			this.setOrientation(LinearLayout.VERTICAL);
			this.setGravity(Gravity.CENTER);
			int length30 = DataRespository.dip2px(mContext, 30);

        	mNation = new TextView(mContext);
        	if (null != mNation)
        	{
        		mNation.setVisibility(View.VISIBLE);	//默认显示
        		mNation.setGravity(Gravity.CENTER);
				lp = new LayoutParams(length30, length30);
				if (null != lp)
				{
					this.addView(mNation, lp);
				}
        	}
        	
        	mLunar = new TextView(mContext);
        	if (null != mLunar)
        	{
        		mLunar.setVisibility(View.GONE);	//默认隐藏
        		mLunar.setGravity(Gravity.CENTER);
				lp = new LayoutParams(length30, length30);
				if (null != lp)
				{
					this.addView(mLunar, lp);
				}
        	}
        	
        }
        catch (Exception e)
        {

        }
        finally
        {

        }

    }
    
    private void regEvent(boolean b)
    {

    }

	/**
	 * 设置背景
	 * @param drawable
     */
	public void setNationBackground(Drawable drawable)
	{
		if (null != mNation)
		{
			mNation.setBackground(drawable);
		}
	}

	/**
	 * 设置国历的字体颜色
	 * @param color
     */
	public void setNationTextColor(int color)
	{
		if (null != mNation)
		{
			mNation.setTextColor(color);
		}
	}

	/**
	 * 设置国历的字体大小
	 * @param size
     */
	public void setNationTextSize(float size)
	{
		if (null != mNation)
		{
			mNation.setTextSize(size);
		}
	}

    public String getNationText() 
    {
    	if (null != mNation && null != mNation.getText())
    	{
    		return mNation.getText().toString();
    	}
    	return null;
	}

    /**
     * 设置国历日期
     * @param national
     */
	public void setNationText(String national) 
	{
		if (null != mNation && null != national)
		{
			mNation.setText(national);
		}
	}

	public String getLunar() 
	{
		if (null != mLunar && null != mLunar.getText())
		{
			return mLunar.getText().toString();
		}
		return null;
	}

	/**
	 * 设置农历日期
	 * @param lunar
	 */
	public void setLunar(String lunar) 
	{
		if (null != mLunar && null != lunar)
		{
			mLunar.setText(lunar);
		}
	}


	public void onDestory()
    {
    	mNation = null;
    	mLunar = null;
    }
}
