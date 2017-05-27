package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectissuehandle.enums.ProPermissionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 问题权限设置
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 09:02 ]
 * @Description: [ 问题权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_propermission")
public class ProPermission extends BaseEntity {
    /**
     * 辅助id
     */
    @Column(name = "idFlag", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '辅助id'")
    private String idFlag;
    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 操作对象
     */
    @Column(name = "operator", columnDefinition = "TEXT   COMMENT '操作对象'")
    private String operator;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "INT(2)   COMMENT '类型'")
    private ProPermissionType type;

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public ProPermissionType getType() {
        return type;
    }

    public void setType(ProPermissionType type) {
        this.type = type;
    }
}
