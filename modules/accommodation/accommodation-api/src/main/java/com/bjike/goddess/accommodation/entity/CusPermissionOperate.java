package com.bjike.goddess.accommodation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 权限配置操作对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "accommodation_cuspermissionoperate")
public class CusPermissionOperate extends BaseEntity {


    /**
     * 操作对象
     */
    @Column(name = "operator", columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 权限id,一对多关系
     */
    @Column(name = "cuspermissionId", columnDefinition = "VARCHAR(255)   COMMENT '客户权限id'")
    private String cuspermissionId;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCuspermissionId() {
        return cuspermissionId;
    }

    public void setCuspermissionId(String cuspermissionId) {
        this.cuspermissionId = cuspermissionId;
    }
}