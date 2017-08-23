package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 供应商类型管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:54.981 ]
 * @Description: [ 供应商类型管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierTypeTO extends BaseTO {

    /**
     * 名称
     */
    @NotNull(message = "供应商类型名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;


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