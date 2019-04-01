package com.bwei.crq.presenter;

import com.bwei.crq.bean.ByStatusBean;
import com.bwei.crq.fragment.orderfragment.PaymentFragment;
import com.bwei.crq.model.ByStatusModel;
import com.bwei.crq.view.ByStatusView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:46:03
 * @Description:
 */
public class ByStatusPresenter<T> {

    private final ByStatusModel byStatusModel;
    private Reference<T> reference;
    private final ByStatusView byStatusView;

    public ByStatusPresenter(ByStatusView view) {
        byStatusModel = new ByStatusModel();
        byStatusView = view;
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


    public void related(String userId, String sessionId, HashMap<String,String> map) {
        byStatusModel.getByStatus(userId,sessionId,map);
        byStatusModel.setOnByStatusListener(new ByStatusModel.OnByStatusListener() {
            @Override
            public void getByStatus(List<ByStatusBean.OrderListBean> orderList) {
                byStatusView.getByStatus(orderList);
            }
        });
    }
}
