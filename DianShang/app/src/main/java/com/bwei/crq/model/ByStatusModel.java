package com.bwei.crq.model;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.ByStatusBean;
import com.bwei.crq.utils.RxjavaUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:45:48
 * @Description:
 */
public class ByStatusModel {


    public interface OnByStatusListener{
        void getByStatus(List<ByStatusBean.OrderListBean> orderList);
    }

    private OnByStatusListener onByStatusListener;

    public void setOnByStatusListener(OnByStatusListener onByStatusListener) {
        this.onByStatusListener = onByStatusListener;
    }

    public void getByStatus(String userId, String sessionId, HashMap<String, String> map) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<ByStatusBean> bystatus = service.bystatus(userId, sessionId, map);
        bystatus.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ByStatusBean>() {
                    @Override
                    public void onNext(ByStatusBean byStatusBean) {
                        List<ByStatusBean.OrderListBean> orderList = byStatusBean.getOrderList();
                        onByStatusListener.getByStatus(orderList);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
