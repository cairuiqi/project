package com.bwei.crq.acitvity.myactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.adapter.AddressLlstAdapter;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.bean.AddressList;
import com.bwei.crq.presenter.AddAdressPresenter;
import com.bwei.crq.presenter.AdressListPresenter;
import com.bwei.crq.view.AddAdressView;
import com.bwei.crq.view.AdressListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 11:52:48
 * @Description:
 */
public class AddadresActivity extends BaseActivity implements AdressListView {
    @BindView(R.id.over)
    TextView over;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.addres)
    Button addadress;
    private AdressListPresenter adressListPresenter;

    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //设置布局管理器
        rv.setLayoutManager(new LinearLayoutManager(this));
        addadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddadresActivity.this, "hh", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddadresActivity.this, AddadressNextActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        SharedPreferences cun = getSharedPreferences("cun", Context.MODE_PRIVATE);
        String userId = cun.getString("userId", "");
        String sessionId = cun.getString("sessionId", "");
        Log.d("sb_adressList********", userId + "**" + sessionId);
        adressListPresenter = new AdressListPresenter(this);
        adressListPresenter.related(userId, sessionId);
        adressListPresenter.attachView(this);
    }

    //收货地址列表
    @Override
    public void onAdressListResult(List<AddressList.ResultBean> result) {
        Log.d("adresslist", result.size() + "");

        //设置适配器
        AddressLlstAdapter addressLlstAdapter = new AddressLlstAdapter(this, result);
        rv.setAdapter(addressLlstAdapter);
      /*  addressLlstAdapter.setOnItenClick(new AddressLlstAdapter.OnItenClick() {
            @Override
            public void idOnclick(int id) {
                    //设置默认地址
            }

            @Override
            public void update(AddressList.ResultBean resultBean) {
                    //修改地址
            }
        });*/

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adressListPresenter.dettach();
    }
}
