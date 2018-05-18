package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 电脑补助数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComputerSubsidiesDTO extends BaseDTO {
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