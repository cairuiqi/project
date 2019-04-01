package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.DetailsActivity;
import com.bwei.crq.model.ShppingAddModel;
import com.bwei.crq.view.ShppingAddView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 20:30:16
 * @Description:
 */
public class ShppingAddPresenter <T>{

    private final ShppingAddModel shppingAddModel;
    private final ShppingAddView shppingAddView;
    private Reference<T> reference;

    public ShppingAddPresenter(ShppingAddView view) {
        shppingAddModel = new ShppingAddModel();
        shppingAddView = view;
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



    public void related(String map, String userId, String sessionId) {
        shppingAddModel.getShppingAddData(map,userId,sessionId);
        shppingAddModel.setOnShoppingAddListener(new ShppingAddModel.OnShoppingAddListener() {
            @Override
            public void onMessage(String message) {
                shppingAddView.onMessage(message);
            }
        });
    }
}
