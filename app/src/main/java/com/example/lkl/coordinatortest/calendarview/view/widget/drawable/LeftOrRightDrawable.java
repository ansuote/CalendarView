package com.example.lkl.coordinatortest.calendarview.view.widget.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by LKL on 2016-8-11.
 */
public class LeftOrRightDrawable extends Drawable{
    private Paint mPaint;
    private int mStrokeColor = Color.parseColor("#B0B0B0");
    private float mStrokeWidth = 1.5f;
    private float mDensity ;
    private boolean mIsRight;     //是否向右
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    public LeftOrRightDrawable(Context context)
    {
        initPaint(context);
    }

    /**
     * 带设置边距的构造参数
     * @param context
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public LeftOrRightDrawable(Context context, int left, int top, int right, int bottom)
    {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
        initPaint(context);
    }

    private void initPaint(Context context)
    {
        if (null == context)    return;

        //宽度乘以密度值
        mDensity = context.getResources().getDisplayMetrics().density;
        mStrokeWidth = mStrokeWidth * mDensity;

        mPaddingLeft = (int)(mPaddingLeft * mDensity);
        mPaddingTop = (int)(mPaddingTop * mDensity);
        mPaddingRight = (int)(mPaddingRight * mDensity);
        mPaddingBottom = (int) (mPaddingBottom * mDensity);

        if (null == mPaint)
        {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  //抗锯齿
            //mPaint.setAntiAlias(true); //等价
        }
        if (null != mPaint)
        {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mStrokeColor);		//设置默认颜色
            mPaint.setStrokeWidth(mStrokeWidth);  //设置宽度
            mPaint.setDither(true);                //设置防抖动
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        float heigth = rect.height();
        float width = rect.width();

        float paintOffset = mStrokeWidth/2; //绘图的时要考虑画笔的粗细。

        Path path = new Path();
        if (null != path)
        {
            float left = paintOffset + mPaddingLeft;
            float top = paintOffset + mPaddingTop;
            float right = width - paintOffset - mPaddingRight;
            float bottom = heigth - paintOffset - mPaddingBottom;

            RectF pathRectf = new RectF(left, top, right, bottom);
            if (null != pathRectf)
            {
                if (!mIsRight)  //左按钮
                {
                    //设置起点
                    path.moveTo(right, top);
                    path.lineTo(left, heigth/2);
                    path.lineTo(right, bottom);
                }
                else
                {
                    path.moveTo(left, top);
                    path.lineTo(right, heigth / 2);
                    path.lineTo(left, bottom);
                }
            }
            canvas.drawPath(path, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha)
    {
        if (null != mPaint)
        {
            mPaint.setAlpha(alpha);
            invalidateSelf();
        }
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

    /**
     * 设置drawable颜色
     * @param color
     */
    public void setColor(int color)
    {
        //setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        if (null != mPaint)
        {
            mPaint.setColor(color);
            invalidateSelf();
        }
    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.TRANSLUCENT;
    }

    public void setStrokeColor(int color)
    {
        mStrokeColor = color;
        invalidateSelf();
    }

    public void setStrokeWidth(float strokeWidth)
    {
        mStrokeWidth = strokeWidth * mDensity;
        invalidateSelf();
    }

    /**
     * 是否是向右按钮
     * @param isRight
     */
    public void setIsRight(boolean isRight)
    {
        mIsRight = isRight;
    }
}
