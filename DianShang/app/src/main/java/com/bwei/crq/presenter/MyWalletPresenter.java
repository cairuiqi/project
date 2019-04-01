package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.myactivity.WalletActivity;
import com.bwei.crq.bean.MyWallet;
import com.bwei.crq.model.MyWalletModel;
import com.bwei.crq.view.MyWalletView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 20:15:03
 * @Description:
 */
public class MyWalletPresenter<T> {

    private final MyWalletModel myWalletModel;
    private Reference<T> reference;
    private final MyWalletView myWalletView;

    public MyWalletPresenter(MyWalletView view) {
        myWalletModel = new MyWalletModel();
        myWalletView = view;
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



    public void related(String userId, String sessionId, int page, int count) {
        myWalletModel.getWallet(userId,sessionId,page,count);
        myWalletModel.setOnMyWalletListener(new MyWalletModel.OnMyWalletListener() {
            @Override
            public void onMyWallet(MyWallet.ResultBean result) {
                myWalletView.onMyWallet(result);
            }
        });
    }
}
