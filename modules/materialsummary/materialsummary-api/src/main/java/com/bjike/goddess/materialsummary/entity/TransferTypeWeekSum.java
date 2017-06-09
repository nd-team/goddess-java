package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.MaterialState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 调动类型周汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型周汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_transfertypeweeksum")
public class TransferTypeWeekSum extends BaseEntity {

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
     * 物资类型
     */
    @Column(name = "materialState", nullable = false, columnDefinition = "TINYINT(2) COMMENT '物资类型'")
    private MaterialState materialState;

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
     * 原存储地区总数量
     */
    @Column(name = "oriStorageAreaTotalQty", nullable = false, columnDefinition = "INT(11) COMMENT '原存储地区总数量'")
    private Integer oriStorageAreaTotalQty;

    /**
     * 调入地区总数量
     */
    @Column(name = "transferredAreaTotalQty", nullable = false, columnDefinition = "INT(11) COMMENT '调入地区总数量'")
    private Integer transferredAreaTotalQty;

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

    public MaterialState getMaterialState() {
        return materialState;
    }

    public void setMaterialState(MaterialState materialState) {
        this.materialState = materialState;
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

    public Integer getOriStorageAreaTotalQty() {
        return oriStorageAreaTotalQty;
    }

    public void setOriStorageAreaTotalQty(Integer oriStorageAreaTotalQty) {
        this.oriStorageAreaTotalQty = oriStorageAreaTotalQty;
    }

    public Integer getTransferredAreaTotalQty() {
        return transferredAreaTotalQty;
    }

    public void setTransferredAreaTotalQty(Integer transferredAreaTotalQty) {
        this.transferredAreaTotalQty = transferredAreaTotalQty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}