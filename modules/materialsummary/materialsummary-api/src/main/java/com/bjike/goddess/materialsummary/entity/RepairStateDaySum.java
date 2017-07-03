package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.MaterialState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 维修状态日汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:52 ]
 * @Description: [ 维修状态日汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_repairstatedaysum")
public class RepairStateDaySum extends BaseEntity {

    /**
     * 汇总日期
     */
    @Column(name = "sumDate", nullable = false, columnDefinition = "DATE COMMENT '汇总日期'")
    private LocalDate sumDate;

    /**
     * 维修状态
     */
    @Column(name = "repairState", nullable = false, columnDefinition = "TINYINT(2) COMMENT '维修状态'")
    private MaterialState repairState;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

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
    @Column(name = "amount", nullable = false, columnDefinition = "INT(11) COMMENT '总金额'")
    private Integer amount;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '0' COMMENT '状态'")
    private Status status;


    public LocalDate getSumDate() {
        return sumDate;
    }

    public void setSumDate(LocalDate sumDate) {
        this.sumDate = sumDate;
    }

    public MaterialState getRepairState() {
        return repairState;
    }

    public void setRepairState(MaterialState repairState) {
        this.repairState = repairState;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}