package com.bwei.crq.presenter;

import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.fragment.ShoppingFragment;
import com.bwei.crq.model.QueryShopCardModel;
import com.bwei.crq.view.QueryShopCardView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 17:06:03
 * @Description:
 */
public class QueryShopCardPresenter<T> {

    private final QueryShopCardModel queryShopCardModel;
    private final QueryShopCardView queryShopCardView;
    private Reference<T> reference;

    public QueryShopCardPresenter(QueryShopCardView view) {
        queryShopCardModel = new QueryShopCardModel();
        queryShopCardView = view;
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



    public void related(String userId, String sessionId) {
        queryShopCardModel.getQueryShopCartData(userId,sessionId);
        queryShopCardModel.setOnShppingListener(new QueryShopCardModel.OnShppingListener() {
            @Override
            public void onShoppingData(List<QueryShopCartJson.ResultBean> result) {
                queryShopCardView.onQueryShopData(result);
            }
        });
    }
}
