package com.example.lkl.coordinatortest.calendarview.view.drawable;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * 自定义circleDrawbale
 * Created by lkl on 16/6/6.
 */
public class CircleDrawable extends Drawable
{
    private Paint mPaint;

    public CircleDrawable()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  //抗锯齿
        if (null != mPaint)
        {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.WHITE);		//设置默认颜色
        }
    }

    /**
     * 设置画笔的类型
     * @param style
     *      Paint.Style.FILL : 填充满
     *      Paint.Style.STROKE  :  画外层边框
     */
    public void setStyle(Paint.Style style)
    {
        if (null != mPaint)
        {
            mPaint.setStyle(style);
            invalidateSelf();
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        final Rect rect = getBounds();
        float cx = rect.exactCenterX();
        float cy = rect.exactCenterY();
        canvas.drawCircle(cx, cy, Math.min(cx, cy), mPaint);
    }

    @Override
    public void setAlpha(int i)
    {
        if (null != mPaint)
        {
            mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    /**
     * 设置drawable颜色
     * @param color
     */
    public void setColor(int color)
    {
    	setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }
    
    @Override
    public void setColorFilter(ColorFilter colorFilter)
    {
        if (null != mPaint)
        {
            mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }
    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.TRANSLUCENT;
    }
}
