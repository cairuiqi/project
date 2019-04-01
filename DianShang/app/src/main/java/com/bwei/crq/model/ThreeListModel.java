package com.bwei.crq.model;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.ThreeList;
import com.bwei.crq.utils.RxjavaUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/30 17:56:42
 * @Description:
 */
public class ThreeListModel {

    public interface OnThreeListListener{
        void getThreeList(List<ThreeList.ResultBean> result );
    }

    private OnThreeListListener onThreeListListener;

    public void setOnThreeListListener(OnThreeListListener onThreeListListener) {
        this.onThreeListListener = onThreeListListener;
    }

    public void getThreeList(CompositeDisposable compositeDisposable, String id, int page, int count) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<ThreeList> threeListFlowable = service.threeList(id, page, count);
        DisposableSubscriber<ThreeList> disposableSubscriber = threeListFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ThreeList>() {
                    @Override
                    public void onNext(ThreeList threeList) {
                        List<ThreeList.ResultBean> result = threeList.getResult();
                        onThreeListListener.getThreeList(result);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加订阅者
        compositeDisposable.add(disposableSubscriber);
    }
}
