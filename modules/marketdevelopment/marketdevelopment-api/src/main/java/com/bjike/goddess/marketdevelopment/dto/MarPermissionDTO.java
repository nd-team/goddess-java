package com.bjike.goddess.marketdevelopment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 市场计划进度管理权限配置数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarPermissionDTO extends BaseDTO {

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