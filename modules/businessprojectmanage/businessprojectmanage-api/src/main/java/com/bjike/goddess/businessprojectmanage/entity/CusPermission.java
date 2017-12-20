package com.bjike.goddess.businessprojectmanage.entity;

import com.bjike.goddess.businessprojectmanage.enums.CusPermissionType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 客户权限设置
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessprojectmanage_cuspermission")
public class CusPermission extends BaseEntity {

    /**
     * 辅助id
     */
    @Column(name = "idFlag",  columnDefinition = "VARCHAR(255)   COMMENT '辅助id'")
    private String idFlag;

    /**
     * 描述
     */
    @Column(name = "description",  columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 操作对象
     */
    @Column(name = "operator",  columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String operator;

    /**
     * 类型
     */
    @Column(name = "",  columnDefinition = "VARCHAR(255)   COMMENT '类型'")
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