package com.bjike.goddess.financeinit.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.financeinit.enums.ScaleShape;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 往来单位导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WithUnitExport extends BaseBO {

    /**
     * 公司名称
     */
    @ExcelHeader(name="公司名称",notNull = true)
    private String companyName;

    /**
     * 税号
     */
    @ExcelHeader(name="税号",notNull = true)
    private String ein;

    /**
     * 电话
     */
    @ExcelHeader(name="电话",notNull = true)
    private String phone;

    /**
     * 地址
     */
    @ExcelHeader(name="地址",notNull = true)
    private String address;

    /**
     * 开户银行
     */
    @ExcelHeader(name="开户银行",notNull = true)
    private String bank;

    /**
     * 银行账号
     */
    @ExcelHeader(name="银行账号",notNull = true)
    private String account;

    /**
     * 公司规模形式
     */
    @ExcelHeader(name="公司规模形式",notNull = true)
    private ScaleShape scaleShape;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ScaleShape getScaleShape() {
        return scaleShape;
    }

    public void setScaleShape(ScaleShape scaleShape) {
        this.scaleShape = scaleShape;
    }
}