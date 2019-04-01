package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.LoginActivity;
import com.bwei.crq.bean.LoginJson;
import com.bwei.crq.model.LoginModel;
import com.bwei.crq.view.LoginView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 14:55:24
 * @Description:
 */
public class LoginPresenter <T>{



    private final LoginModel loginModel;
    private final LoginView loginView;
    private Reference<T> reference;


    public LoginPresenter(LoginView view) {
        //创建LoginModel
        loginModel = new LoginModel();
        loginView = view;
    }


    //内存泄漏
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }


    public void dettach(){
        if(reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }



    public void send(String uphone, String upwd) {
        loginModel.login(uphone,upwd);
        loginModel.setOnLoginListener(new LoginModel.OnLoginListener() {
            @Override
            public void onMessage(LoginJson body) {
                loginView.onMessage(body);
            }
        });
    }
}
