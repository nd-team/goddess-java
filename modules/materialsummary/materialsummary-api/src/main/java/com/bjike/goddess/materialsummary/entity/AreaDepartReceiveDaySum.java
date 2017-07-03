package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 地区部门领用日汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:29 ]
 * @Description: [ 地区部门领用日汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_areadepartreceivedaysum")
public class AreaDepartReceiveDaySum extends BaseEntity {

    /**
     * 汇总日期
     */
    @Column(name = "sumDate", nullable = false, columnDefinition = "DATE COMMENT '汇总日期'")
    private LocalDate sumDate;

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
     * 物资用途
     */
    @Column(name = "materialUse", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资用途'")
    private String materialUse;

    /**
     * 总数量
     */
    @Column(name = "totalQty", nullable = false, columnDefinition = "INT(11) COMMENT '总数量'")
    private Integer totalQty;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT '2' COMMENT '状态'")
    private Status status;


    public LocalDate getSumDate() {
        return sumDate;
    }

    public void setSumDate(LocalDate sumDate) {
        this.sumDate = sumDate;
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

    public String getMaterialUse() {
        return materialUse;
    }

    public void setMaterialUse(String materialUse) {
        this.materialUse = materialUse;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}