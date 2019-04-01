package com.bwei.crq.fragment.orderfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:17:37
 * @Description:
 */
public class HomeViewpage extends ViewPager {
    private boolean scro=true;
    public HomeViewpage(@NonNull Context context) {
        super(context);
    }

    public HomeViewpage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scro){
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scro){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }
}



