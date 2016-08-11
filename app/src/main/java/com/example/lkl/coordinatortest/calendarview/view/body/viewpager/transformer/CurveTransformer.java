package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.transformer;


import android.view.View;

/**
 * 自定义Transformer
 * 1.滑动的时候,透明度,尺寸同时变化
 * 2.左滑的时候,滑出屏幕的view消失
 *
 * Created by LKL on 2016-7-7.
 */
public class CurveTransformer extends BasePageTransformer
{
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale = DEFAULT_MIN_SCALE;

    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlphaNormal = DEFAULT_MIN_ALPHA;
    private float mMinAlphaZero = 0;

    public CurveTransformer()
    {

    }

    @Override
    protected void pageTransform(View view, float position)
    {

        if (position < -1)  //滑出左屏幕的时候
        {
            view.setAlpha(mMinAlphaZero);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        }
        else if (position <= 1) //[-1, 1]
        {
            if (position < 0)   //[-1,0)    0->1  从正中间滑动左边
            {
                float factor = 1 + position;
                view.setAlpha(factor);

                float factorScale = mMinScale + (1 - mMinScale) * (1 + position);
                view.setScaleX(factorScale);
                view.setScaleY(factorScale);
            }
            else    //[0, 1]    1->0    从右边滑动到正中间
            {
                float factor = mMinAlphaNormal + (1 - mMinAlphaNormal) * (1 - position);
                view.setAlpha(factor);

                float factorScale = mMinScale + (1 - mMinScale) * (1 - position);
                view.setScaleX(factorScale);
                view.setScaleY(factorScale);
            }
        }
        else    //positon > 1
        {
            view.setAlpha(mMinAlphaNormal);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        }
    }
}
