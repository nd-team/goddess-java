package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 周计划的合计
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:42 ]
 * @Description: [ 周计划的合计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_weektotal")
public class WeekTotal extends BaseEntity {

    /**
     * 周计划的周期id
     */
    @Column(name = "weekCycleId", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '周计划的周期id'")
    private String weekCycleId;

    /**
     * 合计
     */
    @Column(name = "total", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '合计'")
    private String total;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getWeekCycleId() {
        return weekCycleId;
    }

    public void setWeekCycleId(String weekCycleId) {
        this.weekCycleId = weekCycleId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}