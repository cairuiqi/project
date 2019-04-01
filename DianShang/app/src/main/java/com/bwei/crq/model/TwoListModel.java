package com.bwei.crq.model;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.TwoList;
import com.bwei.crq.utils.RxjavaUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/30 17:11:55
 * @Description:
 */
public class TwoListModel {

    public interface OnTwoListListener{
        void getTwoLit(List<TwoList.ResultBean> result);
    }

    private OnTwoListListener onTwoListListener;

    public void setOnTwoListListener(OnTwoListListener onTwoListListener) {
        this.onTwoListListener = onTwoListListener;
    }

    public void getTwoList(CompositeDisposable disposable, String id) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<TwoList> twoListFlowable = service.twoList(id);
        DisposableSubscriber<TwoList> disposableSubscriber = twoListFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<TwoList>() {
                    @Override
                    public void onNext(TwoList twoList) {
                        List<TwoList.ResultBean> result = twoList.getResult();
                        onTwoListListener.getTwoLit(result);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加订阅管理器
        disposable.add(disposableSubscriber);

    }
}
