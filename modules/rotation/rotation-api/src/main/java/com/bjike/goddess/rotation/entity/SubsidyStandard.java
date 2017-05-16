package com.bjike.goddess.rotation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 岗位补贴标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rotation_subsidystandard")
public class SubsidyStandard extends BaseEntity {

    /**
     * 岗位层级
     */
    @Column(name = "arrangement", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private String arrangement;

    /**
     * 补贴标准
     */
    @Column(name = "standard", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '补贴标准'")
    private Double standard;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}