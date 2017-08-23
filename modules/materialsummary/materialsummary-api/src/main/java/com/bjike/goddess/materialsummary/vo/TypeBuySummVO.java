package com.bjike.goddess.materialsummary.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 物质购买汇总业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TypeBuySummVO{

    /**
     * 类型
     */
    private String type;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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