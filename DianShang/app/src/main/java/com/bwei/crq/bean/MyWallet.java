package com.bwei.crq.bean;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 20:18:35
 * @Description:
 */
public class MyWallet {


    /**
     * result : {"balance":99999697,"detailList":[{"amount":44,"consumerTime":1553858633000,"orderId":"20190329192349402266","userId":266}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        /**
         * balance : 99999697
         * detailList : [{"amount":44,"consumerTime":1553858633000,"orderId":"20190329192349402266","userId":266}]
         */

        private int balance;
        private List<DetailListBean> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * amount : 44
             * consumerTime : 1553858633000
             * orderId : 20190329192349402266
             * userId : 266
             */

            private int amount;
            private long consumerTime;
            private String orderId;
            private int userId;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getConsumerTime() {
                return consumerTime;
            }

            public void setConsumerTime(long consumerTime) {
                this.consumerTime = consumerTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            @Override
            public String toString() {
                return "DetailListBean{" +
                        "amount=" + amount +
                        ", consumerTime=" + consumerTime +
                        ", orderId='" + orderId + '\'' +
                        ", userId=" + userId +
                        '}';
            }
        }
    }
}
