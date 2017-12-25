package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 获取客户编号和姓名
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-03-15T17:00:40.870 ]
 * @Description: [ 获取客户编号和姓名 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerNameNumBO extends BaseBO {

    /**
     * 客户信息编号
     */
    private String customerNum;
    /**
     * 客户姓名
     */
    private String customerName;

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