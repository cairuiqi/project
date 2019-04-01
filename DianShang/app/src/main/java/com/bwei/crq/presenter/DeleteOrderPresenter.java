package com.bwei.crq.presenter;

import com.bwei.crq.fragment.orderfragment.PaymentFragment;
import com.bwei.crq.model.DeleteOrderModel;
import com.bwei.crq.view.DeleteOrderView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 19:24:38
 * @Description:
 */
public class DeleteOrderPresenter <T>{


    private final DeleteOrderModel deleteOrderModel;
    private final DeleteOrderView deleteOrderView;
    private Reference<T> reference;

    public DeleteOrderPresenter(DeleteOrderView view) {
        deleteOrderModel = new DeleteOrderModel();
        deleteOrderView = view;
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


    public void related(String userId, String sessionId, String orderId) {
        deleteOrderModel.deleteOrder(userId,sessionId,orderId);
        deleteOrderModel.setOnDeleteOrderListener(new DeleteOrderModel.OnDeleteOrderListener() {
            @Override
            public void deleteOrder(String message) {
                deleteOrderView.deleteOrder(message);
            }
        });
    }
}
