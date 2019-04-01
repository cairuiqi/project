package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.CreateOrder;
import com.bwei.crq.utils.RetroficUtils;
import com.bwei.crq.utils.RxjavaUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 15:45:07
 * @Description:
 */
public class CreateOrderModel {


    public interface  OnCreateOderListener{
        void onCreateResult(CreateOrder createOrder);
    }

    private OnCreateOderListener onCreateOderListener;

    public void setOnCreateOderListener(OnCreateOderListener onCreateOderListener) {
        this.onCreateOderListener = onCreateOderListener;
    }

    public void getCreteOrderData(String userId, String sessionId, String orderInfo, double tempprice, int id) {
        //请求
        ApiService apiService = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<CreateOrder> createOrderFlowable = apiService.createOrder(userId,sessionId,orderInfo, tempprice, id);
        createOrderFlowable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSubscriber<CreateOrder>() {
                                @Override
                                public void onNext(CreateOrder createOrder) {

                                    if (onCreateOderListener!=null){
                                        onCreateOderListener.onCreateResult(createOrder);
                                    }

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
