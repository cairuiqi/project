package com.bwei.crq.model;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.DeleteOrder;
import com.bwei.crq.utils.RxjavaUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 19:25:43
 * @Description:
 */
public class DeleteOrderModel {

    public interface OnDeleteOrderListener{
        void deleteOrder(String message);
    }

    private OnDeleteOrderListener onDeleteOrderListener;

    public void setOnDeleteOrderListener(OnDeleteOrderListener onDeleteOrderListener) {
        this.onDeleteOrderListener = onDeleteOrderListener;
    }

    public void deleteOrder(String userId, String sessionId, String orderId) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<DeleteOrder> deleteOrderFlowable = service.deleteOrder("small/order/verify/v1/deleteOrder",userId, sessionId, orderId);
        deleteOrderFlowable.subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribeWith(new DisposableSubscriber<DeleteOrder>() {
                               @Override
                               public void onNext(DeleteOrder deleteOrder) {
                                   String message = deleteOrder.getMessage();
                                   onDeleteOrderListener.deleteOrder(message);
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
