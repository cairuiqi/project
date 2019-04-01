package com.bwei.crq.acitvity;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.presenter.RegPresenter;
import com.bwei.crq.utils.IsPhoneUtils;
import com.bwei.crq.view.RegView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends BaseActivity<RegPresenter> implements RegView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yzm)
    EditText yzm;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.reg)
    Button reg;


    private  boolean tag=true;



    @Override
    protected RegPresenter getPresenter() {
        presenter = new RegPresenter(this);
        presenter.attachView(this);
        return presenter;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);


    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.eye, R.id.login, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye:
                tag=!tag;
                //5、判断事件源的选中状态
                if (tag){

                    //显示密码
                    //etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    // 隐藏密码
                    //etPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            /*    //6、每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
                pwd.setSelection(pwd.length());*/


                break;
            case R.id.login:
                //跳转找登录界面
                startActivity(new Intent(RegActivity.this,LoginActivity.class));
                finish();

                break;
            case R.id.reg:

                //获得手机号和密码
                String uphone = phone.getText().toString();
                String upwd = pwd.getText().toString();
                boolean mobile = IsPhoneUtils.isPhoneLegal(uphone);
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", uphone);
                map.put("pwd", upwd);
                //1判断手机号格式是否正确
                if (mobile) {
                    //判断密码
                    if (pwd.length() < 3) {
                        Toast.makeText(RegActivity.this, "密码不对", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        presenter.send(map);
                    }
                } else {
                    Toast.makeText(RegActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
        }
    }

    //注册返回的数据
    @Override
    public void onMessage(String message) {

        if(message.equals("注册成功")){
            //跳转
            Intent intent = new Intent(RegActivity.this,LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
        Log.d("Reg销毁了","销毁了");
    }
}
