package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.orderactivity.OrderActivity;
import com.bwei.crq.bean.CreateOrder;
import com.bwei.crq.model.CreateOrderModel;
import com.bwei.crq.view.CreateOrderView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 15:45:23
 * @Description:
 */
public class CreateOrderPresenter<T> {

    private final CreateOrderModel createOrderModel;
    private Reference<T> reference;
    private final CreateOrderView createOrderView;

    public CreateOrderPresenter(CreateOrderView view) {
        createOrderModel = new CreateOrderModel();
        createOrderView = view;
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



    public void related(String userId, String sessionId, String orderInfo, double tempprice, int id) {
        createOrderModel.getCreteOrderData(userId,sessionId,orderInfo,tempprice,id);
        createOrderModel.setOnCreateOderListener(new CreateOrderModel.OnCreateOderListener() {
            @Override
            public void onCreateResult(CreateOrder createOrder) {
                createOrderView.onCreateResult(createOrder);
            }
        });

    }
}
