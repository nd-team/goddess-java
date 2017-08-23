package com.bjike.goddess.balancecard.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 指标名称设置数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:02 ]
 * @Description: [ 指标名称设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndexNameSetDTO extends BaseDTO {

    /**
     * 指标名称
     */
    private String typeName;

    /**
     * 指标名称添加人
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