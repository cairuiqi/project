package com.bwei.crq.presenter;

import com.bwei.crq.acitvity.DetailsActivity;
import com.bwei.crq.bean.GoodsDetailsJson;
import com.bwei.crq.model.DetailsModel;
import com.bwei.crq.view.DetailsView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.sql.Ref;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 11:24:15
 * @Description:
 */
public class DetailsPresenter<T> {


    private final DetailsModel detailsModel;
    private final DetailsView detailsView;
    private Reference<T> reference;

    public DetailsPresenter(DetailsView view) {
        detailsModel = new DetailsModel();
        detailsView = view;
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


    public void related(int commodityId) {
        detailsModel.getGoodsDetails(commodityId);
        detailsModel.setOnGoodsDetailsListener(new DetailsModel.OnGoodsDetailsListener() {
            @Override
            public void onGoodsDetailsData(GoodsDetailsJson.ResultBean result) {
                detailsView.onGoodsDetailsData(result);
            }
        });
    }
}
