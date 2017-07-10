package com.bjike.goddess.fundcheck.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * Created by ike on 17-7-6.
 */
public class BackTemplateExcel {
    /**
     * 日期
     */
    @ExcelHeader(name = "日期",notNull = true)
    private LocalDate date;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称",notNull = true)
    private String innerName;

    /**
     * 到账金额
     */
    @ExcelHeader(name = "到账金额",notNull = true)
    private Double accountMoney;

    /**
     * 税金
     */
    @ExcelHeader(name = "税金",notNull = true)
    private Double taxes;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
