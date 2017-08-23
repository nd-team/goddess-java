package com.bjike.goddess.capability.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 合作对象商务展示数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:31 ]
 * @Description: [ 合作对象商务展示数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CooperCapabilityDTO extends BaseDTO {

    /**
     * 公司名
     */
    private String companyName ;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}