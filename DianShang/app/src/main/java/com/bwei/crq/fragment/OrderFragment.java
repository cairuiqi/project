package com.bwei.crq.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.adapter.ListPageAdapter;
import com.bwei.crq.base.BaseFragment;
import com.bwei.crq.fragment.orderfragment.HomeViewpage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/17 16:29:21
 * @Description:
 */
public class OrderFragment extends BaseFragment {


    @BindView(R.id.over)
    ImageView over;
    @BindView(R.id.daimoney)
    ImageView daimoney;
    @BindView(R.id.daishou)
    ImageView daishou;
    @BindView(R.id.daiping)
    ImageView daiping;
    @BindView(R.id.yiover)
    ImageView yiover;
    @BindView(R.id.card)
    CardView card;
    Unbinder unbinder;

    @BindView(R.id.viewpage)
    HomeViewpage viewpage;

    @Override
    protected int layoutResID() {
        return R.layout.orderfragment;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }


    @Override
    protected void loadData() {
        Log.d("frag", "OrderFragment");
        ListPageAdapter listPageAdapter = new ListPageAdapter(getChildFragmentManager());
        viewpage.setAdapter(listPageAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.over, R.id.daimoney, R.id.daishou, R.id.daiping, R.id.yiover})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.over:
                viewpage.setCurrentItem(0);
                Log.i("hpp",0+"");
                break;
            case R.id.daimoney:
                viewpage.setCurrentItem(1);
                Log.i("hpp",1+"");
                break;
            case R.id.daishou:
                viewpage.setCurrentItem(2);
                Log.i("hpp",2+"");
                break;
            case R.id.daiping:
                viewpage.setCurrentItem(3);
                Log.i("hpp",3+"");
                break;
            case R.id.yiover:
                viewpage.setCurrentItem(4);
                Log.i("hpp",4+"");
                break;
        }
    }
}
