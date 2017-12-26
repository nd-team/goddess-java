package com.bjike.goddess.fixedassets.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.fixedassets.enums.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 基本信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fixedassets_baseinfo")
public class BaseInfo extends BaseEntity {

    /**
     * 固定资产名称
     */
    @Column(name = "fixedAssetName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '固定资产名称'")
    private String fixedAssetName;

    /**
     * 固定资产规格
     */
    @Column(name = "fixedAssetSize", columnDefinition = "VARCHAR(255)   COMMENT '固定资产规格'")
    private String fixedAssetSize;

    /**
     * 固定资产类别
     */
    @Column(name = "fixedAssetType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '固定资产类别'")
    private FixedAssetType fixedAssetType;

    /**
     * 数量
     */
    @Column(name = "numbers", nullable = false, columnDefinition = "INT(11)   COMMENT '数量'")
    private Integer numbers;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '单位'")
    private Unit unit;

    /**
     * 使用部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '使用部门'")
    private String department;

    /**
     * 使用人
     */
    @Column(name = "userName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '使用人'")
    private String userName;

    /**
     * 入账日期
     */
    @Column(name = "bookDate", nullable = false, columnDefinition = "DATE   COMMENT '入账日期'")
    private LocalDate bookDate;

    /**
     * 固定资产会计科目
     */
    @Column(name = "fixedAssetAccountCourse", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '固定资产会计科目'")
    private String fixedAssetAccountCourse;

    /**
     * 累计折旧科目
     */
    @Column(name = "totalDepreciaSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '累计折旧科目'")
    private String totalDepreciaSubject;

    /**
     * 减值准备科目
     */
    @Column(name = "lossImpairmentSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '减值准备科目'")
    private String lossImpairmentSubject;

    /**
     * 折旧费用科目
     */
    @Column(name = "depreciaExpenceSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '折旧费用科目'")
    private String depreciaExpenceSubject;

    /**
     * 税金科目
     */
    @Column(name = "taxSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税金科目'")
    private String taxSubject;

    /**
     * 减值准备对方科目
     */
    @Column(name = "lossImpairmentOppSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '减值准备对方科目'")
    private String lossImpairmentOppSubject;

    /**
     * 增加方式
     */
    @Column(name = "increaseWay", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '增加方式'")
    private IncreaseWay increaseWay;

    /**
     * 用途
     */
    @Column(name = "uses",  columnDefinition = "VARCHAR(255)   COMMENT '用途'")
    private String uses;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 原值（币值）
     */
    @Column(name = "originalValue", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '原值（币值）'")
    private Double originalValue;

    /**
     * 税额
     */
    @Column(name = "tax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税额'")
    private Double tax;

    /**
     * 残值率
     */
    @Column(name = "salvage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '残值率'")
    private Double salvage;

    /**
     * 预计净残值
     */
    @Column(name = "estimatedValue", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预计净残值'")
    private Double estimatedValue;

    /**
     * 减值准备
     */
    @Column(name = "impairmentLoss", columnDefinition = "DECIMAL(10,2)   COMMENT '减值准备'")
    private Double impairmentLoss;

    /**
     * 期初累计折旧
     */
    @Column(name = "accumulatDepreciation", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期初累计折旧'")
    private Double accumulatDepreciation;

    /**
     * 期初净值
     */
    @Column(name = "netBegining", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期初净值'")
    private Double netBegining;

    /**
     * 月折旧额
     */
    @Column(name = "depreciationMonth", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '月折旧额'")
    private Double depreciationMonth;

    /**
     * 折旧方法
     */
    @Column(name = "depreciationWay", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '折旧方法'")
    private DepreciationWay depreciationWay;

    /**
     * 从入账开始预计使用年限
     */
    @Column(name = "estimatedPeriod", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '从入账开始预计使用年限'")
    private String estimatedPeriod;

    /**
     * 入账后累计计提折旧期间数
     */
    @Column(name = "accumulatedPeriod", nullable = false, columnDefinition = "INT(2)   COMMENT '入账后累计计提折旧期间数'")
    private Integer accumulatedPeriod;

    public String getFixedAssetName() {
        return fixedAssetName;
    }

    public void setFixedAssetName(String fixedAssetName) {
        this.fixedAssetName = fixedAssetName;
    }

    public String getFixedAssetSize() {
        return fixedAssetSize;
    }

    public void setFixedAssetSize(String fixedAssetSize) {
        this.fixedAssetSize = fixedAssetSize;
    }

    public FixedAssetType getFixedAssetType() {
        return fixedAssetType;
    }

    public void setFixedAssetType(FixedAssetType fixedAssetType) {
        this.fixedAssetType = fixedAssetType;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    public String getFixedAssetAccountCourse() {
        return fixedAssetAccountCourse;
    }

    public void setFixedAssetAccountCourse(String fixedAssetAccountCourse) {
        this.fixedAssetAccountCourse = fixedAssetAccountCourse;
    }

    public String getTotalDepreciaSubject() {
        return totalDepreciaSubject;
    }

    public void setTotalDepreciaSubject(String totalDepreciaSubject) {
        this.totalDepreciaSubject = totalDepreciaSubject;
    }

    public String getLossImpairmentSubject() {
        return lossImpairmentSubject;
    }

    public void setLossImpairmentSubject(String lossImpairmentSubject) {
        this.lossImpairmentSubject = lossImpairmentSubject;
    }

    public String getDepreciaExpenceSubject() {
        return depreciaExpenceSubject;
    }

    public void setDepreciaExpenceSubject(String depreciaExpenceSubject) {
        this.depreciaExpenceSubject = depreciaExpenceSubject;
    }

    public String getTaxSubject() {
        return taxSubject;
    }

    public void setTaxSubject(String taxSubject) {
        this.taxSubject = taxSubject;
    }

    public String getLossImpairmentOppSubject() {
        return lossImpairmentOppSubject;
    }

    public void setLossImpairmentOppSubject(String lossImpairmentOppSubject) {
        this.lossImpairmentOppSubject = lossImpairmentOppSubject;
    }

    public IncreaseWay getIncreaseWay() {
        return increaseWay;
    }

    public void setIncreaseWay(IncreaseWay increaseWay) {
        this.increaseWay = increaseWay;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Double originalValue) {
        this.originalValue = originalValue;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getSalvage() {
        return salvage;
    }

    public void setSalvage(Double salvage) {
        this.salvage = salvage;
    }

    public Double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public Double getImpairmentLoss() {
        return impairmentLoss;
    }

    public void setImpairmentLoss(Double impairmentLoss) {
        this.impairmentLoss = impairmentLoss;
    }

    public Double getAccumulatDepreciation() {
        return accumulatDepreciation;
    }

    public void setAccumulatDepreciation(Double accumulatDepreciation) {
        this.accumulatDepreciation = accumulatDepreciation;
    }

    public Double getNetBegining() {
        return netBegining;
    }

    public void setNetBegining(Double netBegining) {
        this.netBegining = netBegining;
    }

    public Double getDepreciationMonth() {
        return depreciationMonth;
    }

    public void setDepreciationMonth(Double depreciationMonth) {
        this.depreciationMonth = depreciationMonth;
    }

    public DepreciationWay getDepreciationWay() {
        return depreciationWay;
    }

    public void setDepreciationWay(DepreciationWay depreciationWay) {
        this.depreciationWay = depreciationWay;
    }

    public String getEstimatedPeriod() {
        return estimatedPeriod;
    }

    public void setEstimatedPeriod(String estimatedPeriod) {
        this.estimatedPeriod = estimatedPeriod;
    }

    public Integer getAccumulatedPeriod() {
        return accumulatedPeriod;
    }

    public void setAccumulatedPeriod(Integer accumulatedPeriod) {
        this.accumulatedPeriod = accumulatedPeriod;
    }
}