package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 高温补助数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TemperatureSubsidiesDTO extends BaseDTO {
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