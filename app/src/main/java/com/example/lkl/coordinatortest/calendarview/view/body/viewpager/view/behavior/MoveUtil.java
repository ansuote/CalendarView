package com.example.lkl.coordinatortest.calendarview.view.body.viewpager.view.behavior;

import android.util.Log;
import android.view.View;

/**
 * Created by YCJ on 2015/11/30.
 */
public class MoveUtil {

    private static int clamp(int value, int min, int max) {
        if (value > max) {
            Log.i("lkl", "clamp -- value > max -- value = " + value);
            return max;
        } else if (value < min) {
            Log.i("lkl", "clamp -- value < min -- value = " + value);
            return min;
        } else {
            Log.i("lkl", "clamp -- value 在中间 -- value = " + value);
            return value;
        }
    }

    //Scroll the view and return back the actual distance scrolled
    public static  int move(View child, int dy, int minOffset, int maxOffset) {
        final int initialOffset = child.getTop();
        //Clamped new position - initial position = offset change
        int delta = clamp(initialOffset - dy, minOffset, maxOffset) - initialOffset;

        Log.i("lkl", "MoveUtil ---- initialOffset = " + initialOffset
                + "; minOffset = " + minOffset
                + "; maxOffset = " + maxOffset
                + "; delta = " + delta);

        child.offsetTopAndBottom(delta);

        return -delta;
    }
}
