package com.bjike.goddess.checkfunds.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 余额调节
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkfunds_remainadjust")
public class RemainAdjust extends BaseEntity {

    /**
     * 资金流水项目
     */
    @Column(name = "moneyWaterProject", columnDefinition = "VARCHAR(255)   COMMENT '资金流水项目'")
    private String moneyWaterProject;

    /**
     * 资金流水金额
     */
    @Column(name = "moneyWaterSum", columnDefinition = "DECIMAL(10,2)   COMMENT '资金流水金额'")
    private Double moneyWaterSum;

    /**
     * 银行流水项目
     */
    @Column(name = "bankWaterProject", columnDefinition = "VARCHAR(255)   COMMENT '银行流水项目'")
    private String bankWaterProject;

    /**
     * 银行流水金额
     */
    @Column(name = "bankWaterSum", columnDefinition = "DECIMAL(10,2)   COMMENT '银行流水金额'")
    private Double bankWaterSum;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 资金类型
     */
    @Column(name = "fundType", columnDefinition = "VARCHAR(255)   COMMENT '资金类型'")
    private String fundType;

    /**
     * 银行类型
     */
    @Column(name = "bankType", columnDefinition = "VARCHAR(255)   COMMENT '银行类型'")
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