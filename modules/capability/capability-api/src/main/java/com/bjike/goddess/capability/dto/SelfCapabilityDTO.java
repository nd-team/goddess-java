package com.bjike.goddess.capability.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 个人能力展示数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SelfCapabilityDTO extends BaseDTO {

    /**
     * 姓名
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}