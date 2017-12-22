package com.bjike.goddess.businessprojectmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 客户权限配置操作对象
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:53 ]
 * @Description: [ 客户权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessprojectmanage_cuspermissionoperate")
public class CusPermissionOperate extends BaseEntity {

    /**
     * 操作对象
     */
    @Column(name = "operator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 客户权限id,一对多关系
     */
    @Column(name = "cuspermissionId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户权限id,一对多关系'")
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