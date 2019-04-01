package com.bwei.crq.bean;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:35:57
 * @Description:
 */
public class ByStatusBean {


    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":17,"commodityName":"化妆镜","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/5.jpg","commodityPrice":31,"orderDetailId":1147}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190329181532426266","orderStatus":1,"payAmount":31,"payMethod":1,"userId":266},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/5.jpg","commodityPrice":278,"orderDetailId":1146}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190329173404969266","orderStatus":1,"payAmount":278,"payMethod":1,"userId":266},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/5.jpg","commodityPrice":78,"orderDetailId":1145}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190329172925324266","orderStatus":1,"payAmount":78,"payMethod":1,"userId":266},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":1144}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190329172638696266","orderStatus":1,"payAmount":39,"payMethod":1,"userId":266},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":21,"commodityName":"【加绒休闲 舒适轻便】秋冬增高休闲鞋厚底棉鞋运动户外通勤简约韩版女鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/bx/4/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/4/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/4/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/4/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/4/5.jpg","commodityPrice":189,"orderDetailId":1143}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190329172129064266","orderStatus":1,"payAmount":189,"payMethod":1,"userId":266}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

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

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":17,"commodityName":"化妆镜","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/5.jpg","commodityPrice":31,"orderDetailId":1147}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20190329181532426266
         * orderStatus : 1
         * payAmount : 31
         * payMethod : 1
         * userId : 266
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 17
             * commodityName : 化妆镜
             * commodityPic : http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/5.jpg
             * commodityPrice : 31
             * orderDetailId : 1147
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
