package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 问题权限配置操作对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 09:02 ]
 * @Description: [ 问题权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_propermissionOperate")
public class ProPermissionOperate extends BaseEntity {
    /**
     * 操作对象
     */
    @Column(name = "operator",  columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 问题权限id,一对多关系
     */
    @Column(name = "propermissionId",  columnDefinition = "VARCHAR(255)   COMMENT '问题权限id'")
    private String propermissionId;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPropermissionId() {
        return propermissionId;
    }

    public void setPropermissionId(String propermissionId) {
        this.propermissionId = propermissionId;
    }
}
