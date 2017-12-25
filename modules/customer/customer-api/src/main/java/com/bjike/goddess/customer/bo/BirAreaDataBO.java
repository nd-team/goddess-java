package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 各业务类型客户地区分布情况数据汇总业务传输对象
 *
 * @Author: [ lijutnao ]
 * @Date: [ 2017-03-16T09:41:13.468 ]
 * @Description: [ 各业务类型客户地区分布情况数据汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BirAreaDataBO extends BaseBO {

    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 地区
     */
    private String area;

    /**
     * 客户数量
     */
    private Integer customerNum;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }
}