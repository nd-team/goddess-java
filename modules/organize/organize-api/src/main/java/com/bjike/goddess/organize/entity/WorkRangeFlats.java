package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工作范围信息设置平台
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-06 10:58 ]
 * @Description: [ 工作范围信息设置平台 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_workrangeflats")
public class WorkRangeFlats extends BaseEntity {

    /**
     * faltId
     */
    @Column(name = "faltId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'faltId'")
    private String faltId;

    public String getFaltId() {
        return faltId;
    }

    public void setFaltId(String faltId) {
        this.faltId = faltId;
    }
}