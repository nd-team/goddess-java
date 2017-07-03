package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.InstockType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 入库来源月汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:06 ]
 * @Description: [ 入库来源月汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_instocksorucemonthsum")
public class InStockSoruceMonthSum extends BaseEntity {

    /**
     * 汇总起始时间
     */
    @Column(name = "sumStartTime", nullable = false, columnDefinition = "DATETIME COMMENT '汇总起始时间'")
    private LocalDateTime sumStartTime;

    /**
     * 汇总结束时间
     */
    @Column(name = "sumEndTime", nullable = false, columnDefinition = "DATETIME COMMENT '汇总结束时间'")
    private LocalDateTime sumEndTime;

    /**
     * 入库来源
     */
    @Column(name = "instockType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '入库来源'")
    private InstockType instockType;

    /**
     * 现入库地区
     */
    @Column(name = "curInstockArea", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '现入库地区'")
    private String curInstockArea;

    /**
     * 部门(项目组)
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '部门(项目组)'")
    private String projectGroup;

    /**
     * 总数量
     */
    @Column(name = "totalQty", nullable = false, columnDefinition = "INT(11) COMMENT '总数量'")
    private Integer totalQty;

    /**
     * 总金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '总金额'")
    private Double amount;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) COMMENT '状态'")
    private Status status;


    public LocalDateTime getSumStartTime() {
        return sumStartTime;
    }

    public void setSumStartTime(LocalDateTime sumStartTime) {
        this.sumStartTime = sumStartTime;
    }

    public LocalDateTime getSumEndTime() {
        return sumEndTime;
    }

    public void setSumEndTime(LocalDateTime sumEndTime) {
        this.sumEndTime = sumEndTime;
    }

    public InstockType getInstockType() {
        return instockType;
    }

    public void setInstockType(InstockType instockType) {
        this.instockType = instockType;
    }

    public String getCurInstockArea() {
        return curInstockArea;
    }

    public void setCurInstockArea(String curInstockArea) {
        this.curInstockArea = curInstockArea;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}