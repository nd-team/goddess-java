package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 股东变更
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_sharechange")
public class ShareChange extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 变更前股东姓名
     */
    @Column(name = "changeBeforeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '变更前股东姓名'")
    private String changeBeforeName;

    /**
     * 变更后股东姓名
     */
    @Column(name = "changeAfterName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '变更后股东姓名'")
    private String changeAfterName;

    /**
     * 变更时间
     */
    @Column(name = "changeDate", nullable = false, columnDefinition = "DATE   COMMENT '变更时间'")
    private LocalDate changeDate;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getChangeBeforeName() {
        return changeBeforeName;
    }

    public void setChangeBeforeName(String changeBeforeName) {
        this.changeBeforeName = changeBeforeName;
    }

    public String getChangeAfterName() {
        return changeAfterName;
    }

    public void setChangeAfterName(String changeAfterName) {
        this.changeAfterName = changeAfterName;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}