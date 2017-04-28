package com.bjike.goddess.accruedtax.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 应交税金数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PayTaxDTO extends BaseDTO {

    /**
     * 公司名
     */
    private String company;
    /**
     * 税种
     */
    private String taxType;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}