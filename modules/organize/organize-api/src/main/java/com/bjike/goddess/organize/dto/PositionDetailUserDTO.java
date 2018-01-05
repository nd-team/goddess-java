package com.bjike.goddess.organize.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 用户职位数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserDTO extends BaseDTO {
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