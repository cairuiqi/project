package com.bwei.crq.bean;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 11:35:37
 * @Description:
 */
public class GoodsOreder {


    /**
     * commodityId : 3  商品id
     * amount : 1   商品数量
     */

    private int commodityId;
    private int amount;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
