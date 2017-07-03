package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 项目提成目标定额
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_targetauota")
public class TargetAuota extends BaseEntity {

    /**
     * 项目提成权重分配
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "allocation_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目提成权重分配'")
    private WeightAllocation allocation;

    /**
     * 影响因素(总数)
     */
    @Column(name = "factor", nullable = false, columnDefinition = "INT(11)   COMMENT '影响因素(总数)'")
    private Integer factor;

    /**
     * 业务提成额
     */
    @Column(name = "business", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务提成额'")
    private Double business;

    /**
     * 管理提成额
     */
    @Column(name = "manage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理提成额'")
    private Double manage;

    /**
     * 资金方额
     */
    @Column(name = "capital", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方额'")
    private Double capital;

    /**
     * 员工持股额
     */
    @Column(name = "staff", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '员工持股额'")
    private Double staff;

    /**
     * 公司风控备额
     */
    @Column(name = "risk", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司风控备额'")
    private Double risk;

    /**
     * 利润额
     */
    @Column(name = "profit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '利润额'")
    private Double profit;

    /**
     * 备注
     */
    @Column(name = "remark",columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 目标或实际
     */
    @Column(name = "is_plan", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '目标或实际'")
    private Boolean plan;


    public WeightAllocation getAllocation() {
        return allocation;
    }

    public void setAllocation(WeightAllocation allocation) {
        this.allocation = allocation;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public Double getBusiness() {
        return business;
    }

    public void setBusiness(Double business) {
        this.business = business;
    }

    public Double getManage() {
        return manage;
    }

    public void setManage(Double manage) {
        this.manage = manage;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getStaff() {
        return staff;
    }

    public void setStaff(Double staff) {
        this.staff = staff;
    }

    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPlan() {
        return plan;
    }

    public void setPlan(Boolean plan) {
        this.plan = plan;
    }
}