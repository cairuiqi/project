package com.bwei.crq.bean;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 16:09:37
 * @Description:
 */
public class CreateOrder {


    /**
     * orderId : 20190329162533602266
     * message : 创建订单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
