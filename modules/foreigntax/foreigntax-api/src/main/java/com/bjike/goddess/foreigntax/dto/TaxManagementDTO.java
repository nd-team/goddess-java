package com.bjike.goddess.foreigntax.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 税金管理数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaxManagementDTO extends BaseDTO {
    /**
     * 公司
     */
    private String company;

    /**
     * 税种
     */
    private String taxType;
    /**
     * 税款所属期起
     */
    private String taxStart;
    /**
     * 税款所属期止
     */
    private String taxEnd;


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

    public String getTaxStart() {
        return taxStart;
    }

    public void setTaxStart(String taxStart) {
        this.taxStart = taxStart;
    }

    public String getTaxEnd() {
        return taxEnd;
    }

    public void setTaxEnd(String taxEnd) {
        this.taxEnd = taxEnd;
    }
}