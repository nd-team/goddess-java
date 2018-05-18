package com.bjike.goddess.fixedassets.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.fixedassets.enums.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 基本信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseInfoTO extends BaseTO {

    /**
     * 固定资产名称
     */
    @NotBlank(message = "固定资产名称不能为空",groups = {ADD.class, EDIT.class})
    private String fixedAssetName;

    /**
     * 固定资产规格
     */
    private String fixedAssetSize;

    /**
     * 固定资产类别
     */
    @NotNull(message = "固定资产类别不能为空",groups = {ADD.class, EDIT.class})
    private FixedAssetType fixedAssetType;

    /**
     * 数量
     */
    @NotNull(message = "固定资产类别不能为空",groups = {ADD.class, EDIT.class})
    private Integer numbers;

    /**
     * 单位
     */
    @NotNull(message = "单位不能为空",groups = {ADD.class, EDIT.class})
    private Unit unit;

    /**
     * 使用部门
     */
    @NotBlank(message = "使用部门不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 使用人
     */
    @NotBlank(message = "使用人不能为空",groups = {ADD.class, EDIT.class})
    private String userName;

    /**
     * 入账日期
     */
    @NotBlank(message = "入账日期不能为空",groups = {ADD.class, EDIT.class})
    private String bookDate;

    /**
     * 固定资产会计科目
     */
    @NotBlank(message = "固定资产会计科目不能为空",groups = {ADD.class, EDIT.class})
    private String fixedAssetAccountCourse;

    /**
     * 累计折旧科目
     */
    @NotBlank(message = "累计折旧科目不能为空",groups = {ADD.class, EDIT.class})
    private String totalDepreciaSubject;

    /**
     * 减值准备科目
     */
    @NotBlank(message = "减值准备科目不能为空",groups = {ADD.class, EDIT.class})
    private String lossImpairmentSubject;

    /**
     * 折旧费用科目
     */
    @NotBlank(message = "折旧费用科目不能为空",groups = {ADD.class, EDIT.class})
    private String depreciaExpenceSubject;

    /**
     * 税金科目
     */
    @NotBlank(message = "税金科目不能为空",groups = {ADD.class, EDIT.class})
    private String taxSubject;

    /**
     * 减值准备对方科目
     */
    @NotBlank(message = "减值准备对方科目不能为空",groups = {ADD.class, EDIT.class})
    private String lossImpairmentOppSubject;

    /**
     * 增加方式
     */
    @NotBlank(message = "增加方式不能为空",groups = {ADD.class, EDIT.class})
    private IncreaseWay increaseWay;

    /**
     * 用途
     */
    private String uses;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空",groups = {ADD.class, EDIT.class})
    private Status status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 原值（币值）
     */
    @NotNull(message = "原值不能为空",groups = {ADD.class, EDIT.class})
    private Double originalValue;

    /**
     * 税额
     */
    @NotNull(message = "税额不能为空",groups = {ADD.class, EDIT.class})
    private Double tax;

    /**
     * 残值率
     */
    @NotNull(message = "残值率不能为空",groups = {ADD.class, EDIT.class})
    private Double salvage;

    /**
     * 减值准备
     */
    private Double impairmentLoss;

    /**
     * 期初累计折旧
     */
    @NotNull(message = "期初累计折旧不能为空",groups = {ADD.class, EDIT.class})
    private Double accumulatDepreciation;

    /**
     * 期初净值
     */
    @NotNull(message = "期初净值不能为空",groups = {ADD.class, EDIT.class})
    private Double netBegining;

    /**
     * 折旧方法
     */
    @NotNull(message = "折旧方法不能为空",groups = {EDIT.class})
    private DepreciationWay depreciationWay;

    /**
     * 从入账开始预计使用年限
     */
    @NotNull(message = "从入账开始预计使用年限不能为空",groups = {EDIT.class})
    private Integer estimatedPeriod;

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


    public DepreciationWay getDepreciationWay() {
        return depreciationWay;
    }

    public void setDepreciationWay(DepreciationWay depreciationWay) {
        this.depreciationWay = depreciationWay;
    }

    public Integer getEstimatedPeriod() {
        return estimatedPeriod;
    }

    public void setEstimatedPeriod(Integer estimatedPeriod) {
        this.estimatedPeriod = estimatedPeriod;
    }
}