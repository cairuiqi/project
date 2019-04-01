package com.bwei.crq.bean;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 17:29:00
 * @Description:
 */
public class AddressList {


    /**
     * result : [{"address":"浙江省杭州市滨江区","createTime":1553820620000,"id":2082,"phone":"13011067611","realName":"擦雌蕊器","userId":266,"whetherDefault":1,"zipCode":"101010"},{"address":"浙江省杭州市滨江区cc1","createTime":1553822116000,"id":2083,"phone":"13133333333","realName":"aa","userId":266,"whetherDefault":2,"zipCode":"101010"},{"address":"浙江省杭州市滨江区","createTime":1553827883000,"id":2085,"phone":"13011067611","realName":"菜如期","userId":266,"whetherDefault":2,"zipCode":"101010"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 浙江省杭州市滨江区
         * createTime : 1553820620000
         * id : 2082
         * phone : 13011067611
         * realName : 擦雌蕊器
         * userId : 266
         * whetherDefault : 1
         * zipCode : 101010
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
