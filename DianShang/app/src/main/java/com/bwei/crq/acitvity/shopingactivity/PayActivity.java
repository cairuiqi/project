package com.bwei.crq.acitvity.shopingactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.api.Api;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.presenter.PayOrderPresenter;
import com.bwei.crq.view.PayOrderView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/27 20:10:47
 * @Description:
 */
public class PayActivity extends BaseActivity implements PayOrderView {


    @BindView(R.id.zhi)
    TextView zhi;
    @BindView(R.id.money)
    ImageView money;
    @BindView(R.id.weixin)
    ImageView weixin;
    @BindView(R.id.zhifubao)
    ImageView zhifubao;
    @BindView(R.id.moneyname)
    TextView moneyname;
    @BindView(R.id.weixinname)
    TextView weixinname;
    @BindView(R.id.zhifubaoname)
    TextView zhifubaoname;
    @BindView(R.id.moneybtn)
    RadioButton moneybtn;
    @BindView(R.id.weixinbtn)
    RadioButton weixinbtn;
    @BindView(R.id.zhifubaobtn)
    RadioButton zhifubaobtn;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.pay)
    Button pay;
    private PayOrderPresenter payOrderPresenter;
    private String tempprice;
    private String userId;
    private String sessionId;
    private String orderId;

    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String message = intent.getExtras().getString("message");
        orderId = intent.getExtras().getString("orderId");
        tempprice = intent.getExtras().getString("tempprice");
        //默认
        pay.setText("余额支付"+tempprice+"元");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //显示地址
        SharedPreferences cun = getSharedPreferences("cun", Context.MODE_PRIVATE);
        userId = cun.getString("userId", "");
        sessionId = cun.getString("sessionId", "");
        Log.d("order********", userId + "**" + sessionId + "***" + orderId);

        //支付订单mvp
        payOrderPresenter = new PayOrderPresenter(this);
        payOrderPresenter.attachView(this);

    }

    @Override
    protected void initData() {

    }

    //支付信息
    @Override
    public void onPayOrder(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (message.equals("支付成功")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.zhifu_view, null);
            Button btnone = view.findViewById(R.id.btnone);
            Button btntwo = view.findViewById(R.id.btnone);
            alert.setView(view);
            alert.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payOrderPresenter.dettach();
    }



    @OnClick({R.id.moneybtn, R.id.weixinbtn, R.id.zhifubaobtn, R.id.pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.moneybtn:
                weixinbtn.setChecked(false);
                zhifubaobtn.setChecked(false);
                settext("余额");
                break;
            case R.id.weixinbtn:
                moneybtn.setChecked(false);
                zhifubaobtn.setChecked(false);
                settext("微信");
                break;
            case R.id.zhifubaobtn:
                moneybtn.setChecked(false);
                weixinbtn.setChecked(false);
                settext("支付");
                break;
            case R.id.pay:
                setpay();
                break;
        }
    }

    //支付方式
    public void settext(String text){
        pay.setText(text+"支付"+tempprice+"元");
    }

    //支付的方法
    public void setpay(){
        if (moneybtn.isChecked()){
            payOrderPresenter.related(userId, sessionId, orderId, 1);


        }else if (zhifubaobtn.isChecked()){
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = View.inflate(this,R.layout.zhifufail_view,null);
            Button btnone= view.findViewById(R.id.btnone);
            TextView failname =view.findViewById(R.id.failname);
            failname.setText("不支持支付宝支付,请用余额支付");
            alert.setView(view);
            alert.show();
        }else if (weixinbtn.isChecked()){
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = View.inflate(this,R.layout.zhifufail_view,null);
            Button btnone= view.findViewById(R.id.btnone);
            TextView failname =view.findViewById(R.id.failname);
            failname.setText("不支持微信支付,请用余额支付");
            alert.setView(view);
            alert.show();
        }
    }



    /**沉浸式
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


}
