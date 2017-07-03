package com.bjike.goddess.checkfunds.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 余额调节业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RemainAdjustBO extends BaseBO {

    /**
     * 资金流水项目
     */
    private String moneyWaterProject;

    /**
     * 资金流水金额
     */
    private Double moneyWaterSum;
    /**
     * 银行流水项目
     */
    private String bankWaterProject;

    /**
     * 银行流水金额
     */
    private Double bankWaterSum;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 类型
     */
    private String type;

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 银行类型
     */
    private String bankType;

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoneyWaterProject() {
        return moneyWaterProject;
    }

    public void setMoneyWaterProject(String moneyWaterProject) {
        this.moneyWaterProject = moneyWaterProject;
    }

    public Double getMoneyWaterSum() {
        return moneyWaterSum;
    }

    public void setMoneyWaterSum(Double moneyWaterSum) {
        this.moneyWaterSum = moneyWaterSum;
    }

    public String getBankWaterProject() {
        return bankWaterProject;
    }

    public void setBankWaterProject(String bankWaterProject) {
        this.bankWaterProject = bankWaterProject;
    }

    public Double getBankWaterSum() {
        return bankWaterSum;
    }

    public void setBankWaterSum(Double bankWaterSum) {
        this.bankWaterSum = bankWaterSum;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}