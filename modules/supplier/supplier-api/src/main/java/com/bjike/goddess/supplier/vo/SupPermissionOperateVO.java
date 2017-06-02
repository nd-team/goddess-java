package com.bjike.goddess.supplier.vo;

/**
 * 供应商权限配置操作对象表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 11:22 ]
 * @Description: [ 供应商权限配置操作对象表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupPermissionOperateVO {

    /**
     * id
     */
    private String id;
    /**
     * 操作对象
     */
    private String operator;

    /**
     * 供应商权限id
     */
    private String supPermissionId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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