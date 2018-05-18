package com.bjike.goddess.fundrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资金流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundrecords_fundrecord")
public class FundRecord extends BaseEntity {

    /**
     * 流水日期
     */
    @Column(name = "recordDate",  columnDefinition = "DATE   COMMENT '流水日期'")
    private LocalDate recordDate;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "project",  columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 摘要
     */
    @Column(name = "digest",  columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String digest;

    /**
     * 收入(借方)
     */
    @Column(name = "income",  columnDefinition = "DECIMAL(10,2)   COMMENT '收入'")
    private Double income;

    /**
     * 支出(贷方)
     */
    @Column(name = "expenditure",  columnDefinition = "DECIMAL(10,2)   COMMENT '支出'")
    private Double expenditure;

    /**
     * 余额
     */
    @Column(name = "amount",  columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 数据来源
     */
    @Column(name = "dataSource",  columnDefinition = "VARCHAR(255)   COMMENT '数据来源'")
    private String dataSource;

    /**
     * 记账凭证导入资金流水对应的数据id
     */
    @Column(name = "voucherGenerateId", columnDefinition = "VARCHAR(255)   COMMENT '记账凭证导入资金流水对应的数据id'")
    private String voucherGenerateId;



    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getVoucherGenerateId() {
        return voucherGenerateId;
    }

    public void setVoucherGenerateId(String voucherGenerateId) {
        this.voucherGenerateId = voucherGenerateId;
    }
}