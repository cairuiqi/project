package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.shopingactivity.PayActivity;
import com.bwei.crq.model.PayOrderModel;
import com.bwei.crq.view.PayOrderView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 18:53:34
 * @Description:
 */
public class PayOrderPresenter<T> {

    private final PayOrderModel payOrderModel;
    private final PayOrderView payOrderView;
    private Reference<T> reference;

    public PayOrderPresenter(PayOrderView view) {
        payOrderModel = new PayOrderModel();
        payOrderView = view;
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

    public void related(String userId, String sessionId, String orderId, int type) {
        payOrderModel.getPayOrder(userId,sessionId,orderId,type);
        payOrderModel.setOnPayOrderListener(new PayOrderModel.OnPayOrderListener() {
            @Override
            public void onPayOrder(String message) {
                payOrderView.onPayOrder(message);
            }
        });
    }
}
