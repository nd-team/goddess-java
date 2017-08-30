package com.bjike.goddess.employeecontract.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 合同性质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "employeecontract_nature")
public class ContractNature extends BaseEntity {

    /**
     * 性质
     */
    @Column(name = "nature", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '性质'")
    private String nature;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0    COMMENT '状态'")
    private Status status;


    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
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