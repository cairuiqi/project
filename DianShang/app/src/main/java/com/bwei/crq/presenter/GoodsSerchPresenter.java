package com.bwei.crq.presenter;

import com.bwei.crq.bean.GoodsSerchJson;
import com.bwei.crq.custom.HomeSerch;
import com.bwei.crq.model.GoodsSerchModel;
import com.bwei.crq.view.GoodsSerchView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 20:19:09
 * @Description:
 */
public class GoodsSerchPresenter<T> {



    private final GoodsSerchModel goodsSerchModel;
    private final GoodsSerchView goodsSerchView;
    private Reference<T> reference;

    public GoodsSerchPresenter(GoodsSerchView view) {
        goodsSerchModel = new GoodsSerchModel();
        goodsSerchView = view;
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




    public void related(String data, int page, int count) {
        goodsSerchModel.getSerchData(data,page,count);
        goodsSerchModel.setOnGetGoodSerchsListen(new GoodsSerchModel.OnGetGoodSerchsListen() {
            @Override
            public void onGoodsSerchResult(List<GoodsSerchJson.ResultBean> result) {
                goodsSerchView.onGoodsSerchResult(result);
            }
        });
    }
}
