package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 周期
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 03:05 ]
 * @Description: [ 周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_cycle")
public class Cycle extends BaseEntity {

    /**
     * 业务方向科目id
     */
    @Column(name = "subjectDataId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目id'")
    private String subjectDataId;

    /**
     * 周期
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '周期'")
    private String cycle;


    public String getSubjectDataId() {
        return subjectDataId;
    }

    public void setSubjectDataId(String subjectDataId) {
        this.subjectDataId = subjectDataId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}