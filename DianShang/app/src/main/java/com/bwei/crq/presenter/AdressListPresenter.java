package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.myactivity.AddadresActivity;
import com.bwei.crq.bean.AddressList;
import com.bwei.crq.model.AdressListModel;
import com.bwei.crq.view.AddAdressView;
import com.bwei.crq.view.AdressListView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 17:36:53
 * @Description:
 */
public class AdressListPresenter<T>{


    private final AdressListModel adressListModel;
    private final AdressListView adressListView;
    private Reference<T> reference;


    public AdressListPresenter(AdressListView view) {
        adressListModel = new AdressListModel();
        adressListView = view;
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



    public void related(String userId, String sessionId) {
        adressListModel.getAdressList(userId,sessionId);
        adressListModel.setOnAdressListListener(new AdressListModel.OnAdressListListener() {
            @Override
            public void onAdressListResult(List<AddressList.ResultBean> result) {
                adressListView.onAdressListResult(result);
            }
        });
    }
}
