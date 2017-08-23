package com.bjike.goddess.costdetail.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 主营业务收入明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:27 ]
 * @Description: [ 主营业务收入明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "costdetail_businessincomedetail")
public class BusinessIncomeDetail extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "costTime", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate costTime;

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;
    /**
     * 分类名
     */
    @Column(name = "typeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类名'")
    private String typeName;

    /**
     * 主营业务收入合计
     */
    @Column(name = "businessIncomeSum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '主营业务收入合计'")
    private Double businessIncomeSum;

    /**
     * 主营业务收入十日
     */
    @Column(name = "businessIncomeTen", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '主营业务收入十日'")
    private Double businessIncomeTen;

    /**
     * 主营业务收入十五日
     */
    @Column(name = "businessIncomeFift", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '主营业务收入十五日'")
    private Double businessIncomeFift;

    /**
     * 主营业务收入二十日
     */
    @Column(name = "businessIncomeTwtenty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '主营业务收入二十日'")
    private Double businessIncomeTwtenty;

    /**
     * 主营业务收入三十日
     */
    @Column(name = "businessIncomeThirty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '主营业务收入三十日'")
    private Double businessIncomeThirty;

    /**
     * 成本明细表id
     */
    @Column(name = "costId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '成本明细表id'")
    private String costId;


    public LocalDate getCostTime() {
        return costTime;
    }

    public void setCostTime(LocalDate costTime) {
        this.costTime = costTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getBusinessIncomeSum() {
        return businessIncomeSum;
    }

    public void setBusinessIncomeSum(Double businessIncomeSum) {
        this.businessIncomeSum = businessIncomeSum;
    }

    public Double getBusinessIncomeTen() {
        return businessIncomeTen;
    }

    public void setBusinessIncomeTen(Double businessIncomeTen) {
        this.businessIncomeTen = businessIncomeTen;
    }

    public Double getBusinessIncomeFift() {
        return businessIncomeFift;
    }

    public void setBusinessIncomeFift(Double businessIncomeFift) {
        this.businessIncomeFift = businessIncomeFift;
    }

    public Double getBusinessIncomeTwtenty() {
        return businessIncomeTwtenty;
    }

    public void setBusinessIncomeTwtenty(Double businessIncomeTwtenty) {
        this.businessIncomeTwtenty = businessIncomeTwtenty;
    }

    public Double getBusinessIncomeThirty() {
        return businessIncomeThirty;
    }

    public void setBusinessIncomeThirty(Double businessIncomeThirty) {
        this.businessIncomeThirty = businessIncomeThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}