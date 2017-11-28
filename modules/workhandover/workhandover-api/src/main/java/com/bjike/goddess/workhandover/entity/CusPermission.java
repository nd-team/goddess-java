package com.bjike.goddess.workhandover.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.workhandover.enums.CusPermissionType;

import javax.persistence.*;


/**
 * 权限配置
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-14 09:33 ]
 * @Description: [ 权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workhandover_cuspermission")
public class CusPermission extends BaseEntity {

    /**
     * 辅助id
     */
    @Column(name = "idFlag", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '辅助id'")
    private String idFlag;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 操作对象
     */
    @Column(name = "operator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 类型
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private CusPermissionType type;


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

    public CusPermissionType getType() {
        return type;
    }

    public void setType(CusPermissionType type) {
        this.type = type;
    }
}