package com.bwei.crq.view;

import com.bwei.crq.bean.GoodsBannerJson;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 21:36:51
 * @Description:
 */
public interface GoodsBannerView {
    void  onGoodsBannerData(List<GoodsBannerJson.ResultBean> result);
}
