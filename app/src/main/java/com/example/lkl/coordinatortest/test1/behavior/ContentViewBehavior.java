package com.example.lkl.coordinatortest.test1.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lkl.coordinatortest.test1.ContentView;
import com.example.lkl.coordinatortest.test1.TitleView;

/**
 * Created by LKL on 2016-8-4.
 */
public class ContentViewBehavior extends CoordinatorLayout.Behavior<TitleView>{
    private int mTop = 0;
    private int dependentViewTop = -1;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TitleView child, View dependency)
    {
        Log.i("lkl", "ContentViewBehavior --- layoutDependsOn");
        return dependency instanceof ContentView;
    }

    /**
     * 点击view的时候会触发该方法
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, TitleView child, int layoutDirection)
    {
        Log.i("lkl", "我已经进入onLayoutChild方法了 --- layoutDirection = " + layoutDirection);
        parent.onLayoutChild(child, layoutDirection);
        child.setY(mTop);
        //child.offsetTopAndBottom(mTop);
        return true;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, TitleView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed)
    {
        final int measuredHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec)/2;
        int childMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY);
        child.measure(parentWidthMeasureSpec, childMeasureSpec);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TitleView child, View dependency)
    {
        Log.i("lkl", "ContentViewBehavior --- onDependentViewChanged");

        if (-1 != dependentViewTop){
            int dy = dependency.getTop() - dependentViewTop;
            int top = child.getTop();
            TextView textView2 = (TextView) child.getChildAt(1);
            if (dy > -top)
            {
                Log.i("lkl", " ---- 1 ---- dy = " + dy + "; -top = " + (-top));
                dy = -top;
               // dy = 0;
            }

            //如果scrolling up 移动到选中的位置之后
            if (dy < -top - textView2.getTop())
            //~~if (dy < -top - child.getTopMovableDistance())
            {

                //dy = -top - child.getTopMovableDistance();
                dy = -top - textView2.getTop();
                Log.i("lkl", " ---- 2 ---- dy = " + dy + "; top = " + top + "; textView2.getTop() = " + textView2.getTop());
            }

           Log.i("lkl", " ---- 3 ---- dy = " + dy + "; top = " + top);
            child.offsetTopAndBottom(dy);
        }
        dependentViewTop = dependency.getTop();
        mTop = child.getTop();
        return true;

    }


}
