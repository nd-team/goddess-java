package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.MaterialState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 入库地区日汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:13 ]
 * @Description: [ 入库地区日汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_instockareadaysum")
public class InStockAreaDaySum extends BaseEntity {

    /**
     * 汇总日期
     */
    @Column(name = "sumDate", nullable = false, columnDefinition = "DATE COMMENT '汇总日期'")
    private LocalDate sumDate;

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
     * 入库物资状态
     */
    @Column(name = "instockState", nullable = false, columnDefinition = "TINYINT(2) COMMENT '入库物资状态'")
    private MaterialState instockState;

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


    public LocalDate getSumDate() {
        return sumDate;
    }

    public void setSumDate(LocalDate sumDate) {
        this.sumDate = sumDate;
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

    public MaterialState getInstockState() {
        return instockState;
    }

    public void setInstockState(MaterialState instockState) {
        this.instockState = instockState;
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