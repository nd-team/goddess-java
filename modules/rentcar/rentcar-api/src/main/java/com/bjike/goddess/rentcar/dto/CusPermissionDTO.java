package com.bjike.goddess.rentcar.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 客户权限配置数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusPermissionDTO extends BaseDTO {

    /**
     * 描述
     */
    private String description;

    /**
     * 操作对象
     */
    private String operator;

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
}