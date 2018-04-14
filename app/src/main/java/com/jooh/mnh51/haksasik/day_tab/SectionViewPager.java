package com.jooh.mnh51.haksasik.day_tab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mnh51 on 2018-04-14.
 */

public class SectionViewPager extends ViewPager {

    boolean swipeable = false;

    public SectionViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwipeable(boolean swipeable) {
        this.swipeable = swipeable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return (this.swipeable) ? super.onInterceptTouchEvent(arg0) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return (this.swipeable) ? super.onTouchEvent(event) : false;
    }
}
