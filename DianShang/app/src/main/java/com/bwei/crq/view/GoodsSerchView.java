package com.bwei.crq.view;

import com.bwei.crq.bean.GoodsSerchJson;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 20:58:47
 * @Description:
 */
public interface GoodsSerchView {
    void onGoodsSerchResult(List<GoodsSerchJson.ResultBean> result);
}
