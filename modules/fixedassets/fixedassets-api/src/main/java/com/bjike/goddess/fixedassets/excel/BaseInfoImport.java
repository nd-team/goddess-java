package com.bjike.goddess.fixedassets.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.fixedassets.enums.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 基本信息导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseInfoImport extends BaseBO {

    /**
     * 固定资产名称
     */
   @ExcelHeader(name = "固定资产名称",notNull = true)
    private String fixedAssetName;

    /**
     * 固定资产规格
     */
    @ExcelHeader(name = "固定资产规格")
    private String fixedAssetSize;

    /**
     * 固定资产类别
     */
    @ExcelHeader(name = "固定资产类别",notNull = true)
    private FixedAssetType fixedAssetType;

    /**
     * 数量
     */
    @ExcelHeader(name = "数量",notNull = true)
    private Integer numbers;

    /**
     * 单位
     */
    @ExcelHeader(name = "单位",notNull = true)
    private Unit unit;

    /**
     * 使用部门
     */
    @ExcelHeader(name = "使用部门",notNull = true)
    private String department;

    /**
     * 使用人
     */
    @ExcelHeader(name = "使用人",notNull = true)
    private String userName;

    /**
     * 入账日期
     */
    @ExcelHeader(name = "入账日期",notNull = true)
    private String bookDate;

    /**
     * 固定资产会计科目
     */
    @ExcelHeader(name = "固定资产会计科目",notNull = true)
    private String fixedAssetAccountCourse;

    /**
     * 累计折旧科目
     */
    @ExcelHeader(name = "累计折旧科目",notNull = true)
    private String totalDepreciaSubject;

    /**
     * 减值准备科目
     */
    @ExcelHeader(name = "减值准备科目",notNull = true)
    private String lossImpairmentSubject;

    /**
     * 折旧费用科目
     */
    @ExcelHeader(name = "折旧费用科目",notNull = true)
    private String depreciaExpenceSubject;

    /**
     * 税金科目
     */
    @ExcelHeader(name = "税金科目",notNull = true)
    private String taxSubject;

    /**
     * 减值准备对方科目
     */
    @ExcelHeader(name = "减值准备对方科目",notNull = true)
    private String lossImpairmentOppSubject;

    /**
     * 增加方式
     */
    @ExcelHeader(name = "增加方式",notNull = true)
    private IncreaseWay increaseWay;

    /**
     * 用途
     */
    @ExcelHeader(name = "用途")
    private String uses;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态",notNull = true)
    private Status status;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

    /**
     * 原值（币值）
     */
    @ExcelHeader(name = "原值（币值）",notNull = true)
    private Double originalValue;

    /**
     * 税额
     */
    @ExcelHeader(name = "税额",notNull = true)
    private Double tax;

    /**
     * 残值率
     */
    @ExcelHeader(name = "残值率",notNull = true)
    private Double salvage;

    /**
     * 预计净残值
     */
    @ExcelHeader(name = "预计净残值",notNull = true)
    private Double estimatedValue;

    /**
     * 减值准备
     */
    @ExcelHeader(name = "减值准备")
    private Double impairmentLoss;

    /**
     * 期初累计折旧
     */
    @ExcelHeader(name = "期初累计折旧",notNull = true)
    private Double accumulatDepreciation;

    /**
     * 期初净值
     */
    @ExcelHeader(name = "期初净值",notNull = true)
    private Double netBegining;

    /**
     * 月折旧额
     */
    @ExcelHeader(name = "月折旧额",notNull = true)
    private Double depreciationMonth;

    /**
     * 折旧方法
     */
    @ExcelHeader(name = "折旧方法",notNull = true)
    private DepreciationWay depreciationWay;

    /**
     * 从入账开始预计使用年限
     */
    @ExcelHeader(name = "从入账开始预计使用年限",notNull = true)
    private String estimatedPeriod;

    /**
     * 入账后累计计提折旧期间数
     */
    @ExcelHeader(name = "入账后累计计提折旧期间数",notNull = true)
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

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
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