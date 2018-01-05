package com.bjike.goddess.workjoin.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工作交接责任义务
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workjoin_workjoinduty")
public class WorkJoinDuty extends BaseEntity {

    /**
     * 对象
     */
    @Column(name = "object", columnDefinition = "VARCHAR(255)   COMMENT '对象'")
    private String object;

    /**
     * 责任与义务
     */
    @Column(name = "duty", columnDefinition = "TEXT   COMMENT '责任与义务'")
    private String duty;


    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}