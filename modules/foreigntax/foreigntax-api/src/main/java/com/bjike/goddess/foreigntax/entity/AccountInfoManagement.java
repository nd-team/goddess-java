package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 外账资料管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_accountinfomanagement")
public class AccountInfoManagement extends BaseEntity {

    /**
     * 公司
     */
    @Column(name = "company",  columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;
    /**
     * 时间
     */
    @Column(name = "time", columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;
    /**
     * 资料名称
     */
    @Column(name = "dataName", columnDefinition = "VARCHAR(255)   COMMENT '资料名称'")
    private String dataName;
    /**
     * 办税员
     */
    @Column(name = "taxAgent", columnDefinition = "VARCHAR(255)   COMMENT '办税员'")
    private String taxAgent;

    /**
     * 跟进人
     */
    @Column(name = "followUpPeople", columnDefinition = "VARCHAR(255)   COMMENT '跟进人'")
    private String followUpPeople;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getTaxAgent() {
        return taxAgent;
    }

    public void setTaxAgent(String taxAgent) {
        this.taxAgent = taxAgent;
    }

    public String getFollowUpPeople() {
        return followUpPeople;
    }

    public void setFollowUpPeople(String followUpPeople) {
        this.followUpPeople = followUpPeople;
    }
}