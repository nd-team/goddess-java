package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 市场计划进度管理权限配置操作对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:36 ]
 * @Description: [ 市场计划进度管理权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_marpermission_operate")
public class MarPermissionOperate extends BaseEntity {

    /**
     * 操作对象
     */
    @Column(name = "operator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 市场计划进度管理权限id
     */
    @Column(name = "marPermissionId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场计划进度管理权限id'")
    private String marPermissionId;


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMarPermissionId() {
        return marPermissionId;
    }

    public void setMarPermissionId(String marPermissionId) {
        this.marPermissionId = marPermissionId;
    }
}