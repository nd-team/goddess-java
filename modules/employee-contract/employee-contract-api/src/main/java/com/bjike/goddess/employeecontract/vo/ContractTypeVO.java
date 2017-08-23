package com.bjike.goddess.employeecontract.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 合同类型表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:55 ]
 * @Description: [ 合同类型表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractTypeVO {

    /**
     * id
     */
    private String id;
    /**
     * 类型
     */
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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