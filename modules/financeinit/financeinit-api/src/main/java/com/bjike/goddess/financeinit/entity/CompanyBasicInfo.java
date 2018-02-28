package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.ScaleShape;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公司基本信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_companybasicinfo")
public class CompanyBasicInfo extends BaseEntity {

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 税号
     */
    @Column(name = "ein",  columnDefinition = "VARCHAR(255)   COMMENT '税号'")
    private String ein;

    /**
     * 电话
     */
    @Column(name = "phone", columnDefinition = "VARCHAR(255)   COMMENT '电话'")
    private String phone;

    /**
     * 地址
     */
    @Column(name = "address",  columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 开户银行(基本户)
     */
    @Column(name = "bank",  columnDefinition = "VARCHAR(255)   COMMENT '开户银行(基本户)'")
    private String bank;

    /**
     * 银行账号
     */
    @Column(name = "account",  columnDefinition = "VARCHAR(255)   COMMENT '银行账号'")
    private String account;

    /**
     * 公司规模形式
     */
    @Column(name = "scaleShape",  columnDefinition = "TINYINT(2)   COMMENT '公司规模形式'")
    private ScaleShape scaleShape;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}