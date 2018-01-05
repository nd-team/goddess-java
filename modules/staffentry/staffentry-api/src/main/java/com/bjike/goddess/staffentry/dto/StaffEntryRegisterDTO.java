package com.bjike.goddess.staffentry.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 员工入职注册数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffEntryRegisterDTO extends BaseDTO {
    /**
     * 用户名
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}