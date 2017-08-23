package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 补助标准数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssistanceStandardDTO extends BaseDTO {

    /**
     * 补助类型名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}