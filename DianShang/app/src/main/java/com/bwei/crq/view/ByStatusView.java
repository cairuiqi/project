package com.bwei.crq.view;

import com.bwei.crq.bean.ByStatusBean;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:46:27
 * @Description:
 */
public interface ByStatusView {
    void getByStatus(List<ByStatusBean.OrderListBean> orderList);
}
