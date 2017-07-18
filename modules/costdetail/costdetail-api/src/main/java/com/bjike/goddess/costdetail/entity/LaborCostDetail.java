package com.bjike.goddess.costdetail.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 劳务成本明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:52 ]
 * @Description: [ 劳务成本明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "costdetail_laborcostdetail")
public class LaborCostDetail extends BaseEntity {

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
     * 劳务成本合计
     */
    @Column(name = "laborCostSum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '劳务成本合计'")
    private Double laborCostSum;

    /**
     * 劳务成本十日
     */
    @Column(name = "laborCostTen", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '劳务成本十日'")
    private Double laborCostTen;

    /**
     * 劳务成本十五日
     */
    @Column(name = "laborCostFift", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '劳务成本十五日'")
    private Double laborCostFift;

    /**
     * 劳务成本二十日
     */
    @Column(name = "laborCostTwtenty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '劳务成本二十日'")
    private Double laborCostTwtenty;

    /**
     * 劳务成本三十日
     */
    @Column(name = "laborCostThirty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '劳务成本三十日'")
    private Double laborCostThirty;

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

    public Double getLaborCostSum() {
        return laborCostSum;
    }

    public void setLaborCostSum(Double laborCostSum) {
        this.laborCostSum = laborCostSum;
    }

    public Double getLaborCostTen() {
        return laborCostTen;
    }

    public void setLaborCostTen(Double laborCostTen) {
        this.laborCostTen = laborCostTen;
    }

    public Double getLaborCostFift() {
        return laborCostFift;
    }

    public void setLaborCostFift(Double laborCostFift) {
        this.laborCostFift = laborCostFift;
    }

    public Double getLaborCostTwtenty() {
        return laborCostTwtenty;
    }

    public void setLaborCostTwtenty(Double laborCostTwtenty) {
        this.laborCostTwtenty = laborCostTwtenty;
    }

    public Double getLaborCostThirty() {
        return laborCostThirty;
    }

    public void setLaborCostThirty(Double laborCostThirty) {
        this.laborCostThirty = laborCostThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}