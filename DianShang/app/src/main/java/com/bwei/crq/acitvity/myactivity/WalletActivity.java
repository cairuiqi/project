package com.bwei.crq.acitvity.myactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.adapter.MyWalletAdapter;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.bean.MyWallet;
import com.bwei.crq.presenter.MyWalletPresenter;
import com.bwei.crq.view.MyWalletView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 11:29:13
 * @Description:
 */
public class WalletActivity extends BaseActivity implements MyWalletView {
    @BindView(R.id.wall_money)
    TextView wallMoney;
    @BindView(R.id.wall_recycle)
    RecyclerView wallRecycle;
    int page = 1;
    int count = 100;
    private MyWalletPresenter myWalletPresenter;

    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        wallRecycle.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences cun = getSharedPreferences("cun", Context.MODE_PRIVATE);

        String userId = cun.getString("userId", "");
        String sessionId = cun.getString("sessionId", "");
        Log.d("sb_my", userId + "**" + sessionId);
        //我的钱包
        myWalletPresenter = new MyWalletPresenter(this);
        myWalletPresenter.attachView(this);
        myWalletPresenter.related(userId, sessionId, page, count);
    }

    @Override
    protected void initData() {


    }


    //我的钱包
    @Override
    public void onMyWallet(MyWallet.ResultBean result) {
        wallMoney.setText("¥"+result.getBalance() + "");
        List<MyWallet.ResultBean.DetailListBean> detailList = result.getDetailList();
        MyWalletAdapter myWalletAdapter = new MyWalletAdapter(this, detailList);
        wallRecycle.setAdapter(myWalletAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myWalletPresenter.dettach();
    }
}
