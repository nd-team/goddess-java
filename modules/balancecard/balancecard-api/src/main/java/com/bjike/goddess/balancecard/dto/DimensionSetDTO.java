package com.bjike.goddess.balancecard.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 维度设置数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 维度设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DimensionSetDTO extends BaseDTO {

    /**
     * 维度名称
     */
    private String typeName;

    /**
     * 维度添加人
     */
    private String person;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}