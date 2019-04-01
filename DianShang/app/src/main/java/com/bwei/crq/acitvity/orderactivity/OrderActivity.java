package com.bwei.crq.acitvity.orderactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.acitvity.shopingactivity.PayActivity;
import com.bwei.crq.adapter.AddressLlstAdapter;
import com.bwei.crq.adapter.CreatAdapter;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.bean.AddressList;
import com.bwei.crq.bean.CreateOrder;
import com.bwei.crq.bean.GoodsOreder;
import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.presenter.AdressListPresenter;
import com.bwei.crq.presenter.CreateOrderPresenter;
import com.bwei.crq.view.AdressListView;
import com.bwei.crq.view.CreateOrderView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 10:59:14
 * @Description:
 */
public class OrderActivity extends BaseActivity implements AdressListView ,CreateOrderView {
    @BindView(R.id.realname)
    TextView realname;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.wu)
    TextView wu;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.show_rv)
    RecyclerView showGoodsRv;
    @BindView(R.id.card_rv)
    RecyclerView cardRv;
    @BindView(R.id.btnbottom)
    CheckBox btnbottom;
    @BindView(R.id.creat)
    Button creat;
    @BindView(R.id.counttext)
    TextView counttext;

    //保存当前值的集合
    private List<GoodsOreder> listOrder;
    //值
    private String addressId;
    private double tempprice = 0.0;
    private AdressListPresenter adressListPresenter;
    private int id;
    private String orderInfo;
    private String userId;
    private String sessionId;
    private CreateOrderPresenter createOrderPresenter;


    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        listOrder = new ArrayList<>();
        //设置布局管理器

        showGoodsRv.setLayoutManager(new LinearLayoutManager(this));
        //获取穿过来的值
        Intent intent = getIntent();
        String goodsPrice = intent.getExtras().getString("goodsPrice");
        String imageView = intent.getExtras().getString("imageView");
        String goodsName = intent.getExtras().getString("goodsName");
        String commodityId1 = intent.getExtras().getString("commodityId1");
        Integer integer = Integer.valueOf(goodsPrice);
        Integer commodityId = Integer.valueOf(commodityId1);

        //设置集合
        ArrayList<QueryShopCartJson.ResultBean> orderGoodsList = new ArrayList<>();
        orderGoodsList.add(new QueryShopCartJson.ResultBean(commodityId, integer, imageView, goodsName, 1));
        //设置适配器


        CreatAdapter creatAdapter = new CreatAdapter(this, orderGoodsList);
        //设置适配器
        showGoodsRv.setAdapter(creatAdapter);
        //底部的值
        if (orderGoodsList != null && orderGoodsList.size() > 0) {
            for (QueryShopCartJson.ResultBean resultBean : orderGoodsList) {
                GoodsOreder goodsOreder = new GoodsOreder();
                goodsOreder.setCommodityId(resultBean.getCommodityId());
                goodsOreder.setAmount(resultBean.getCount());

                listOrder.add(goodsOreder);
                tempprice += resultBean.getPrice() * resultBean.getCount();
            }
            counttext.setText("共" + orderGoodsList.size() + "件商品，需付款" + tempprice + "元");
        }
        Gson gson = new Gson();
        //订单商品信息
        orderInfo = gson.toJson(listOrder);

        //显示地址
        SharedPreferences cun = getSharedPreferences("cun", Context.MODE_PRIVATE);
        userId = cun.getString("userId", "");
        sessionId = cun.getString("sessionId", "");
        Log.d("order********", userId + "**" + sessionId);
        adressListPresenter = new AdressListPresenter(this);
        adressListPresenter.related(userId, sessionId);
        adressListPresenter.attachView(this);


    }

    @Override
    protected void initData() {

    }

    //地址列表数据
    @Override
    public void onAdressListResult(List<AddressList.ResultBean> result) {
        if (result.size() > 0 && result != null) {
            for (AddressList.ResultBean resultBean : result) {
                int whetherDefault = resultBean.getWhetherDefault();
                String s = whetherDefault + "";
                if (s.equals("1")) {
                    addressId = resultBean.getId() + "";
                    realname.setText(resultBean.getRealName());
                    phone.setText(resultBean.getPhone());
                    address.setText(resultBean.getAddress());
                }
                id = result.get(whetherDefault).getId();
            }
            wu.setVisibility(View.GONE);
            //  orderAddressAdapter.setList(addressList.getResult());

            Log.d("收货地址的id",id+"");




        }


        ///创建订单  id   , tempprice ,,
        createOrderPresenter = new CreateOrderPresenter(this);
        createOrderPresenter.related(userId, sessionId, orderInfo, tempprice, id);
        createOrderPresenter.attachView(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //地址,创建订单
        adressListPresenter.dettach();
        createOrderPresenter.dettach();
    }

    //创建订单的数据
    @Override
    public void onCreateResult(final CreateOrder createOrder) {
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到支付页面
                Intent intent = new Intent(OrderActivity.this, PayActivity.class);
                intent.putExtra("message",createOrder.getMessage());
                intent.putExtra("orderId",createOrder.getOrderId());
                intent.putExtra("tempprice",tempprice+"");
                startActivity(intent);
            }
        });

    }


}
