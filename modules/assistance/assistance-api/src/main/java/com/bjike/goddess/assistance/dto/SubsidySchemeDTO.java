package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 公司补助方案数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubsidySchemeDTO extends BaseDTO {
    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 补助名称
     */
    private String subsidyName;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSubsidyName() {
        return subsidyName;
    }

    public void setSubsidyName(String subsidyName) {
        this.subsidyName = subsidyName;
    }
}