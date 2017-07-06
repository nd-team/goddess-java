package com.bjike.goddess.fundcheck.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 回款业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BackBO extends BaseBO {

    /**
     * 日期
     */
    private String date;

    /**
     * 地区
     */
    private String area;

    /**
     * 内部项目名称
     */
    private String innerName;

    /**
     * 到账金额
     */
    private Double accountMoney;

    /**
     * 税金
     */
    private Double taxes;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }
}