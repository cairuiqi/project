package com.bwei.crq.view;

import com.bwei.crq.bean.QueryShopCartJson;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 17:06:29
 * @Description:
 */
public interface QueryShopCardView {
    void  onQueryShopData(List<QueryShopCartJson.ResultBean> result );

}
