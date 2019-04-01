package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.myactivity.AddadressNextActivity;
import com.bwei.crq.bean.LoginJson;
import com.bwei.crq.model.AddAdressModel;
import com.bwei.crq.model.LoginModel;
import com.bwei.crq.view.AddAdressView;
import com.bwei.crq.view.LoginView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 16:04:21
 * @Description:
 */
public class AddAdressPresenter <T>{


    private Reference<T> reference;
    private final AddAdressModel addAdressModel;
    private final AddAdressView addAdressView;

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

    public AddAdressPresenter(AddAdressView view) {
        addAdressModel = new AddAdressModel();
        addAdressView = view;
    }

    public void related(String userId, String sessionId, HashMap<String,String> map) {
        addAdressModel.getAdressData(userId,sessionId,map);
        addAdressModel.setOnAddAdressListener(new AddAdressModel.OnAddAdressListener() {
            @Override
            public void onMessage(String message) {
                addAdressView.onMessage(message);
            }
        });
    }


}
