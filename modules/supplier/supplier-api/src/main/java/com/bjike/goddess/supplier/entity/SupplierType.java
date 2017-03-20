package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 供应商类型管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:54.977 ]
 * @Description: [ 供应商类型管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_supplier_type")
public class SupplierType extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '名称'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '状态' ", nullable = false, insertable = false)
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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