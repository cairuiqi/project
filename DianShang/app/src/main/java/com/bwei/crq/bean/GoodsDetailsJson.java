package com.bwei.crq.bean;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 11:30:45
 * @Description:
 */
public class GoodsDetailsJson {


    /**
     * result : {"categoryId":"1001004004","categoryName":"板鞋","commentNum":0,"commodityId":20,"commodityName":"环球 冬季休闲加绒保暖棉靴 韩版百搭学生小白鞋女士高帮棉鞋","describe":"白色,35码","details":"<div class=\"dc-img\">\r\n    <div class=\"dc-img-detail\">\r\n                        <div class=\"img-6xx-bg\">\r\n            <img src=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/39/07ac0106-6aba-4a4d-b135-a8a817ae0316.jpg\" class=\"J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/39/07ac0106-6aba-4a4d-b135-a8a817ae0316.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/66/1839fa34-afe3-4f05-b250-5fbf358e28ae.jpg\" class=\"J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/66/1839fa34-afe3-4f05-b250-5fbf358e28ae.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/165/d24b42f9-8dfc-4b1c-b80d-022549484865.jpg\" class=\"J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/165/d24b42f9-8dfc-4b1c-b80d-022549484865.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/67/4b6e69ef-94d8-43f0-a490-ee81d18e4301.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/140/b7213bc0-0853-4390-a9ac-4e26fa375465.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/26/123c795b-408f-49db-a73c-eef08801a4b3.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/106/62e4a485-6925-4f36-967b-2d176b778394.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/106/84e7794c-c7d6-4e7b-9875-5fccdf0dd11a.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/63/6852064b-a1e8-4925-af9f-17f4792b7dad.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/59/3ea22151-9ce0-4b16-8ae0-00d567a5b663.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/125/65967eee-4c3e-4b3e-8d18-3b7186030362.jpg\">\r\n        <\/div>\r\n            <\/div>\r\n    <div class=\"dc-img-con\">\r\n            <\/div>\r\n    <div class=\"dc-txt-con\">\r\n            <\/div>\r\n<\/div>","picture":"http://172.17.8.100/images/small/commodity/nx/bx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/5.jpg","price":78,"saleNum":0,"stock":9999,"weight":1}
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
         * categoryId : 1001004004
         * categoryName : 板鞋
         * commentNum : 0
         * commodityId : 20
         * commodityName : 环球 冬季休闲加绒保暖棉靴 韩版百搭学生小白鞋女士高帮棉鞋
         * describe : 白色,35码
         * details : <div class="dc-img">
         <div class="dc-img-detail">
         <div class="img-6xx-bg">
         <img src="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/39/07ac0106-6aba-4a4d-b135-a8a817ae0316.jpg" class="J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/39/07ac0106-6aba-4a4d-b135-a8a817ae0316.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/66/1839fa34-afe3-4f05-b250-5fbf358e28ae.jpg" class="J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/66/1839fa34-afe3-4f05-b250-5fbf358e28ae.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/165/d24b42f9-8dfc-4b1c-b80d-022549484865.jpg" class="J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/165/d24b42f9-8dfc-4b1c-b80d-022549484865.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/67/4b6e69ef-94d8-43f0-a490-ee81d18e4301.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/140/b7213bc0-0853-4390-a9ac-4e26fa375465.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/26/123c795b-408f-49db-a73c-eef08801a4b3.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/106/62e4a485-6925-4f36-967b-2d176b778394.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/106/84e7794c-c7d6-4e7b-9875-5fccdf0dd11a.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/63/6852064b-a1e8-4925-af9f-17f4792b7dad.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/59/3ea22151-9ce0-4b16-8ae0-00d567a5b663.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1027/125/65967eee-4c3e-4b3e-8d18-3b7186030362.jpg">
         </div>
         </div>
         <div class="dc-img-con">
         </div>
         <div class="dc-txt-con">
         </div>
         </div>
         * picture : http://172.17.8.100/images/small/commodity/nx/bx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/3/5.jpg
         * price : 78
         * saleNum : 0
         * stock : 9999
         * weight : 1
         */

        private String categoryId;
        private String categoryName;
        private int commentNum;
        private int commodityId;
        private String commodityName;
        private String describe;
        private String details;
        private String picture;
        private int price;
        private int saleNum;
        private int stock;
        private int weight;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
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

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
