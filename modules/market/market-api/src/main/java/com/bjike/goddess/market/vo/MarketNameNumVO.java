package com.bjike.goddess.market.vo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 客户姓名和客户编号
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-03-21T11:00:01.544 ]
 * @Description: [ 客户姓名和客户编号 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketNameNumVO{
    /**
     * 客户信息id
     */
    private String id;
    /**
     * 客户信息编号
     */
    private String customerNum;
    /**
     * 客户姓名
     */
    private String customerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}