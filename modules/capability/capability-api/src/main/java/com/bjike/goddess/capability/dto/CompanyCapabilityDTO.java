package com.bjike.goddess.capability.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 公司能力展示数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyCapabilityDTO extends BaseDTO {

    /**
     * 公司名称
     */
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}