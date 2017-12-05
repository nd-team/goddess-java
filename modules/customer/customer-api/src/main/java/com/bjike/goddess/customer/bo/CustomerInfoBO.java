package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户基本信息业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.044 ]
 * @Description: [ 客户基本信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoBO extends BaseBO {

//    /**
//     * 客户信息编号
//     */
//    private String customerNum;

    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 地区
     */
    private String area;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 现业务有关联公司
     */
    private String currentBusiness;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCurrentBusiness() {
        return currentBusiness;
    }

    public void setCurrentBusiness(String currentBusiness) {
        this.currentBusiness = currentBusiness;
    }
}