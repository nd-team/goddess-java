package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 供应商权限配置操作对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 11:22 ]
 * @Description: [ 供应商权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_suppermissionoperate")
public class SupPermissionOperate extends BaseEntity {

    /**
     * 操作对象
     */
    @Column(name = "operator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 供应商权限id
     */
    @Column(name = "supPermissionId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商权限id'")
    private String supPermissionId;


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSupPermissionId() {
        return supPermissionId;
    }

    public void setSupPermissionId(String supPermissionId) {
        this.supPermissionId = supPermissionId;
    }
}