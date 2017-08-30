package com.bjike.goddess.supplier.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 供应商类型管理表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:54.983 ]
 * @Description: [ 供应商类型管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierTypeVO {

    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}