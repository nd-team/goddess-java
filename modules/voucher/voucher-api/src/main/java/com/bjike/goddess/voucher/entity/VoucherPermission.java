package com.bjike.goddess.voucher.entity;

import com.bjike.goddess.voucher.enums.VoucherPermissionType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
@Table(name = "voucher_cuspermission")
public class VoucherPermission extends BaseEntity {

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
    @Column(name = "operator",  columnDefinition = "TEXT   COMMENT '操作对象'")
    private String operator;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "INT(2)   COMMENT '类型'")
    private VoucherPermissionType type;

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

    public VoucherPermissionType getType() {
        return type;
    }

    public void setType(VoucherPermissionType type) {
        this.type = type;
    }
}