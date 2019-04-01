package com.bwei.crq.view;

import com.bwei.crq.bean.AddressList;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 17:37:02
 * @Description:
 */
public interface AdressListView {
    void onAdressListResult( List<AddressList.ResultBean> result);
}
