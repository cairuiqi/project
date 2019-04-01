package com.bwei.crq.presenter;

import com.bwei.crq.model.RegModel;
import com.bwei.crq.view.RegView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 14:26:17
 * @Description:
 */
public class RegPresenter<T> {

    private final RegModel regModel;
    private final RegView regView;
    private Reference<T> reference;

    public RegPresenter(RegView view) {
        regModel = new RegModel();
        regView = view;
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


    public void send(HashMap<String,String> hash) {
        regModel.getRegData(hash);
        regModel.setOnLoginListener(new RegModel.OnRegListener() {
            @Override
            public void onMessage(String message) {
                regView.onMessage(message);
            }
        });

    }

}
