package com.bjike.goddess.costdetail.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 实收资本明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 07:06 ]
 * @Description: [ 实收资本明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "costdetail_paidcapitaldetail")
public class PaidCapitalDetail extends BaseEntity {

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
     * 实收资本合计
     */
    @Column(name = "paidCapitalSum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实收资本合计'")
    private Double paidCapitalSum;

    /**
     * 实收资本十日
     */
    @Column(name = "paidCapitalTen", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实收资本十日'")
    private Double paidCapitalTen;

    /**
     * 实收资本十五日
     */
    @Column(name = "paidCapitalFift", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实收资本十五日'")
    private Double paidCapitalFift;

    /**
     * 实收资本二十日
     */
    @Column(name = "paidCapitalTwtenty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实收资本二十日'")
    private Double paidCapitalTwtenty;

    /**
     * 实收资本三十日
     */
    @Column(name = "paidCapitalThirty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实收资本三十日'")
    private Double paidCapitalThirty;

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

    public Double getPaidCapitalSum() {
        return paidCapitalSum;
    }

    public void setPaidCapitalSum(Double paidCapitalSum) {
        this.paidCapitalSum = paidCapitalSum;
    }

    public Double getPaidCapitalTen() {
        return paidCapitalTen;
    }

    public void setPaidCapitalTen(Double paidCapitalTen) {
        this.paidCapitalTen = paidCapitalTen;
    }

    public Double getPaidCapitalFift() {
        return paidCapitalFift;
    }

    public void setPaidCapitalFift(Double paidCapitalFift) {
        this.paidCapitalFift = paidCapitalFift;
    }

    public Double getPaidCapitalTwtenty() {
        return paidCapitalTwtenty;
    }

    public void setPaidCapitalTwtenty(Double paidCapitalTwtenty) {
        this.paidCapitalTwtenty = paidCapitalTwtenty;
    }

    public Double getPaidCapitalThirty() {
        return paidCapitalThirty;
    }

    public void setPaidCapitalThirty(Double paidCapitalThirty) {
        this.paidCapitalThirty = paidCapitalThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}