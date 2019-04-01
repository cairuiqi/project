package com.bwei.crq.acitvity.myactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.presenter.AddAdressPresenter;
import com.bwei.crq.utils.IsPhoneUtils;
import com.bwei.crq.view.AddAdressView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 11:57:44
 * @Description:
 */
public class AddadressNextActivity extends BaseActivity implements AddAdressView {


    //申明对象
    CityPickerView mPicker = new CityPickerView();
    @BindView(R.id.aa)
    TextView aa;
    @BindView(R.id.shou)
    TextView shou;
    @BindView(R.id.shouname)
    EditText shouname;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.phonename)
    EditText phonename;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.addressname)
    TextView addressname;
    @BindView(R.id.xiang)
    TextView xiang;
    @BindView(R.id.xiangname)
    EditText xiangname;
    @BindView(R.id.office)
    TextView office;
    @BindView(R.id.officename)
    EditText officename;
    @BindView(R.id.card)
    ConstraintLayout card;
    @BindView(R.id.addadress)
    Button addadress;
    private String userId;
    private String sessionId;
    private AddAdressPresenter addAdressPresenter;


    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_addadress;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mPicker.init(this);
    }

    @Override
    protected void initData() {
        SharedPreferences cun = getSharedPreferences("cun", Context.MODE_PRIVATE);

        userId = cun.getString("userId", "");
        sessionId = cun.getString("sessionId", "");
        Log.d("sb_addnext********", userId + "**" + sessionId);





    }

    public void imgclick(View view) {

        //添加默认的配置，不需要自己定义，当然也可以自定义相关熟悉，详细属性请看demo
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);
        //显示
        mPicker.showCityPicker();
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                addressname.setText(province.getName() + city.getName() + district.getName());
                //省份province
                //城市city
                //地区district
            }

            @Override
            public void onCancel() {

            }
        });

    }




    @OnClick(R.id.addadress)
    public void onViewClicked() {


        HashMap<String, String> map = new HashMap<>();
        map.put("realName", shouname.getText().toString());
        map.put("phone", phonename.getText().toString());
        map.put("address", addressname.getText().toString() + xiangname.getText().toString());
        map.put("zipCode", officename.getText().toString());
        addAdressPresenter = new AddAdressPresenter(this);
        addAdressPresenter.related(userId, sessionId, map);
        addAdressPresenter.attachView(this);
    }


    //返回的数据
    @Override
    public void onMessage(String message) {
            if(message.equals("添加成功")){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        addAdressPresenter.dettach();
    }
}
