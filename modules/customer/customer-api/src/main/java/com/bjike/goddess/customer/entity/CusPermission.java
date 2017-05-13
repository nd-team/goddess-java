package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 客户权限配置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_cuspermission")
public class CusPermission extends BaseEntity {

    /**
     * 辅助id
     */
    @Column(name = "idFlag", nullable = false, unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '辅助id'")
    private String idFlag;
    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 操作对象
     */
    @Column(name = "operator",  columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}