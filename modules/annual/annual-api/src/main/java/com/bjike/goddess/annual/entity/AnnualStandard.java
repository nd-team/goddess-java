package com.bjike.goddess.annual.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 年假标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "annual_annual_standard")
public class AnnualStandard extends BaseEntity {

    /**
     * 开始工龄范围(年)
     */
    @Column(name = "startCycle", nullable = false, columnDefinition = "INT(11)   COMMENT '开始工龄范围(年)'")
    private Integer startCycle;

    /**
     * 结束工龄范围(年)
     */
    @Column(name = "endCycle", nullable = false, columnDefinition = "INT(11)   COMMENT '结束工龄范围(年)'")
    private Integer endCycle;

    /**
     * 病假限制(月)
     */
    @Column(name = "astrict", nullable = false, columnDefinition = "INT(11)   COMMENT '病假限制(月)'")
    private Integer astrict;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getStartCycle() {
        return startCycle;
    }

    public void setStartCycle(Integer startCycle) {
        this.startCycle = startCycle;
    }

    public Integer getEndCycle() {
        return endCycle;
    }

    public void setEndCycle(Integer endCycle) {
        this.endCycle = endCycle;
    }

    public Integer getAstrict() {
        return astrict;
    }

    public void setAstrict(Integer astrict) {
        this.astrict = astrict;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}