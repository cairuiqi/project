package com.bwei.crq.bean;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 19:07:03
 * @Description:
 */
public class PayOrder {


    /**
     * message : 支付成功
     * status : 0000
     */

    private String message;
    private String status;

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
