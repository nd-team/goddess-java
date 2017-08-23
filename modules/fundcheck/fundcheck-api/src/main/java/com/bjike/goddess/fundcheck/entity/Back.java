package com.bjike.goddess.fundcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 回款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundcheck_back")
public class Back extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 内部项目名称
     */
    @Column(name = "innerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerName;

    /**
     * 到账金额
     */
    @Column(name = "accountMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '到账金额'")
    private Double accountMoney;

    /**
     * 税金
     */
    @Column(name = "taxes", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税金'")
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