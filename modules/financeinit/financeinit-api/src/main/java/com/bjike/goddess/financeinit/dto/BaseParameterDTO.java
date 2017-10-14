package com.bjike.goddess.financeinit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 基本参数数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseParameterDTO extends BaseDTO {
    /**
     * 公司名称
     */
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}