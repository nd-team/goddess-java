package com.bjike.goddess.intromanage.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户及合作伙伴导出
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:56 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerAndPartnerExport extends BaseBO {


    /**
     * 运营商
     */
    @ExcelHeader(name="运营商")
    private String operators;

    /**
     * 厂家
     */
    @ExcelHeader(name="厂家")
    private String manufacturer;

    /**
     * 各政府单位
     */
    @ExcelHeader(name="各政府单位")
    private String governmentUnit;

    /**
     * 合作伙伴
     */
    @ExcelHeader(name="合作伙伴")
    private String partner;


    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGovernmentUnit() {
        return governmentUnit;
    }

    public void setGovernmentUnit(String governmentUnit) {
        this.governmentUnit = governmentUnit;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
}