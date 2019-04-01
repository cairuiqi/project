package com.bwei.crq.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.acitvity.DetailsActivity;
import com.bwei.crq.acitvity.LoginActivity;
import com.bwei.crq.acitvity.orderactivity.OrderActivity;
import com.bwei.crq.adapter.MyShppingAdapter;
import com.bwei.crq.base.BaseFragment;
import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.presenter.QueryShopCardPresenter;
import com.bwei.crq.view.QueryShopCardView;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/17 16:29:21
 * @Description:
 */
public class ShoppingFragment extends BaseFragment implements QueryShopCardView {
    private boolean isGetData = false;
    @BindView(R.id.shopcar)
    RecyclerView shopcar;
    @BindView(R.id.qx)
    CheckBox qx;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.countprice)
    TextView countprice;
    @BindView(R.id.settlement)
    Button settlement;
    private QueryShopCardPresenter queryShopCardPresenter;
    private QueryShopCardPresenter queryShopCardPresenter1;
    private MyShppingAdapter myShppingAdapter;


    @Override
    protected int layoutResID() {
        return R.layout.shoppingment;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

    }


    @Override
    protected void loadData() {

        shopcar.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences cun = getActivity().getSharedPreferences("cun", getActivity().MODE_PRIVATE);


        String userId = cun.getString("userId", "");
        String sessionId = cun.getString("sessionId", "");
        //查询购物侧mvp
        if (!userId.equals("")){
            queryShopCardPresenter = new QueryShopCardPresenter(this);
            queryShopCardPresenter.related(userId,sessionId);
            queryShopCardPresenter.attachView(this);

        }


    }

    //查询购物车的数据
    @Override
    public void onQueryShopData(final List<QueryShopCartJson.ResultBean> result) {



        settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if (result.size()>0){
                    Intent intent = new Intent(getActivity(), OrderActivity.class);

                    intent.putExtra("goodsPrice",price+"");
                    intent.putExtra("imageView",);
                    intent.putExtra("goodsName",commodityName+"");
                    intent.putExtra("commodityId1",commodityId1+"");
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"请选择商品",Toast.LENGTH_SHORT).show();
                }*/
               /* Intent intent = new Intent(getActivity(), OrderActivity.class);
                //图片地址

                intent.putExtra("goodsPrice",price+"");
                intent.putExtra("imageView",);
                intent.putExtra("goodsName",commodityName+"");
                intent.putExtra("commodityId1",commodityId1+"");
                //跳转到待支付订单页面
                startActivity(intent);*/
            }
        });



        //设置给适配器

        myShppingAdapter = new MyShppingAdapter(getActivity(),result);
        //条目点击事件

        shopcar.setAdapter(myShppingAdapter);
        //1点击全选的方法返回总价的方法
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price = myShppingAdapter.qx(qx.isChecked());
                //设置价格
                countprice.setText("￥:"+price);
            }
        });

        //2条目点击控制全选 和价格
        myShppingAdapter.setQxOnclick(new MyShppingAdapter.QxOnclick() {
            @Override
            public void OnClick() {
                if (myShppingAdapter.checknotity()){
                    qx.setChecked(true);
                }else{
                    qx.setChecked(false);
                }
                double getprice = myShppingAdapter.getprice();
                countprice.setText("￥:"+getprice);
            }
        });

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            SharedPreferences cun = getActivity().getSharedPreferences("cun", getActivity().MODE_PRIVATE);
            String userId = cun.getString("userId", "");
            String sessionId = cun.getString("sessionId", "");

            if(userId.equals("")){
                Toast.makeText(getActivity(), "购物车需要登陆", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(getActivity()).setTitle("需要登录")//设置对话框标题

                        .setMessage("是否确定登录")//设置显示的内容

                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮


                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                startActivity(new Intent(getActivity(),LoginActivity.class));


                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮



                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件


                        Log.i("alertdialog"," 请保存数据！");

                    }

                }).show();//在按键响应事件中显示此对话框*/


            }else{

                queryShopCardPresenter1 = new QueryShopCardPresenter(this);
                queryShopCardPresenter1.related(userId,sessionId);
            }
          
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }







    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    //内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        queryShopCardPresenter.dettach();
    }


}
