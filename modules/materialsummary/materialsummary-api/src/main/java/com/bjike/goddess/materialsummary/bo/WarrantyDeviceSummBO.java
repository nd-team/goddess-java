package com.bjike.goddess.materialsummary.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 设备维修汇总业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 设备维修汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WarrantyDeviceSummBO extends BaseBO {

    /**
     * 是否在保修期
     */
    private String warranty;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门（项目）
     */
    private String department;

    /**
     * 总数量
     */
    private Integer totalNum;

    /**
     * 总金额
     */
    private Double totalAmount;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}