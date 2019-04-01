package com.bwei.crq.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwei.crq.fragment.orderfragment.PaymentFragment;
import com.bwei.crq.fragment.orderfragment.ReceivingFragment;
import com.bwei.crq.fragment.orderfragment.WholeFragment;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:23:50
 * @Description:
 */
public class ListPageAdapter extends FragmentPagerAdapter {
    public ListPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                 return new WholeFragment();
            case 1:
                return new PaymentFragment();
            case 2:
                return new ReceivingFragment();
            case 3:
             //   return new EvaluateFragment();
            case 4:
           //     return new OverFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
