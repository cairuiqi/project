package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.ThreeListActivity;
import com.bwei.crq.bean.ThreeList;
import com.bwei.crq.model.ThreeListModel;
import com.bwei.crq.view.ThreeListView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/30 17:56:59
 * @Description:
 */
public class ThreeListPresenter<T>{

    private final ThreeListModel threeListModel;
    private Reference<T> reference;
    private final ThreeListView threeListView;

    public ThreeListPresenter(ThreeListView view) {
        threeListModel = new ThreeListModel();
        threeListView = view;
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


    public void related(CompositeDisposable compositeDisposable, String id, int page, int count) {
        threeListModel.getThreeList(compositeDisposable,id,page,count);
        threeListModel.setOnThreeListListener(new ThreeListModel.OnThreeListListener() {
            @Override
            public void getThreeList(List<ThreeList.ResultBean> result) {
                threeListView.getThreeList(result);
            }
        });
    }
}
