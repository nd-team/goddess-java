package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 设置币别
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_currency")
public class Currency extends BaseEntity {

    /**
     * 代码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '代码'")
    private String code;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名称'")
    private String name;

    /**
     * 记账汇率
     */
    @Column(name = "rate", columnDefinition = "DECIMAL(10,2)   COMMENT '记账汇率'")
    private Double rate;

    /**
     * 是否本位币
     */
    @Column(name = "is_standardMoney", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否本位币'", insertable = false)
    private Boolean standardMoney;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getStandardMoney() {
        return standardMoney;
    }

    public void setStandardMoney(Boolean standardMoney) {
        this.standardMoney = standardMoney;
    }
}