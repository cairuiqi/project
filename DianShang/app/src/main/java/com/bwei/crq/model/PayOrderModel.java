package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.PayOrder;
import com.bwei.crq.utils.RxjavaUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 18:53:49
 * @Description:
 */
public class PayOrderModel {


    public interface OnPayOrderListener{
        void onPayOrder(String message);
    }

    private OnPayOrderListener onPayOrderListener;

    public void setOnPayOrderListener(OnPayOrderListener onPayOrderListener) {
        this.onPayOrderListener = onPayOrderListener;
    }

    public void getPayOrder(String userId, String sessionId, String orderId, int type) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<PayOrder> payOrderFlowable = service.payOrder(userId, sessionId, orderId, type);
        payOrderFlowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<PayOrder>() {
                            @Override
                            public void onNext(PayOrder payOrder) {
                                String message = payOrder.getMessage();
                                Log.d("pay",message);
                                onPayOrderListener.onPayOrder(message);
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
