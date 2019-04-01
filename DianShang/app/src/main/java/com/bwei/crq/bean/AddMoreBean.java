package com.bwei.crq.bean;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/26 11:34:08
 * @Description:
 */
public class AddMoreBean {




    private int commodityId;
    private int count;

    public AddMoreBean(int s, int s1) {
        this.commodityId=s;
        this.count=s1;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
