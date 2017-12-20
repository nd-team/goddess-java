package com.bjike.goddess.businessprojectmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.swing.*;

/**
 * 客户权限设置数据传输对象
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusPermissionDTO extends BaseDTO {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}