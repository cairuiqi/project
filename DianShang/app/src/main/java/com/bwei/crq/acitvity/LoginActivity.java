package com.bwei.crq.acitvity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.R;
import com.bwei.crq.bean.LoginJson;
import com.bwei.crq.presenter.LoginPresenter;
import com.bwei.crq.utils.IsPhoneUtils;
import com.bwei.crq.view.LoginView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 泛型类派生子类
 * 当创建了带泛型声明的接口、父类之后，可以为该接口创建实现类，
 * 或者从该父类派生子类，需要注意：使用这些接口、父类派生子类时不能再包含类型形参，需要传入具体的类型。
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.cb_pwd)
    CheckBox cbPwd;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.login)
    Button login;
    private SharedPreferences sp;
    private  boolean tag=true;
    private SharedPreferences cun;


    @Override
    protected LoginPresenter getPresenter() {
        presenter = new LoginPresenter(this);
        presenter.attachView(this);
        return presenter;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }


    //初始化控件
    @Override
    protected void initView() {
        ///获得sp
        sp = getSharedPreferences("jzmm", Context.MODE_PRIVATE);
        cun = getSharedPreferences("cun", Context.MODE_PRIVATE);

        ButterKnife.bind(this);

        //编辑

        if (sp.getBoolean("记住密码", false)) {
            String uname = sp.getString("phone", "");
            String upwd = sp.getString("pwd", "");
            phone.setText(uname);
            pwd.setText(upwd);
            cbPwd.setChecked(true);
        }
    }

    @Override
    protected void initData() {

    }



    @OnClick({R.id.eye, R.id.cb_pwd, R.id.reg, R.id.login})
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
                pwd.setSelection(pwd.length());
*/


                break;


            case R.id.reg:
                //跳转到注册
                startActivity(new Intent(LoginActivity.this,RegActivity.class));


                break;
            case R.id.login:
                //获得手机号和密码
                String uphone = phone.getText().toString();
                String upwd = pwd.getText().toString();
                boolean mobileNo = IsPhoneUtils.isPhoneLegal(uphone);
                //1判断手机号是否正确
                if(mobileNo){
                    //判断密码
                    if(pwd.length()<3){
                        Toast.makeText(LoginActivity.this, "密码不对", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        //创建LoginPresenter
                     //   LoginPresenter loginPresenter=new LoginPresenter(this);
                        // 编辑
                        SharedPreferences.Editor edit = sp.edit();
                        // 添加数据
                        edit.putBoolean("记住密码", cbPwd.isChecked());
                        edit.putString("phone", uphone);
                        edit.putString("pwd", upwd);

                        // 提交
                        edit.commit();

                        presenter.send(uphone,upwd);
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
        }
    }


    //返回的登录数据
    @Override
    public void onMessage(LoginJson body) {
            //
        LoginJson.ResultBean result = body.getResult();
        String headPic = result.getHeadPic();
        String nickName1 = result.getNickName();

      //传值
        EventBus.getDefault().post(headPic+","+nickName1);


        //得到uid 和sid
        int userId = body.getResult().getUserId();
        String sessionId = body.getResult().getSessionId();


         SharedPreferences.Editor edit1 = cun.edit();
        //存在sp
        edit1.putString("userId",userId+"");
        edit1.putString("sessionId",sessionId+"");
        Log.d("sb_login",userId+"**"+sessionId);
        // 提交
        edit1.commit();


        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
        Log.d("login销毁了","销毁了");
    }
}
