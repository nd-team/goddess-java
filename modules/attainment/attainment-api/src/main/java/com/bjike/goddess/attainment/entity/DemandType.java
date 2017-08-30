package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 调研需求类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:46 ]
 * @Description: [ 调研需求类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_demand_type")
public class DemandType extends BaseEntity {

    /**
     * 类型
     */
    @Column(name = "type", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}