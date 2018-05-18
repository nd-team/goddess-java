package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 变更股权类型
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_changeequitytype")
public class ChangeEquityType extends BaseEntity {

    /**
     * 变更前股权类型
     */
    @Column(name = "changeBeforeType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '变更前股权类型'")
    private String changeBeforeType;

    /**
     * 变更后股权类型
     */
    @Column(name = "changeAfterType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '变更后股权类型'")
    private String changeAfterType;

    /**
     * 变更时间
     */
    @Column(name = "changeDate", nullable = false, columnDefinition = "DATE   COMMENT '变更时间'")
    private LocalDate changeDate;

    /**
     * 备注
     */
    @Column(name = "remark",columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getChangeBeforeType() {
        return changeBeforeType;
    }

    public void setChangeBeforeType(String changeBeforeType) {
        this.changeBeforeType = changeBeforeType;
    }

    public String getChangeAfterType() {
        return changeAfterType;
    }

    public void setChangeAfterType(String changeAfterType) {
        this.changeAfterType = changeAfterType;
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